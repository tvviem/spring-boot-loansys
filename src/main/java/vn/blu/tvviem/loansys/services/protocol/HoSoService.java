package vn.blu.tvviem.loansys.services.protocol;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.blu.tvviem.loansys.models.hoso.HoSo;
import vn.blu.tvviem.loansys.web.dto.HoSoGiamDocRoleDto;
import vn.blu.tvviem.loansys.web.dto.HoSoNhanVienRoleDto;
import vn.blu.tvviem.loansys.web.dto.HoSoThuNganRoleDto;

import java.math.BigDecimal;
import java.util.Date;

public interface HoSoService {

    /** Quyen nhan vien gom cac thao tac */
    // Tao ho so voi cac thong tin thuoc quyen nhan vien
    HoSo taoHoSo(HoSoNhanVienRoleDto hoSoNhanVienRoleDto);
    // Cap nhat ho so boi nhanVien role
    HoSo updateHoSoById(Long hoSoId, HoSoNhanVienRoleDto hoSoNhanVienRoleDto);
    // Cap nhat thong tin khac cua ho so doi voi quyen giam doc
    HoSo pheDuyetHoSo(Long hoSoId, HoSoGiamDocRoleDto hoSoGiamDocRoleDto);
    // Ho so duoc cap nhat boi thu ngan
    HoSo thuNganHoSo(Long hoSoId, HoSoThuNganRoleDto hoSoThuNganRoleDto);

    // Phan trang cac ho so
    Page<HoSo> getAllHoSo(Pageable pageable);
    // Lay thong tin ho so
    HoSo findOneHoSoById(Long hoSoId);
    // Xoa mot ho so
    boolean deleteHoSo(Long hoSoId);
    // Cap nhat thong tin ho so voi vai tro nhan vien
    //HoSo updateHoSo(Long hoSoId, HoSoNhanVienRoleDto hoSoNhanVienRoleDto);
    // Tim thong tin ho so theo ngay tao
    Iterable<HoSo> getHoSosByNgayTao(Date ngayTao);
    // Tra ve tong gia tri duoc phep vay cua mot tai san trong cac ho so con no
    BigDecimal getTongDuocPhepVayOfTaiSanInHoSo(Long taiSanId);

    // Xac dinh tong so ngay nop hien tai cua ho so
    //int getTongSoNgayDaNop(Long hoSoId);

    // Lay cac ho so con no

    // ...
}
