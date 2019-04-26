package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.blu.tvviem.loansys.models.hoso.HoSo;
import vn.blu.tvviem.loansys.services.protocol.HoSoService;
import vn.blu.tvviem.loansys.web.dto.HoSoNhanVienRoleDto;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1")
public class HoSoRest {
    @Autowired
    private HoSoService hoSoService;

    // Tao tai san cho khach hang (hinh anh cap nhat sau)
    @Transactional
    @PostMapping("/hosos")
    public ResponseEntity<?> createTaiSan(@Valid @RequestBody HoSoNhanVienRoleDto hoSoNhanVienRoleDto) {
        HoSo hoSoCreated = hoSoService.taoHoSo(hoSoNhanVienRoleDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hoSoCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // return 201 and location in header
    }

}
