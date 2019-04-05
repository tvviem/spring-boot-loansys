package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vn.blu.tvviem.loansys.exceptions.EntityNotFoundException;
import vn.blu.tvviem.loansys.exceptions.types.BadRequestException;
import vn.blu.tvviem.loansys.exceptions.types.ResourceNotFoundException;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;
import vn.blu.tvviem.loansys.services.protocol.LoaiTaiSanService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LoaiTaiSanRest {
    @Autowired
    private LoaiTaiSanService loaiTaiSanService;

    @PostMapping("/loaitaisans")
    public LoaiTaiSan createLoaiTs(@Valid @RequestBody LoaiTaiSan loaiTaiSan) {
        /*LoaiTaiSan created = loaiTaiSanService.saveLoaiTs(loaiTaiSan);
        return ResponseEntity.ok(created);*/
        return loaiTaiSanService.saveLoaiTs(loaiTaiSan);
    }

    @GetMapping("/loaitaisans")
    public List<LoaiTaiSan> getAll() {
        return loaiTaiSanService.getAllLoaiTaiSan();
    }

    @GetMapping("/loaitaisans/{id}")
    public ResponseEntity<LoaiTaiSan> getLoaiTs(@PathVariable Integer id) throws EntityNotFoundException {
        LoaiTaiSan loaiTaiSan = loaiTaiSanService.getOneLoaiTs(id);
        return ResponseEntity.ok(loaiTaiSan);
    }

    @PutMapping("/loaitaisans/{id}")
    public ResponseEntity<LoaiTaiSan> updateLoaiTaiSan(@PathVariable Integer id, @Valid @RequestBody LoaiTaiSan
            loaiTaiSanDetail) throws EntityNotFoundException {
        LoaiTaiSan loaiTaiSanExist = loaiTaiSanService.getOneLoaiTs(id);
        if(loaiTaiSanExist == null) {
            return ResponseEntity.notFound().build();
        }
        loaiTaiSanDetail.setId(id);
        LoaiTaiSan loaiTaiSanUpdated = loaiTaiSanService.saveLoaiTs(loaiTaiSanDetail);
        return ResponseEntity.ok(loaiTaiSanUpdated);
    }

    @DeleteMapping("/loaitaisans/{id}")
    public ResponseEntity<LoaiTaiSan> deleteLoaiTaiSan(@PathVariable Integer id) throws EntityNotFoundException {
        LoaiTaiSan loaiTaiSanExist = loaiTaiSanService.getOneLoaiTs(id);
        if(loaiTaiSanExist == null) {
            return ResponseEntity.notFound().build();
        }
        loaiTaiSanService.deleteLoaiTaiSan(loaiTaiSanExist);
        return ResponseEntity.ok().build();
    }
}
