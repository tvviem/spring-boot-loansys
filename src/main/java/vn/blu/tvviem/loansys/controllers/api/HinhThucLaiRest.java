package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.blu.tvviem.loansys.models.hoso.HinhThucLai;
import vn.blu.tvviem.loansys.services.protocol.HinhThucLaiService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1")
public class HinhThucLaiRest {

    @Autowired
    HinhThucLaiService hinhThucLaiService;

    // them hinh thuc
    @PostMapping("/hinhthuclais")
    public ResponseEntity<?> createHinhThuc(@Valid @RequestBody HinhThucLai hinhThucLai) {
        HinhThucLai createdObj = hinhThucLaiService.luuHinhThuc(hinhThucLai);
        /*if(createdObj == null) {
            return new ResponseEntity(HttpStatus.CONFLICT); // Trung ten loai ho so
        }*/
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdObj.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // return 201 created
    }

    @GetMapping("/hinhthuclais")
    public Iterable<HinhThucLai> getAll() {
        return hinhThucLaiService.getCacHinhThucLai();
    }
    @GetMapping("/hinhthuclais/{id}")
    public ResponseEntity<HinhThucLai> getHinhThuc(@PathVariable Integer id) {
        HinhThucLai hinhThucLai = hinhThucLaiService.getHinhThucLai(id);
        if (hinhThucLai == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hinhThucLai);
    }

    @PutMapping("/hinhthuclais/{id}")
    public ResponseEntity<?> updateHinhThuc(@PathVariable Integer id, @Valid @RequestBody HinhThucLai
            hinhThucLaiDetail) {
        HinhThucLai hinhThucLai = hinhThucLaiService.getHinhThucLai(id);
        if(hinhThucLai == null) {
            return ResponseEntity.notFound().build();
        }
        hinhThucLaiDetail.setId(id);
        HinhThucLai updated = hinhThucLaiService.capNhatHinhThuc(hinhThucLaiDetail);
        return ResponseEntity.ok(updated); // return 200
    }

    @DeleteMapping("/hinhthuclais/{id}")
    public ResponseEntity<?> deleteHinhThucLai(@PathVariable Integer id) {
        HinhThucLai hinhThucLai = hinhThucLaiService.getHinhThucLai(id);
        if(hinhThucLai == null) {
            return ResponseEntity.notFound().build();
        }
        hinhThucLaiService.deleteHinhThuc(hinhThucLai);
        return ResponseEntity.noContent().build(); // return 204
    }
}
