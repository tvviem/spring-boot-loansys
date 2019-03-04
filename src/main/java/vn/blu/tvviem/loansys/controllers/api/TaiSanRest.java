package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;
import vn.blu.tvviem.loansys.services.TaiSanService;
import vn.blu.tvviem.loansys.web.dto.ChiTietThongTinDto;

import javax.validation.Valid;

@RestController
public class TaiSanRest {

    @Autowired
    private TaiSanService taiSanService;

    // Tao tai san cho khach hang (hinh anh cap nhat sau)
    @PostMapping("/taisans/create/{khachHangId}/{loaiTaiSanId}")
    public TaiSan createTaiSan(@PathVariable Long khachHangId, @PathVariable Integer loaiTaiSanId, @Valid @RequestBody
            ChiTietThongTinDto chiTietThongTinDto) {

        return taiSanService.saveTaiSan(khachHangId, loaiTaiSanId, chiTietThongTinDto);
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

    /*@GetMapping("/{loaiTaiSanId}/thongtins")
    public Page<ThongTin> getThongTinsByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId,
                                                     Pageable pageable) {
        return thongTinService.findByLoaiTaiSanId(loaiTaiSanId, pageable);
    }*/
}
