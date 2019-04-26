package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.services.protocol.KhachHangService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1")
class KhachHangRest {

    private final KhachHangService khachHangService;

    @Autowired
    public KhachHangRest(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @PostMapping("/khachhangs")
    public ResponseEntity<?> createKhachHang(@Valid @RequestBody KhachHang k) {
        KhachHang khachHangCreated = khachHangService.saveKhachHang(k);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(khachHangCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // return 201 and location in header
    }

    // ex1: //http://localhost:8080/v1/khachhangs/?size=2&page=1
    // ex2: http://localhost:8080/v1/khachhangs?hoten=xuyen&size=1&page=0
    @GetMapping("/khachhangs")
    public Page<KhachHang> getAll(@RequestParam(value = "hoten", required = false, defaultValue = "") String partOfHoTen, Pageable pageable) {
        // Pageable pageable = PageRequest.of(0, 3, Sort.by("hoTen"));
        if (partOfHoTen.isEmpty())
            return khachHangService.getAllKhachHang(pageable);

        return khachHangService.getKhachHangHavePartHoTen(partOfHoTen, pageable);
    }

    @GetMapping("/khachhangs/{id}") // Tim khach hang theo Id
    public ResponseEntity<KhachHang> getKhachHang(@PathVariable Long id) {
        KhachHang khachHang = khachHangService.getOneKhachHang(id);
        if (khachHang==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(khachHang);
    }

    @GetMapping("/khachhangs/cmnd/{soCmnd}") // tim chinh xac theo soCmnd
    public ResponseEntity<?> getKhachHangBySoCmnd(@PathVariable String soCmnd) {
        KhachHang khachHangSearch = khachHangService.getKhachHangBySoCmnd(soCmnd);
        if (khachHangSearch == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(khachHangSearch);
    }

    // Cap nhat khach hang
    @PutMapping("/khachhangs/{id}")
    public ResponseEntity<KhachHang> updateKhachHang(@PathVariable Long id, @Valid @RequestBody KhachHang
            khachHangDetail) {
        KhachHang khachHang = khachHangService.getOneKhachHang(id);
        if(khachHang==null) {
            return ResponseEntity.notFound().build();
        }
        khachHangDetail.setId(id);
        // WARNING: when have redundant data in RequestBody, khachHangDetail
        KhachHang updatedKh = khachHangService.saveKhachHang(khachHangDetail);
        return ResponseEntity.ok(updatedKh);
    }

    // Xoa 1 khach hang
    @DeleteMapping("/khachhangs/{id}")
    public ResponseEntity<KhachHang> deleteKhachHang(@PathVariable Long id) {
        KhachHang khachHang = khachHangService.getOneKhachHang(id);
        if(khachHang==null) {
            return ResponseEntity.notFound().build();
        }
        khachHangService.deleteKhachHang(khachHang);
        return ResponseEntity.noContent().build(); // 204
    }
}
