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
    public ResponseEntity<?> createTaiSan(@Valid @RequestBody TaiSanDto taiSanDto) {
        TaiSan taiSanCreated = taiSanService.saveTaiSan(taiSanDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taiSanCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // return 201 and location in header
    }

    /*@Transactional
    @PostMapping("/taisans")
    public TaiSan createTaiSan(@Valid @RequestBody TaiSanDto taiSanDto) {
        return taiSanService.saveTaiSan(taiSanDto);
    }*/

    // Cap nhat thong tin tai san
    @Transactional
    @PutMapping("/taisans/{taiSanId}")
    public ResponseEntity<TaiSan> updateTaiSan(@PathVariable Long taiSanId,
                                               @Valid @RequestBody TaiSanDto newTaiSanDto) {
        TaiSan taiSanUpdated = taiSanService.updateTaiSanById(taiSanId, newTaiSanDto);
        return ResponseEntity.ok(taiSanUpdated);
    }

    // Xoa tai san biet Id, anh huong mat tat ca thong tin
    @DeleteMapping("/taisans/{taiSanId}")
    public ResponseEntity<TaiSan> deleteTaiSan(@PathVariable Long taiSanId) {
        if(taiSanService.deleteTaiSan(taiSanId)) {
            return ResponseEntity.noContent().build(); // return 204
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Luu hinh anh sau khi luu tai san thanh cong
    @PostMapping("/taisans/{taiSanId}/hinhanhs")
    public UploadFileResponse uploadFile(@PathVariable Long taiSanId, @RequestParam("file") MultipartFile file) {
        HinhTaiSan hinhTaiSanStored = hinhTaiSanService.storeFile(taiSanId, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idHinh}")
                .buildAndExpand(hinhTaiSanStored.getId().toString())
                .toUriString();

        return new UploadFileResponse(hinhTaiSanStored.getTenTapTin(), fileDownloadUri,
                hinhTaiSanStored.getLoaiHinh(), file.getSize());
    }

    @PostMapping("/taisans/{taiSanId}/hinhanhs/luu-nhieu")
    public List<UploadFileResponse> uploadMultipleFiles(@PathVariable Long taiSanId,
                                                        @RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(file -> uploadFile(taiSanId, file))
                .collect(Collectors.toList());
    }

    @GetMapping("/taisans/{taiSanId}/hinhanhs/{hinhTaiSanId}")
    public ResponseEntity<Resource> xemHinhTaiSanTheoId(@PathVariable Long taiSanId, @PathVariable Long hinhTaiSanId) {
        HinhTaiSan hinhTaiSanFind = hinhTaiSanService.getHinhTaiSanByIdAndTaiSan(hinhTaiSanId, taiSanId);

        if(hinhTaiSanFind != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(hinhTaiSanFind.getLoaiHinh()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + hinhTaiSanFind.getTenTapTin() + "\"")
                    .body(new ByteArrayResource(hinhTaiSanFind.getNoiDungHinh()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/taisans/{taiSanId}/hinhanhs/vitri/{position}")
    public ResponseEntity<Resource> xemHinhTaiSanTheoViTri(@PathVariable Long taiSanId, @PathVariable int position) {
        // Load file from database
        List<HinhTaiSan> hinhTaiSans = hinhTaiSanService.getHinhTaiSans(taiSanId);

        // get one picture at position
        HinhTaiSan onePicture = hinhTaiSans.get(position);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(onePicture.getLoaiHinh()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + onePicture.getTenTapTin() + "\"")
                .body(new ByteArrayResource(onePicture.getNoiDungHinh()));
    }

    /** Cac thao tac voi tung chi tiet tai san */
    @PostMapping("/taisans/{taiSanId}/thongtin")
    public ResponseEntity<?> themMotThongTinTaiSan(@PathVariable Long taiSanId,
                                             @RequestBody ChiTietThongTin chiTietThongTin) {
        boolean result = chiTietTaiSanService.saveThongTinTaiSan(taiSanId, chiTietThongTin.getIdThongTin(),
                chiTietThongTin.getNoiDung());

        if(result) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
            return ResponseEntity.created(location).build(); // return 201 when created one chiTietThongTin cua tai san
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/taisans/{taiSanId}/thongtin")
    public ResponseEntity<?> capNhatMotThongTinTaiSan(@PathVariable Long taiSanId,
                                                           @RequestBody ChiTietThongTin chiTietThongTin) {
        boolean result = chiTietTaiSanService.saveThongTinTaiSan(taiSanId, chiTietThongTin.getIdThongTin(),
                chiTietThongTin.getNoiDung());
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/taisans/{taiSanId}/thongtin/{thongTinId}")
    public ResponseEntity<?> xoaMotThongTinTaiSan(@PathVariable Long taiSanId, @PathVariable Integer thongTinId) {
        boolean isDeleted = chiTietTaiSanService.deleteThongTinTaiSan(taiSanId, thongTinId);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // return 204 when delete one chiTietTaiSan
        }
        return ResponseEntity.badRequest().build();
    }
}
