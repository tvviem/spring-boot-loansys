package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.blu.tvviem.loansys.exceptions.FileStorageException;
import vn.blu.tvviem.loansys.exceptions.MyFileNotFoundException;
import vn.blu.tvviem.loansys.exceptions.types.ResourceNotFoundException;
import vn.blu.tvviem.loansys.models.taisan.HinhTaiSan;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;
import vn.blu.tvviem.loansys.repositories.HinhTaiSanRepo;
import vn.blu.tvviem.loansys.repositories.TaiSanRepo;

import java.io.IOException;
import java.util.List;

@Service
public class HinhTaiSanService {

    @Autowired
    private HinhTaiSanRepo hinhTaiSanRepo;

    @Autowired
    private TaiSanRepo taiSanRepo;

    // Save picture to database
    public HinhTaiSan storeFile(Long taiSanId, MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            TaiSan taiSanSaved = taiSanRepo.findById(taiSanId).orElseThrow(()->new ResourceNotFoundException("NOT " +
                    "FOUND: " + taiSanId));

            HinhTaiSan hinhTaiSan = new HinhTaiSan(taiSanSaved, fileName, file.getContentType(), file.getBytes());

            return hinhTaiSanRepo.save(hinhTaiSan);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public HinhTaiSan getHinhTaiSan(Long hinhTaiSanId) {
        return hinhTaiSanRepo.findById(hinhTaiSanId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + hinhTaiSanId));
    }

    // Lay tap cac hinh anh cua tai san
    public List<HinhTaiSan> getHinhTaiSans(Long taiSanId) {
        TaiSan taiSanCreated = taiSanRepo.findById(taiSanId).orElseThrow(()->new ResourceNotFoundException("NOT " +
                "FOUND: " + taiSanId));

        return hinhTaiSanRepo.findHinhTaiSansByTaiSan(taiSanCreated);
    }
}
