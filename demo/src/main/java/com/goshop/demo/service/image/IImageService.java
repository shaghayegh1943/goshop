package com.goshop.demo.service.image;

import com.goshop.demo.dto.ImageDto;
import com.goshop.demo.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IImageService {
    Image findById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImage(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long imageId) throws IOException, SQLException;
}
