package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.repositories.KhachHangRepo;
import vn.blu.tvviem.loansys.services.protocol.KhachHangService;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    private final KhachHangRepo khachHangRepo;

    @Autowired
    public KhachHangServiceImpl(KhachHangRepo khachHangRepo) {
        this.khachHangRepo = khachHangRepo;
    }

    // Them khach hang vao CSDL
    public KhachHang saveKhachHang(KhachHang k) {
        return khachHangRepo.save(k);
    }

    /**
     * @param soCmnd Cung cap chuoi so CMND
     * @apiNote Tim chinh xac 1 khach hang khi biet so CMND
     * @return KhachHang object or null
     * */
    @Override
    public KhachHang getKhachHangBySoCmnd(String soCmnd) {
        return khachHangRepo.findFirstBySoCmnd(soCmnd).orElse(null);
    }

    // Tra ve 1 khach hang voi tham so la id
    @Override
    public KhachHang getOneKhachHang(Long id) {
        return khachHangRepo.findById(id).orElse(null);
    }

    @Override
    public Page<KhachHang> getAllKhachHang(Pageable pageable) {
        return khachHangRepo.findAll(pageable);
    }

    // Xoa khach hang
    @Override
    public void deleteKhachHang(KhachHang khachHang) {
        khachHangRepo.delete(khachHang);
    }

    // Lay ve danh sach khach hang
    // using Iterable for optimize lazy loading
    /*@Override
    public Page<KhachHang> getAllKhachHang(Pageable pageable) {
        return khachHangRepo.findAll(pageable);
    }
*/
    @Override
    public Page<KhachHang> getKhachHangHavePartHoTen(String partOfHoTen, Pageable pageable) {
        // pageable = PageRequest.of(0, 2, Sort.by("hoTen").ascending());

        return khachHangRepo.findByHoTenContaining(partOfHoTen, pageable);
    }
}