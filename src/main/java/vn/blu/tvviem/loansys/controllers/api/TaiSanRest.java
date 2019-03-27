package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;
import vn.blu.tvviem.loansys.services.protocol.TaiSanService;
import vn.blu.tvviem.loansys.web.dto.TaiSanDto;

import javax.validation.Valid;

@RestController
public class TaiSanRest {

    @Autowired
    private TaiSanService taiSanService;

    // Tao tai san cho khach hang (hinh anh cap nhat sau)
    @Transactional
    @PostMapping("/taisans/create")
    public TaiSan createTaiSan(@Valid @RequestBody TaiSanDto taiSanDto) {
        return taiSanService.saveTaiSan(taiSanDto);
    }

    // Lay thong tin chi tiet cua tat ca cac tai san (Pageable)
    @GetMapping("/taisans/danhsach")
    public Page<TaiSan> getAllTaiSans(Pageable pageable) {
        return taiSanService.getAllTaiSanPageable(pageable);
    }

    // Lay thong tin cua 01 tai san cu the
    @GetMapping("/taisans/{taiSanId}")
    public TaiSan getTaiSan(@PathVariable Long taiSanId) {
        return taiSanService.findOneByTaiSanId(taiSanId);
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

    /*@GetMapping("/{loaiTaiSanId}/thongtins")
    public Page<ThongTin> getThongTinsByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId,
                                                     Pageable pageable) {
        return thongTinService.findByLoaiTaiSanId(loaiTaiSanId, pageable);
    }*/
}
