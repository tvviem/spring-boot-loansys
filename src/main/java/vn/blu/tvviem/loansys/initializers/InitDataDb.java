package vn.blu.tvviem.loansys.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.blu.tvviem.loansys.models.baomat.Role;
import vn.blu.tvviem.loansys.models.baomat.RoleName;
import vn.blu.tvviem.loansys.models.baomat.User;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;
import vn.blu.tvviem.loansys.repositories.KhachHangRepo;
import vn.blu.tvviem.loansys.repositories.LoaiTaiSanRepo;
import vn.blu.tvviem.loansys.repositories.RoleRepository;
import vn.blu.tvviem.loansys.repositories.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitDataDb implements ApplicationListener<ContextRefreshedEvent> {

    private LoaiTaiSanRepo loaiTaiSanRepo;
    private KhachHangRepo khachHangRepo;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public InitDataDb(LoaiTaiSanRepo loaiTaiSanRepo, KhachHangRepo khachHangRepo, UserRepository userRepository, RoleRepository roleRepository) {
        this.loaiTaiSanRepo = loaiTaiSanRepo;
        this.khachHangRepo = khachHangRepo;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Tao 2 loai tai san và thông tin tương ứng
        if(loaiTaiSanRepo.findByTenLoai("Xe máy").orElse(null)==null) {
            LoaiTaiSan loaiTaiSanXeMay = new LoaiTaiSan("Xe máy", "Loại phương tiện di chuyển cơ bản");
            loaiTaiSanXeMay.addThongTin(new ThongTin("Số đăng ký"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Số máy"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Số khung"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Số loại"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Dung tích"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Nhãn hiệu"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Màu"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Nơi đăng ký"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Ngày đăng ký"));
            loaiTaiSanXeMay.addThongTin(new ThongTin("Biển số xe"));
            loaiTaiSanRepo.save(loaiTaiSanXeMay);
        }
        if(loaiTaiSanRepo.findByTenLoai("Đất đai").orElse(null) == null) {
            LoaiTaiSan loaiTaiSanDatDai = new LoaiTaiSan("Đất đai", "Loài tài sản đất do nhà nước cấp quyền sử dụng");
            loaiTaiSanDatDai.addThongTin(new ThongTin("Tờ bản đồ số"));
            loaiTaiSanDatDai.addThongTin(new ThongTin("Thửa đất số"));
            loaiTaiSanDatDai.addThongTin(new ThongTin("Diện tích"));
            loaiTaiSanDatDai.addThongTin(new ThongTin("Hình thức sử dụng"));
            loaiTaiSanDatDai.addThongTin(new ThongTin("Mục đích sử dụng"));
            loaiTaiSanDatDai.addThongTin(new ThongTin("Thời hạn sử dụng"));
            loaiTaiSanDatDai.addThongTin(new ThongTin("Nguồn gốc sử dụng"));

            loaiTaiSanRepo.save(loaiTaiSanDatDai);
        }

        // Tạo thông tin khách hàng
        try {
            if(khachHangRepo.findFirstBySoCmnd("381222111").orElse(null)==null) {
                KhachHang khachHang1 = new KhachHang("Quách Duy Nam", true, "Phường 3, Sóc Trăng", "0912233445",
                        "381222111", new SimpleDateFormat("dd/MM/yyyy").parse("19/5/2009"),
                        "Sóc Trăng", "img/pic1.png");
                khachHangRepo.save(khachHang1);
            }
            if(khachHangRepo.findFirstBySoCmnd("381299999").orElse(null)==null) {
                KhachHang khachHang2 = new KhachHang("Hồ Diệp Mai Quế", false, "Phường 8, Sóc Trăng", "0918899445",
                        "381299999", new SimpleDateFormat("dd/MM/yyyy").parse("8/12/1995"),
                        "Sóc Trăng", "img/pic2.png");
                khachHangRepo.save(khachHang2);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create Role
        Role roleAdmin = createRoleIfNotFound(RoleName.ROLE_ADMIN, "Quản lý việc sử dụng hệ thống của người dùng, " +
                "trạng thái băng thông, khả năng sẵn dùng của hệ thống");
        Role roleGiamDoc = createRoleIfNotFound(RoleName.ROLE_GIAMDOC, "Giám đốc công ty, duyệt mức vay của hồ sơ");
        Role roleTinDung = createRoleIfNotFound(RoleName.ROLE_TINDUNG, "Quản lý khách hàng và hồ sơ");
        Role roleThuNgan = createRoleIfNotFound(RoleName.ROLE_THUNGAN, "Thu tiền từ nhân viên thu lãi");
        Role roleXemLai = createRoleIfNotFound(RoleName.ROLE_XEMLAI, "Người dùng có thể xem thông tin nộp lãi của " +
                "một hồ sơ");

        User user = createUserIfNotFound("ththe1987", "P@ssword87",
                    "ththe87@gmail.com", "Thế", "Trương Hữu",
                        new HashSet<>(Arrays.asList(roleAdmin)));
        User nhanVien = createUserIfNotFound("tvviem87", "123456", "tvviem@gmail.com",
                    "Viêm", "Triệu Vĩnh", new HashSet<>(Collections.singletonList(roleTinDung)));
    }

    @Transactional
    Role createRoleIfNotFound(RoleName name, String description) {
        Role role = roleRepository.findByRoleName(name).orElse(null);
        if (role == null) {
            role = new Role(name, description);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String username, final String password, final String email,
                              final String firstName, final String lastName, final Set<Role> roles) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setRoles(roles);
            user = userRepository.save(user);
        }
        return user;
    }

    // Create loaiTaiSan, thongTin,
    @Transactional
    void createData() {

    }
}
