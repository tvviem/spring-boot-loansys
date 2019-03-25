package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.services.KhachHangServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
// @RequestMapping("khachhang")
//@Controller
public class KhachHangRest {

    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;

    // @RequestMapping(method = RequestMethod.POST, value = "khachhangs")
    @PostMapping("/khachhangs")
    public KhachHang createKhachHang(@Valid @RequestBody KhachHang k) {
        return khachHangServiceImpl.saveKhachHang(k);
    }

    @GetMapping("/khachhangs")
    public List<KhachHang> getAll() {
        return khachHangServiceImpl.getAllKhachHang();
    }

    @GetMapping("/khachhangs/{id}")
    //@ResponseBody
    public ResponseEntity<KhachHang> getKhachHang(@PathVariable Long id) {
        KhachHang khachHang = khachHangServiceImpl.getOneKhachHang(id);
        if (khachHang==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(khachHang);
    }

    // Cap nhat khach hang
    @PutMapping("/khachhangs/{id}")
    public ResponseEntity<KhachHang> updateKhachHang(@PathVariable Long id, @Valid @RequestBody KhachHang
            khachHangDetail) {
        KhachHang khachHang = khachHangServiceImpl.getOneKhachHang(id);
        if(khachHang==null) {
            return ResponseEntity.notFound().build();
        }
        khachHangDetail.setId(id);
        // WARNING: when have redundant data in RequestBody, khachHangDetail
        KhachHang updatedKh = khachHangServiceImpl.saveKhachHang(khachHangDetail);
        return ResponseEntity.ok(updatedKh);
    }

    // Xoa 1 khach hang
    @DeleteMapping("/khachhangs/{id}")
    public ResponseEntity<KhachHang> deleteKhachHang(@PathVariable Long id) {
        KhachHang khachHang = khachHangServiceImpl.getOneKhachHang(id);
        if(khachHang==null) {
            return ResponseEntity.notFound().build();
        }
        khachHangServiceImpl.deleteKhachHang(khachHang);
        return ResponseEntity.ok().build();
    }
}
