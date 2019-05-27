package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.blu.tvviem.loansys.models.hoso.HoSo;
import vn.blu.tvviem.loansys.services.protocol.HoSoService;
import vn.blu.tvviem.loansys.web.dto.HoSoGiamDocRoleDto;
import vn.blu.tvviem.loansys.web.dto.HoSoNhanVienRoleDto;
import vn.blu.tvviem.loansys.web.dto.HoSoThuNganRoleDto;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/v1")
public class HoSoRest {
    @Autowired
    private HoSoService hoSoService;

    // Tao tai san cho khach hang (hinh anh cap nhat sau)
    @Transactional
    @PostMapping("/hosos")
    public ResponseEntity<?> createHoSo(@Valid @RequestBody HoSoNhanVienRoleDto hoSoNhanVienRoleDto) {
        HoSo hoSoCreated = hoSoService.taoHoSo(hoSoNhanVienRoleDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(hoSoCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build(); // return 201 and location in header
    }

    @Transactional
    @PutMapping("/hosos/{hoSoId}")
    public ResponseEntity<?> updateHoSo(@PathVariable Long hoSoId,
                                               @Valid @RequestBody HoSoNhanVienRoleDto hoSoNhanVienRoleDto) {
        HoSo hoSoUpdated = hoSoService.updateHoSoById(hoSoId, hoSoNhanVienRoleDto);
        if(hoSoUpdated==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(hoSoUpdated);
    }

    // Duyet ho so va tong vay
    @Transactional
    @PutMapping("/hosos/{hoSoId}/giamdoc")
    public ResponseEntity<?> updateHoSoWithGiamDocRole(@PathVariable Long hoSoId,
                                                       @RequestBody HoSoGiamDocRoleDto hoSoGiamDocRoleDto) {
        HoSo hoSoUpdated = hoSoService.pheDuyetHoSo(hoSoId, hoSoGiamDocRoleDto);
        if (hoSoUpdated != null) {
            return ResponseEntity.ok(hoSoUpdated);
        }
        return ResponseEntity.badRequest().build();
    }

    // Cap nhat giai ngan hoac thu hoi no boi Thu Ngan
    @Transactional
    @PutMapping("/hosos/{hoSoId}/thungan")
    public ResponseEntity<?> updateHoSoWithThuNganRole(@PathVariable Long hoSoId,
                                                       @RequestBody HoSoThuNganRoleDto hoSoThuNganRoleDto) {
        HoSo hoSoUpdated = hoSoService.thuNganHoSo(hoSoId, hoSoThuNganRoleDto);
        if (hoSoUpdated != null) {
            return ResponseEntity.ok(hoSoUpdated);
        }
        return ResponseEntity.badRequest().build();
    }

    // Lay ve tat ca ho so co phan trang
    @GetMapping("/hosos")
    public Page<HoSo> getAllHoSos(Pageable pageable) {
        return hoSoService.getAllHoSo(pageable);
    }

    // Tra ve thong tin mot ho so
    @GetMapping("/hosos/{hoSoId}")
    public ResponseEntity<?> getOneHoSo(@PathVariable Long hoSoId) {
        HoSo hoSoFound = hoSoService.findOneHoSoById(hoSoId);
        if(hoSoFound==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hoSoFound);
    }

    // Xoa mot hoSo bang cach thiet lap deleted=1
    @DeleteMapping("/hosos/{id}")
    public ResponseEntity<?> deleteHoSo(@PathVariable Long id) {
        HoSo hoSoFound = hoSoService.findOneHoSoById(id);
        if(hoSoFound==null) {
            return ResponseEntity.notFound().build(); // 404
        }
        if(hoSoService.deleteHoSo(id)) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.badRequest().build(); // 400
        }
    }

    @GetMapping("/hosos/taisans/{taiSanId}/tongvay")
    public BigDecimal getTongVay(@PathVariable Long taiSanId) {
        return hoSoService.getTongDuocPhepVayOfTaiSanInHoSo(taiSanId);
    }
}
