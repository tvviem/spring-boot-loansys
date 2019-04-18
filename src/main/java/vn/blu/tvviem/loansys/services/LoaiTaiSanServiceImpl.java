package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;
import vn.blu.tvviem.loansys.repositories.LoaiTaiSanRepo;
import vn.blu.tvviem.loansys.repositories.ThongTinRepo;
import vn.blu.tvviem.loansys.services.protocol.LoaiTaiSanService;

import java.util.List;

@Service
public class LoaiTaiSanServiceImpl implements LoaiTaiSanService {

    @Autowired
    private LoaiTaiSanRepo loaiTaiSanRepo;
    @Autowired
    private ThongTinRepo thongTinRepo;

    // Tao loai tai san
    @Override
    public LoaiTaiSan saveLoaiTs(LoaiTaiSan loaiTaiSan) {
        return loaiTaiSanRepo.save(loaiTaiSan);
    }

    // Lay danh sach loai tai san
    @Override
    public List<LoaiTaiSan> getAllLoaiTaiSan() {
        return loaiTaiSanRepo.findAll();
    }
    // Lay ve mot loai tai san
    @Override
    public LoaiTaiSan getOneLoaiTs(Integer id) {
        return loaiTaiSanRepo.findById(id).orElse(null);
    }
    // Xoa loai tai san
    @Override
    public void deleteLoaiTaiSan(LoaiTaiSan loaiTaiSan) {
        loaiTaiSanRepo.delete(loaiTaiSan);
    }

    @Override
    public LoaiTaiSan getLoaiTsByTenLoai(String tenLoai) {
        return loaiTaiSanRepo.findByTenLoai(tenLoai).orElse(null);
    }
}
