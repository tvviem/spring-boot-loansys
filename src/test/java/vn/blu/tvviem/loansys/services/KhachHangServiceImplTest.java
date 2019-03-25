package vn.blu.tvviem.loansys.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.repositories.KhachHangRepo;
import vn.blu.tvviem.loansys.services.protocol.KhachHangService;

import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class KhachHangServiceImplTest {

    @InjectMocks
    private KhachHangService khachHangServiceImpl;

    @Mock
    private KhachHangRepo khachHangRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveKhachHang() {
        KhachHang khachHang = new KhachHang();
        khachHang.setId(3L);
        khachHang.setHoTen("Trieu Vinh Viem");
        khachHang.setDiaChi("Bac Lieu");
        khachHang.setNgayCapCmnd(new Date());
        khachHang.setDuongDanHinh("imgs/abc.jpg");
        khachHang.setNoiCap("CA Bac Lieu");
        khachHang.setSoDienThoai("0987654312");

        when(khachHangRepo.save(any(KhachHang.class))).thenReturn(khachHang);
        KhachHang khachHangActual = khachHangServiceImpl.saveKhachHang(khachHang);

        Assertions.assertEquals(new Long(3), khachHangActual.getId(), "ID can nhat quan");
    }

    @Test
    @Disabled
    void getAllKhachHang() {
    }

    @Test
    void getOneKhachHang() {

        KhachHang khachHang = new KhachHang();
        khachHang.setId(3L);
        khachHang.setHoTen("Trieu Vinh Viem");
        khachHang.setDiaChi("Bac Lieu");
        khachHang.setNgayCapCmnd(new Date());
        khachHang.setDuongDanHinh("imgs/abc.jpg");
        khachHang.setNoiCap("CA Bac Lieu");
        khachHang.setSoDienThoai("0987654312");

        when(khachHangRepo.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(khachHang));

        KhachHang khachHangActual =  khachHangServiceImpl.getOneKhachHang(3L);

        Assertions.assertNotNull(khachHangActual, "Khach hang can phai co gia tri");

        String expectedHoTen = "Trieu Vinh Viem";
        String actualHoTen = khachHangActual.getHoTen();
        Assertions.assertEquals(expectedHoTen, actualHoTen, () -> "Gia tri mong doi la <" + expectedHoTen + ">, " +
                "nhung ket qua la: " + actualHoTen);
    }

    @Test
    @Disabled
    void deleteKhachHang() {
    }

}