package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;
import vn.blu.tvviem.loansys.services.ThongTinService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/loaitaisans")
public class ThongTinRest {

    @Autowired
    private ThongTinService thongTinService;

    // Lay cac thong tin cua mot loai tai san
    @GetMapping("/{loaiTaiSanId}/thongtins")
    public List<ThongTin> getThongTinsByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId) {
        return thongTinService.findByLoaiTaiSanId(loaiTaiSanId);
    }

    /*@GetMapping("/{loaiTaiSanId}/thongtins")
    public Page<ThongTin> getThongTinsByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId,
                                                     Pageable pageable) {
        return thongTinService.findByLoaiTaiSanId(loaiTaiSanId, pageable);
    }*/



    // Tao mot thong tin cho mot loai tai san
    @PostMapping("/{loaiTaiSanId}/thongtins")
    public ThongTin createThongTin(@PathVariable Integer loaiTaiSanId, @Valid @RequestBody ThongTin thongTin) {
        return thongTinService.saveThongTinCuaLoaiTs(thongTin, loaiTaiSanId);
    }

    // Cap nhat thong tin cua mot loai tai san
    @PutMapping("/{loaiTaiSanId}/thongtins/{thongTinId}")
    public ThongTin updateThongTin(@PathVariable Integer loaiTaiSanId, @PathVariable Integer thongTinId, @Valid
    @RequestBody ThongTin thongTinDetail) {
        return thongTinService.updateThongTinCuaLoaiTs(loaiTaiSanId, thongTinId, thongTinDetail);
    }

    // Xoa thong tin cua mot loai tai san
    @DeleteMapping("/{loaiTaiSanId}/thongtins/{thongTinId}")
    public ResponseEntity<?> deleteThongTin(@PathVariable Integer loaiTaiSanId, @PathVariable Integer thongTinId) {
        return thongTinService.deleteThongTin(loaiTaiSanId, thongTinId);
    }
}