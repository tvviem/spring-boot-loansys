package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.repositories.KhachHangRepo;
import vn.blu.tvviem.loansys.services.protocol.KhachHangService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepo khachHangRepo;

    // Them khach hang vao CSDL
    public KhachHang saveKhachHang(KhachHang k) {
        return khachHangRepo.save(k);
    }
    // Lay ve danh sach khach hang
    public List<KhachHang> getAllKhachHang() {
        return khachHangRepo.findAll();
    }

    // Tra ve 1 khach hang voi tham so la id
    public KhachHang getOneKhachHang(Long id) {
        /*Optional<KhachHang> kh = khachHangRepo.findById(id);
        if (!kh.isPresent())
            return new KhachHang();
        return kh.get();*/

        // return khachHangRepo.findOne(id);
        //return khachHangRepo.getOne(id);
        return khachHangRepo.findById(id).orElse(null);
    }

    // Xoa khach hang
    public void deleteKhachHang(KhachHang khachHang) {
        khachHangRepo.delete(khachHang);
    }

}