package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;
import vn.blu.tvviem.loansys.services.protocol.LoaiTaiSanService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1")
class LoaiTaiSanRest {
    private final LoaiTaiSanService loaiTaiSanService;

    @Autowired
    public LoaiTaiSanRest(LoaiTaiSanService loaiTaiSanService) {
        this.loaiTaiSanService = loaiTaiSanService;
    }

    // Only save loaiTaiSan info
    @PostMapping("/loaitaisans")
    public ResponseEntity createLoaiTs(@Valid @RequestBody LoaiTaiSan loaiTaiSan) {
        LoaiTaiSan createdObj = loaiTaiSanService.saveLoaiTs(loaiTaiSan);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdObj.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // return 201 created
    }

    // Save LoaiTaiSan and its ThongTin
    //public

    @GetMapping("/loaitaisans")
    public List<LoaiTaiSan> getAll() {
        return loaiTaiSanService.getAllLoaiTaiSan();
    }

    @GetMapping("/loaitaisans/{id}")
    public ResponseEntity<LoaiTaiSan> getLoaiTs(@PathVariable Integer id) {
        LoaiTaiSan loaiTaiSan = loaiTaiSanService.getOneLoaiTs(id);
        if (loaiTaiSan==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loaiTaiSan);
    }

    @PutMapping("/loaitaisans/{id}")
    public ResponseEntity<LoaiTaiSan> updateLoaiTaiSan(@PathVariable Integer id, @Valid @RequestBody LoaiTaiSan
            loaiTaiSanDetail) {
        LoaiTaiSan loaiTaiSanExist = loaiTaiSanService.getOneLoaiTs(id);
        if(loaiTaiSanExist == null) {
            return ResponseEntity.notFound().build();
        }
        loaiTaiSanDetail.setId(id);
        LoaiTaiSan created = loaiTaiSanService.saveLoaiTs(loaiTaiSanDetail); // cap nhat loai tai san
        return ResponseEntity.ok(created); // return 200
    }

    @DeleteMapping("/loaitaisans/{id}")
    public ResponseEntity<LoaiTaiSan> deleteLoaiTaiSan(@PathVariable Integer id) {
        LoaiTaiSan loaiTaiSanExist = loaiTaiSanService.getOneLoaiTs(id);
        if(loaiTaiSanExist == null) {
            return ResponseEntity.notFound().build();
        }
        loaiTaiSanService.deleteLoaiTaiSan(loaiTaiSanExist);
        return ResponseEntity.noContent().build(); // return 204
    }
}
