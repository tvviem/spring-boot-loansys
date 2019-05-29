package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.blu.tvviem.loansys.models.hoso.GhiLai;
import vn.blu.tvviem.loansys.services.protocol.GhiLaiService;
import vn.blu.tvviem.loansys.web.dto.GhiLaiDto;

import java.net.URI;

@RestController
@RequestMapping("/v1")
public class GhiLaiRest {

    @Autowired
    private GhiLaiService ghiLaiService;

    @PostMapping("/ghilais")
    @Transactional
    public ResponseEntity<?> ghiLai(@RequestBody GhiLaiDto ghiLaiDto) {
        GhiLai ghiLaiOk =  ghiLaiService.nopLai(ghiLaiDto);
        if(ghiLaiOk!=null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(ghiLaiOk.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.badRequest().build();
    }

    // Lay thong tin ghi lai voi id
    @GetMapping("/ghilais/{id}")
    public ResponseEntity<GhiLai> getHinhThuc(@PathVariable Long id) {
        GhiLai ghiLai = ghiLaiService.getGhiLai(id);
        if (ghiLai == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ghiLai);
    }

    // Tra ve chi tiet cac lan ghi lai cua mot hoso
    @GetMapping("/ghilais/{hoSoId}/chitiet")
    public Iterable<GhiLai> layChiTietNopLai(@PathVariable Long hoSoId) {
        return ghiLaiService.layChiTietGhiLai(hoSoId);
    }

    /*// Cap nhat viec nop lai voi thong tin so ngay nop va ngay nop, hosoId
    @PutMapping("/ghilai/{id}")
    public ResponseEntity<?> capNhatGhiLai(@PathVariable Long id, @RequestBody GhiLaiDto ghiLaiDtoToUpdated) {
        return null;
    }*/

    // Xoa thong tin ghi lai
    @DeleteMapping("/ghilais/{id}")
    public ResponseEntity<?> xoaThongTinGhiLai(@PathVariable Long id) {
        if(ghiLaiService.xoaThongTinGhiLai(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
