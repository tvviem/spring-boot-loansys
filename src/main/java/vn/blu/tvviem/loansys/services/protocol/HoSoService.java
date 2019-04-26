package vn.blu.tvviem.loansys.services.protocol;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.blu.tvviem.loansys.exceptions.EntityNotFoundException;
import vn.blu.tvviem.loansys.models.hoso.HoSo;
import vn.blu.tvviem.loansys.web.dto.HoSoNhanVienRoleDto;

import java.util.Date;

public interface HoSoService {

    /** Quyen nhan vien gom cac thao tac */
    // Tao ho so voi cac thong tin thuoc quyen nhan vien
    HoSo taoHoSo(HoSoNhanVienRoleDto hoSoNhanVienRoleDto) throws EntityNotFoundException;
    // Phan trang cac ho so
    Page<HoSo> getAllHoSo(Pageable pageable);
    // Lay thong tin ho so
    HoSo findOneHoSoById(Long hoSoId);
    // Xoa mot ho so
    boolean deleteHoSo(Long hoSoId);
    // Cap nhat thong tin ho so voi vai tro nhan vien
    HoSo updateHoSo(Long hoSoId, HoSoNhanVienRoleDto hoSoNhanVienRoleDto);
    // Tim thong tin ho so theo ngay tao
    Iterable<HoSo> getHoSosByNgayTao(Date ngayTao);


}
