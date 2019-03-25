package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;
import vn.blu.tvviem.loansys.services.ThongTinServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/loaitaisans")
public class ThongTinRest {

    @Autowired
    private ThongTinServiceImpl thongTinServiceImpl;

    // Lay cac thong tin cua mot loai tai san
    @GetMapping("/{loaiTaiSanId}/thongtins")
    public List<ThongTin> getThongTinsByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId) {
        return thongTinServiceImpl.findByLoaiTaiSanId(loaiTaiSanId);
    }

    /*@GetMapping("/{loaiTaiSanId}/thongtins")
    public Page<ThongTin> getThongTinsByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId,
                                                     Pageable pageable) {
        return thongTinService.findByLoaiTaiSanId(loaiTaiSanId, pageable);
    }*/


    // Lay mot thong tin cua mot loai tai san
    @GetMapping("/{loaiTaiSanId}/thongtins/{thongTinId}")
    public ThongTin getThongTinByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId, @PathVariable Integer thongTinId) {
        return thongTinServiceImpl.getThongTinFromLoaiTaiSan(loaiTaiSanId, thongTinId);
    }

    // Tao mot thong tin cho mot loai tai san
    @PostMapping("/{loaiTaiSanId}/thongtins")
    public ThongTin createThongTin(@PathVariable Integer loaiTaiSanId, @Valid @RequestBody ThongTin thongTin) {
        return thongTinServiceImpl.saveThongTinCuaLoaiTs(thongTin, loaiTaiSanId);
    }

    // Cap nhat thong tin cua mot loai tai san
    @PutMapping("/{loaiTaiSanId}/thongtins/{thongTinId}")
    public ThongTin updateThongTin(@PathVariable Integer loaiTaiSanId, @PathVariable Integer thongTinId, @Valid
    @RequestBody ThongTin thongTinDetail) {
        return thongTinServiceImpl.updateThongTinCuaLoaiTs(loaiTaiSanId, thongTinId, thongTinDetail);
    }

    // Xoa thong tin cua mot loai tai san
    @DeleteMapping("/{loaiTaiSanId}/thongtins/{thongTinId}")
    public ResponseEntity<?> deleteThongTin(@PathVariable Integer loaiTaiSanId, @PathVariable Integer thongTinId) {
        return thongTinServiceImpl.deleteThongTin(loaiTaiSanId, thongTinId);
    }
}