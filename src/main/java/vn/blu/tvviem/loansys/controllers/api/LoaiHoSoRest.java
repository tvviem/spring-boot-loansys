package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.blu.tvviem.loansys.models.hoso.LoaiHoSo;
import vn.blu.tvviem.loansys.services.protocol.LoaiHoSoService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1")
public class LoaiHoSoRest {

    private final LoaiHoSoService loaiHoSoService;

    @Autowired
    public LoaiHoSoRest(LoaiHoSoService loaiHoSoService) {
        this.loaiHoSoService = loaiHoSoService;
    }

    @PostMapping("/loaihosos")
    public ResponseEntity createLoaiHoSo(@Valid @RequestBody LoaiHoSo loaiHoSo) {
        LoaiHoSo createdObj = loaiHoSoService.luuLoaiHs(loaiHoSo);
        if(createdObj == null) {
            return new ResponseEntity(HttpStatus.CONFLICT); // Trung ten loai ho so
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdObj.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // return 201 created
    }

    @GetMapping("/loaihosos")
    public Iterable<LoaiHoSo> getAll() {
        return loaiHoSoService.getAllLoaiHs();
    }

    @GetMapping("/loaihosos/{id}")
    public ResponseEntity<LoaiHoSo> getLoaiTs(@PathVariable Integer id) {
        LoaiHoSo loaiHoSo = loaiHoSoService.getOneLoaiHS(id);
        if (loaiHoSo==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loaiHoSo);
    }

    @PutMapping("/loaihosos/{id}")
    public ResponseEntity<LoaiHoSo> updateLoaiHoSo(@PathVariable Integer id, @Valid @RequestBody LoaiHoSo
            loaiHoSoDetail) {
        LoaiHoSo loaiHoSo = loaiHoSoService.getOneLoaiHS(id);
        if(loaiHoSo == null) {
            return ResponseEntity.notFound().build();
        }
        loaiHoSoDetail.setId(id);
        LoaiHoSo created = loaiHoSoService.capNhatLoaiHs(loaiHoSoDetail);
        return ResponseEntity.ok(created); // return 200
    }

    @DeleteMapping("/loaihosos/{id}")
    public ResponseEntity<?> deleteLoaiHs(@PathVariable Integer id) {
        LoaiHoSo loaiHoSo = loaiHoSoService.getOneLoaiHS(id);
        if(loaiHoSo == null) {
            return ResponseEntity.notFound().build();
        }
        loaiHoSoService.deleteLoaiHs(loaiHoSo);
        return ResponseEntity.noContent().build(); // return 204
    }
}
