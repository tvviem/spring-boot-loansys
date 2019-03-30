package vn.blu.tvviem.loansys.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class TaiSanDto {
    private Long khachHangId;
    private Integer loaiTaiSanId;
    private List<ChiTietThongTin> cacThongTin;
    // private MultipartFile[] multipartFiles;
}
