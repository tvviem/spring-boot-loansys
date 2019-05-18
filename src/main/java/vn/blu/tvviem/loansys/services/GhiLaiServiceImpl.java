package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.hoso.GhiLai;
import vn.blu.tvviem.loansys.models.hoso.HoSo;
import vn.blu.tvviem.loansys.repositories.GhiLaiRepo;
import vn.blu.tvviem.loansys.services.protocol.GhiLaiService;
import vn.blu.tvviem.loansys.services.protocol.HoSoService;
import vn.blu.tvviem.loansys.web.dto.GhiLaiDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;

@Service
public class GhiLaiServiceImpl implements GhiLaiService {

    @Autowired
    private HoSoService hoSoService;
    @Autowired
    private GhiLaiRepo ghiLaiRepo;

    @Override
    public GhiLai nopLai(GhiLaiDto ghiLaiDto) {
        HoSo hoSoSearch = hoSoService.findOneHoSoById(ghiLaiDto.getHoSoId());
        if(hoSoSearch!=null) {
            System.out.println("INSIDE - SEARCHED HOSO");

            if(hoSoSearch.getKyHan() < (tongNgayDaNop(hoSoSearch.getId()) + ghiLaiDto.getSoNgayNop())) {
                return null; // số ngày nộp vượt quá kỳ hạn
            }
            GhiLai ghiLaiTemp = new GhiLai();
            ghiLaiTemp.getId().setHoSo(hoSoSearch);
            ghiLaiTemp.setSoNgayNop(ghiLaiDto.getSoNgayNop());

            BigDecimal laiGocNopMoiNgay = xacDinhTienLaiGop(hoSoSearch.getGiamDocDuyet(), hoSoSearch.getMucLaiSuat(),
                    hoSoSearch.getKyHan(), hoSoSearch.getHinhThucLai().getId());
            // Tổng tiền thu trên số ngày nộp và lãi gộp mỗi ngày
            ghiLaiTemp.setSoTienNop(laiGocNopMoiNgay.multiply(new BigDecimal(ghiLaiDto.getSoNgayNop())));

            LocalDate localDate =  LocalDate.now().plusDays(ghiLaiDto.getSoNgayNop());
            Date ngayNhacLai = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            ghiLaiTemp.setNgayNhacNop(ngayNhacLai);
            ghiLaiRepo.save(ghiLaiTemp);
        }
        return null; // Ghi lãi thất bại
    }

    // Tiền cần phải nộp mỗi ngày dựa vào hình thức, mức lãi suất và tổng tiền vay
    @Override
    public BigDecimal xacDinhTienLaiGop(BigDecimal tongTienVay, int mucLaiSuat, int kyHan, int hinhThucLaiId) {
        // Tra ve so ngay cua nam hien tai
        int soNgayTrongNam = Year.of(LocalDate.now().getYear()).length();
        if(hinhThucLaiId==1) { // Lai gop chia deu
            double mucLaiTrenNgay =  (double)(mucLaiSuat/100)/soNgayTrongNam;
            double tongVay = tongTienVay.doubleValue();
            double tongLai = mucLaiTrenNgay*kyHan*tongVay;
            double laiThuMoiNgay = (tongLai+tongVay)/kyHan; // Lai + goc chia deu
            return new BigDecimal(laiThuMoiNgay);
        }
        return null;
    }

    @Override
    public Integer tongNgayDaNop(Long hoSoId) {
        return ghiLaiRepo.sumSoNgayNopByHoSoId(hoSoId);
    }
}
