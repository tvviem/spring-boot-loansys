package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;
import vn.blu.tvviem.loansys.repositories.LoaiTaiSanRepo;
import vn.blu.tvviem.loansys.services.protocol.LoaiTaiSanService;

import java.util.List;

@Service
public class LoaiTaiSanServiceImpl implements LoaiTaiSanService {

    @Autowired
    private LoaiTaiSanRepo loaiTaiSanRepo;

    // Tao loai tai san
    public LoaiTaiSan saveLoaiTs(LoaiTaiSan loaiTaiSan) {
        return loaiTaiSanRepo.save(loaiTaiSan);
    }
    // Lay danh sach loai tai san
    public List<LoaiTaiSan> getAllLoaiTaiSan() {
        return loaiTaiSanRepo.findAll();
    }
    // Lay ve mot loai tai san
    public LoaiTaiSan getOneLoaiTs(Integer id) {
        /*Optional<KhachHang> kh = khachHangRepo.findById(id);
        if (!kh.isPresent())
            return new KhachHang();
        return kh.get();*/

        // return khachHangRepo.findOne(id);
        //return khachHangRepo.getOne(id);
        return loaiTaiSanRepo.findById(id).orElse(null);
    }
    // Xoa loai tai san
    public void deleteLoaiTaiSan(LoaiTaiSan loaiTaiSan) {
        loaiTaiSanRepo.delete(loaiTaiSan);
    }
}
