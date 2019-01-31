package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.repositories.KhachHangRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepo khachHangRepo;

    // Lay ve danh sach khach hang
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> lsKhachHang = new ArrayList<>();
        khachHangRepo.findAll().forEach(lsKhachHang::add);
        return lsKhachHang;
    }

    // Them khach hang vao CSDL
    public void addKhachHang(KhachHang k) {
        khachHangRepo.save(k);
    }
}
