package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.baomat.User;
import vn.blu.tvviem.loansys.models.hoso.GhiLai;
import vn.blu.tvviem.loansys.models.hoso.HoSo;
import vn.blu.tvviem.loansys.repositories.GhiLaiRepo;
import vn.blu.tvviem.loansys.repositories.UserRepository;
import vn.blu.tvviem.loansys.services.protocol.GhiLaiService;
import vn.blu.tvviem.loansys.services.protocol.HoSoService;
import vn.blu.tvviem.loansys.web.dto.GhiLaiDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.Date;

@Service
public class GhiLaiServiceImpl implements GhiLaiService {

    @Autowired
    private HoSoService hoSoService;
    @Autowired
    private GhiLaiRepo ghiLaiRepo;
    @Autowired
    private UserRepository userRepository;

    @Override
    public GhiLai nopLai(GhiLaiDto ghiLaiDto) {
        HoSo hoSoSearch = hoSoService.findOneHoSoById(ghiLaiDto.getHoSoId());
        if(hoSoSearch!=null && hoSoSearch.isDaDuyet()) {
            if(hoSoSearch.getKyHan() < (tongNgayDaNop(hoSoSearch.getId()) + ghiLaiDto.getSoNgayNop())) {
                return null; // số ngày nộp vượt quá kỳ hạn (dư)
            }

            User nhanVien = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails)principal).getUsername();
                nhanVien = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Not found username[%s]", username)));
            }

            // Tìm xem có từng ghi lãi chưa
            GhiLai ghiLaiExist = ghiLaiRepo.findFirstByHoSo_IdOrderByNgayNhacNopDesc(hoSoSearch.getId()).orElse(null);

            GhiLai ghiLaiTempToSave = new GhiLai(hoSoSearch);

            ghiLaiTempToSave.setSoNgayNop(ghiLaiDto.getSoNgayNop());
            ghiLaiTempToSave.setCongTacVien(nhanVien);

            // Tính lãi + gốc của mỗi ngày
            BigDecimal laiGocNopMoiNgay = xacDinhTienLaiGop(hoSoSearch.getGiamDocDuyet(), hoSoSearch.getMucLaiSuat(),
                    hoSoSearch.getKyHan(), hoSoSearch.getHinhThucLai().getId());
            // Tổng tiền thu trên số ngày nộp và lãi gộp mỗi ngày
            BigDecimal soTienNop = laiGocNopMoiNgay.multiply(new BigDecimal(ghiLaiDto.getSoNgayNop()));
            ghiLaiTempToSave.setSoTienNop(soTienNop);
            BigDecimal tienHoaHong =
                    soTienNop.multiply(new BigDecimal((double)hoSoSearch.getMucHoaHong()/100)).setScale(0, RoundingMode.CEILING);
            ghiLaiTempToSave.setSoTienHoaHong(tienHoaHong);
            System.out.println("tienHoaHong="+tienHoaHong);

            /*LocalDate localDate =  LocalDate.now().plusDays(ghiLaiDto.getSoNgayNop());
            Date ngayNhacLai = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());*/
            if(ghiLaiExist==null) { // trường hợp ghi lãi lần đầu
                LocalDateTime localDateTime = LocalDateTime.now().plusDays(ghiLaiDto.getSoNgayNop());
                Date ngayNhacLai = Date.from(localDateTime.atZone(ZoneOffset.ofHours(0)).toInstant());
                //Date ngayNhacLai = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                ghiLaiTempToSave.setNgayNhacNop(ngayNhacLai);
            } else { // Trường hợp đã từng ghi lãi trước đó
                LocalDateTime localDateTime =
                        LocalDateTime.ofInstant(ghiLaiExist.getNgayNhacNop().toInstant(), ZoneOffset.ofHours(0)).plusDays(ghiLaiDto.getSoNgayNop());
                System.out.println("ngayNhacNop: " + ghiLaiExist.getNgayNhacNop());
                Date ngayNhacNopLaiTiepTheo = Date.from(localDateTime.atZone(ZoneOffset.ofHours(0)).toInstant());
                ghiLaiTempToSave.setNgayNhacNop(ngayNhacNopLaiTiepTheo);
            }

            return ghiLaiRepo.save(ghiLaiTempToSave);
        }
        return null; // Ghi lãi thất bại
    }

    // Tiền cần phải nộp mỗi ngày dựa vào hình thức, mức lãi suất và tổng tiền vay
    @Override
    public BigDecimal xacDinhTienLaiGop(BigDecimal tongTienVay, int mucLaiSuat, int kyHan, int hinhThucLaiId) {
        // Tra ve so ngay cua nam hien tai
        int soNgayTrongNam = Year.of(LocalDate.now().getYear()).length();
        System.out.println("soNgayTrongNam="+soNgayTrongNam);
        if(hinhThucLaiId==1) { // Lai gop chia deu
            BigDecimal mucLaiTrenNgay =  new BigDecimal(((double)mucLaiSuat/100)/soNgayTrongNam).setScale(6, RoundingMode.CEILING);
            System.out.println("mucLaiSuat="+mucLaiSuat);
            System.out.println("mucLaiTrenNgay="+mucLaiTrenNgay);

            System.out.println("tongTienVay="+tongTienVay.longValue());
            BigDecimal tongLai = mucLaiTrenNgay.multiply(tongTienVay).multiply(new BigDecimal(kyHan));
            System.out.println("tongLai="+tongLai+" tren ky han " + kyHan + " ngay");

            BigDecimal laiVaGoc = tongLai.add(tongTienVay);
            BigDecimal laiThuMoiNgay = laiVaGoc.divide(new BigDecimal(kyHan), 2, RoundingMode.CEILING);

            System.out.println("laiGopGocThuMoiNgay="+laiThuMoiNgay);
            return laiThuMoiNgay;
        }
        return null;
    }

    @Override
    public Integer tongNgayDaNop(Long hoSoId) {
        return ghiLaiRepo.sumSoNgayNopByHoSoId(hoSoId)==null ? 0 : ghiLaiRepo.sumSoNgayNopByHoSoId(hoSoId);
    }

    @Override
    public Iterable<GhiLai> layChiTietGhiLai(Long hoSoId) {
        return ghiLaiRepo.findByHoSo_IdOrderByNgayNhacNopDesc(hoSoId);
    }

    @Override
    public boolean xoaThongTinGhiLai(Long id) {
        if(ghiLaiRepo.existsById(id)) {
            ghiLaiRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
