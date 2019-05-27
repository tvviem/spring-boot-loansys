package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.blu.tvviem.loansys.models.hoso.GhiLai;
import vn.blu.tvviem.loansys.services.protocol.GhiLaiService;
import vn.blu.tvviem.loansys.web.dto.GhiLaiDto;

@RestController
@RequestMapping("/v1")
public class GhiLaiRest {

    @Autowired
    private GhiLaiService ghiLaiService;

    @PostMapping("/ghilai")
    public ResponseEntity<?> ghiLai(@RequestBody GhiLaiDto ghiLaiDto) {
        GhiLai ghiLaiOk =  ghiLaiService.nopLai(ghiLaiDto);
        if(ghiLaiOk!=null) {
            return ResponseEntity.ok(ghiLaiOk);
        }
        return ResponseEntity.badRequest().build();
    }

    // Tra ve chi tiet ghi lai cua mot hoso
    @GetMapping("/ghilai/{hoSoId}")
    public Iterable<GhiLai> layChiTietNopLai(@PathVariable Long hoSoId) {
        return ghiLaiService.layChiTietGhiLai(hoSoId);
    }

    /*// Cap nhat viec nop lai voi thong tin so ngay nop va ngay nop, hosoId
    @PutMapping("/ghilai/{id}")
    public ResponseEntity<?> capNhatGhiLai(@PathVariable Long id, @RequestBody GhiLaiDto ghiLaiDtoToUpdated) {
        return null;
    }*/

    // Xoa thong tin ghi lai
    @DeleteMapping("/ghilai/{id}")
    public ResponseEntity<?> xoaThongTinGhiLai(@PathVariable Long id) {
        if(ghiLaiService.xoaThongTinGhiLai(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
