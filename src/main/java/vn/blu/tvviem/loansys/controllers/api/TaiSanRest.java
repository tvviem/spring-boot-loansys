package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.blu.tvviem.loansys.models.taisan.HinhTaiSan;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;
import vn.blu.tvviem.loansys.services.HinhTaiSanService;
import vn.blu.tvviem.loansys.services.protocol.ChiTietTaiSanService;
import vn.blu.tvviem.loansys.services.protocol.TaiSanService;
import vn.blu.tvviem.loansys.web.dto.ChiTietThongTin;
import vn.blu.tvviem.loansys.web.dto.TaiSanDto;
import vn.blu.tvviem.loansys.web.dto.UploadFileResponse;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
class TaiSanRest {

    @Autowired
    private TaiSanService taiSanService;

    @Autowired
    private ChiTietTaiSanService chiTietTaiSanService;

    @Autowired
    private HinhTaiSanService hinhTaiSanService;

    // Lay thong tin chi tiet cua tat ca cac tai san (Pageable)
    @GetMapping("/taisans")
    public Page<TaiSan> getAllTaiSans(Pageable pageable) {
        return taiSanService.getAllTaiSanPageable(pageable);
    }

    // Lay thong tin cua 01 tai san cu the
    @GetMapping("/taisans/{taiSanId}")
    public TaiSan getTaiSan(@PathVariable Long taiSanId) {
        return taiSanService.findOneByTaiSanId(taiSanId);
    }

    // Tao tai san cho khach hang (hinh anh cap nhat sau)
    @Transactional
    @PostMapping("/taisans")
    public TaiSan createTaiSan(@Valid @RequestBody TaiSanDto taiSanDto) {
        return taiSanService.saveTaiSan(taiSanDto);
    }

    // Cap nhat thong tin tai san
    @Transactional
    @PutMapping("/taisans/{taiSanId}")
    public TaiSan updateTaiSan(@PathVariable Long taiSanId, @Valid @RequestBody TaiSanDto newTaiSanDto) {
        return taiSanService.updateTaiSanById(taiSanId, newTaiSanDto);
        //return null;
    }

    // Xoa tai san biet Id
    @DeleteMapping("/taisans/{taiSanId}")
    public ResponseEntity<TaiSan> deleteTaiSan(@PathVariable Long taiSanId) {
        if(taiSanService.deleteTaiSan(taiSanId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Luu hinh anh sau khi luu tai san thanh cong
    @PostMapping("/taisans/{taiSanId}/luu-hinh-anh")
    public UploadFileResponse uploadFile(@PathVariable Long taiSanId, @RequestParam("file") MultipartFile file) {
        HinhTaiSan hinhTaiSanStored = hinhTaiSanService.storeFile(taiSanId, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/taisans/")
                .path(taiSanId.toString())
                .path("/hinhanhs")
                .toUriString();

        return new UploadFileResponse(hinhTaiSanStored.getTenTapTin(), fileDownloadUri,
                hinhTaiSanStored.getLoaiHinh(), file.getSize());
    }

    @PostMapping("/taisans/{taiSanId}/luu-danh-sach-hinh")
    public List<UploadFileResponse> uploadMultipleFiles(@PathVariable Long taiSanId,
                                                        @RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(taiSanId, file))
                .collect(Collectors.toList());
    }

    @GetMapping("/taisans/{taiSanId}/hinhanhs/{position}")
    public ResponseEntity<Resource> xemHinhTaiSan(@PathVariable Long taiSanId, @PathVariable int position) {
        // Load file from database
        List<HinhTaiSan> hinhTaiSans = hinhTaiSanService.getHinhTaiSans(taiSanId);

        // get one picture at position
        HinhTaiSan hinh1 = hinhTaiSans.get(position);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(hinh1.getLoaiHinh()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + hinh1.getTenTapTin() + "\"")
                .body(new ByteArrayResource(hinh1.getNoiDungHinh()));
    }

    /** Cac thao tac voi tung chi tiet tai san */
    @PostMapping("/taisans/{taiSanId}/luu-thong-tin")
    public ResponseEntity<String> themMotThongTinTaiSan(@PathVariable Long taiSanId,
                                             @RequestBody ChiTietThongTin chiTietThongTin) {
        boolean result = chiTietTaiSanService.saveThongTinTaiSan(taiSanId, chiTietThongTin.getIdThongTin(),
                chiTietThongTin.getNoiDung());
        if(result)
            return ResponseEntity.created(URI.create("/taisans/"+taiSanId+"/luu-thong-tin/")).build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/taisans/{taiSanId}/cap-nhat-thong-tin")
    public ResponseEntity<String> capNhatMotThongTinTaiSan(@PathVariable Long taiSanId,
                                                           @RequestBody ChiTietThongTin chiTietThongTin) {
        boolean result = chiTietTaiSanService.saveThongTinTaiSan(taiSanId, chiTietThongTin.getIdThongTin(),
                chiTietThongTin.getNoiDung());
        if (result)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/taisans/{taiSanId}/xoa-mot-chi-tiet/{thongTinId}")
    public ResponseEntity<String> xoaMotThongTinTaiSan(@PathVariable Long taiSanId, @PathVariable Integer thongTinId) {
        boolean result = chiTietTaiSanService.deleteThongTinTaiSan(taiSanId, thongTinId);
        if (result)
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }
}
