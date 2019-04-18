package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;
import vn.blu.tvviem.loansys.services.protocol.ThongTinService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
class ThongTinRest {

    @Autowired
    private ThongTinService thongTinService;

    // Lay cac thong tin cua mot loai tai san, su dung parameter on URL
    // request client-side is /thongtins/loaitaisans?id=1
    // Cach lam nay co the thiet lap gia tri mac dinh cho THAM SO
    @GetMapping("/thongtins/loaitaisans")
    public List<ThongTin> getThongTinsByLoaiTaiSanId(@RequestParam("id") Integer loaiTaiSanId) {
        return thongTinService.findByLoaiTaiSanId(loaiTaiSanId);
    }

    @GetMapping("/loaitaisans/{loaiTaiSanId}/thongtins")
    public List<ThongTin> getThongTinsByLoaiTaiSanIdInPath(@PathVariable Integer loaiTaiSanId) {
        return thongTinService.findByLoaiTaiSanId(loaiTaiSanId);
    }

    /*@GetMapping("/{loaiTaiSanId}/thongtins")
    public Page<ThongTin> getThongTinsByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId,
                                                     Pageable pageable) {
        return thongTinService.findByLoaiTaiSanId(loaiTaiSanId, pageable);
    }*/


    // Lay mot thong tin cua mot loai tai san
    @GetMapping("/loaitaisans/{loaiTaiSanId}/thongtins/{thongTinId}")
    public ThongTin getThongTinByLoaiTaiSanId(@PathVariable Integer loaiTaiSanId, @PathVariable Integer thongTinId) {
        return thongTinService.getThongTinFromLoaiTaiSan(loaiTaiSanId, thongTinId);
    }

    // Tao mot thong tin cho mot loai tai san
    @PostMapping("/loaitaisans/{loaiTaiSanId}/thongtins")
    public ThongTin createThongTin(@PathVariable Integer loaiTaiSanId, @Valid @RequestBody ThongTin thongTin) {
        return thongTinService.saveThongTinCuaLoaiTs(thongTin, loaiTaiSanId);
    }

    // Cap nhat thong tin cua mot loai tai san
    @PutMapping("/loaitaisans/{loaiTaiSanId}/thongtins/{thongTinId}")
    public ThongTin updateThongTin(@PathVariable Integer loaiTaiSanId, @PathVariable Integer thongTinId, @Valid
    @RequestBody ThongTin thongTinDetail) {
        return thongTinService.updateThongTinCuaLoaiTs(loaiTaiSanId, thongTinId, thongTinDetail);
    }

    // Xoa thong tin cua mot loai tai san
    @DeleteMapping("/loaitaisans/{loaiTaiSanId}/thongtins/{thongTinId}")
    public ResponseEntity<?> deleteThongTin(@PathVariable Integer loaiTaiSanId, @PathVariable Integer thongTinId) {
        return thongTinService.deleteThongTin(loaiTaiSanId, thongTinId);
    }
}