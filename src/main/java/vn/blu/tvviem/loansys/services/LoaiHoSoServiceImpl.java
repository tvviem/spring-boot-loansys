package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.hoso.LoaiHoSo;
import vn.blu.tvviem.loansys.repositories.LoaiHoSoRepo;
import vn.blu.tvviem.loansys.services.protocol.LoaiHoSoService;

@Service
public class LoaiHoSoServiceImpl implements LoaiHoSoService {

    private final LoaiHoSoRepo loaiHoSoRepo;

    @Autowired
    public LoaiHoSoServiceImpl(LoaiHoSoRepo loaiHoSoRepo) {
        this.loaiHoSoRepo = loaiHoSoRepo;
    }

    @Override
    public LoaiHoSo luuLoaiHs(LoaiHoSo loaiHoSo) {
        LoaiHoSo hoSoByTen = loaiHoSoRepo.findFirstByTenLoaiHoSo(loaiHoSo.getTenLoaiHoSo().trim()).orElse(null);
        if(hoSoByTen==null) {
            return loaiHoSoRepo.save(loaiHoSo); // INSERT because tenLoai unique
        }
        return null;
    }

    @Override
    public LoaiHoSo capNhatLoaiHs(LoaiHoSo loaiHoSoUpdated) {
        return loaiHoSoRepo.save(loaiHoSoUpdated); // UPDATED
    }

    @Override
    public Iterable<LoaiHoSo> getAllLoaiHs() {
        return loaiHoSoRepo.findAll();
    }

    @Override
    public LoaiHoSo getOneLoaiHS(Integer id) {
        return loaiHoSoRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteLoaiHs(LoaiHoSo loaiHoSo) {
        loaiHoSoRepo.delete(loaiHoSo);
    }

    @Override
    public LoaiHoSo getHoSoByTenLoai(String tenLoai) {
        return loaiHoSoRepo.findFirstByTenLoaiHoSo(tenLoai).orElse(null);
    }
}
