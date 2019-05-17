package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.hoso.HinhThucLai;
import vn.blu.tvviem.loansys.repositories.HinhThucLaiRepo;
import vn.blu.tvviem.loansys.services.protocol.HinhThucLaiService;

@Service
public class HinhThucLaiServiceImpl implements HinhThucLaiService {
    @Autowired
    HinhThucLaiRepo hinhThucLaiRepo;

    @Override
    public HinhThucLai luuHinhThuc(HinhThucLai hinhThucLai) {
        /*HinhThucLai hinhThuc = hinhThucLaiRepo.findFirstByTenHinhThuc(hinhThucLai.getTenHinhThuc().trim()).orElse(null);
        if(hinhThuc == null) {
        }
        return null;*/
        return hinhThucLaiRepo.save(hinhThucLai); // INSERT because hinhThuc unique
    }

    @Override
    public HinhThucLai capNhatHinhThuc(HinhThucLai hinhThucLai) {
        return hinhThucLaiRepo.save(hinhThucLai); // updated
    }

    @Override
    public Iterable<HinhThucLai> getCacHinhThucLai() {
        return hinhThucLaiRepo.findAll();
    }

    @Override
    public HinhThucLai getHinhThucLai(Integer id) {
        return hinhThucLaiRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteHinhThuc(HinhThucLai hinhThucLai) {
        hinhThucLaiRepo.delete(hinhThucLai);
    }

    @Override
    public HinhThucLai getByTenHinhThuc(String tenHinhThuc) {
        return hinhThucLaiRepo.findFirstByTenHinhThuc(tenHinhThuc).orElse(null);
    }
}
