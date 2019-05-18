package vn.blu.tvviem.loansys.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.blu.tvviem.loansys.models.hoso.GhiLai;
import vn.blu.tvviem.loansys.services.protocol.GhiLaiService;
import vn.blu.tvviem.loansys.web.dto.GhiLaiDto;

@RestController
@RequestMapping("/v1")
// @RequiredArgsConstructor
public class GhiLaiRest {

    @Autowired
    private GhiLaiService ghiLaiService;

    @PostMapping("/noplai")
    public ResponseEntity<?> ghiLai(@RequestBody GhiLaiDto ghiLaiDto) {
        GhiLai ghiLaiOk =  ghiLaiService.nopLai(ghiLaiDto);
        if(ghiLaiOk!=null) {
            ResponseEntity.ok(ghiLaiOk);
        }
        return ResponseEntity.badRequest().build();
    }

}
