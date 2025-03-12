package com.goshop.demo.service.image;

import com.goshop.demo.dto.ImageDto;
import com.goshop.demo.exception.ResourceNotFoundException;
import com.goshop.demo.model.Image;
import com.goshop.demo.model.Product;
import com.goshop.demo.repository.ImageRepository;
import com.goshop.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductService productService;

    @Override
    public Image findById(Long id) {
        return imageRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("could not find Image with Id " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("could not find image with id " + id);
        });
    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDto = new ArrayList<>();
        for(MultipartFile file : files){
        try {
            Image image = new Image();
            image.setImgName(file.getOriginalFilename());
            image.setImgType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            image.setProduct(product);
            String BuildDownloadUrl = "api/v1/images/image/download/" ;
            String downloadUrl = BuildDownloadUrl + image.getId();

            image.setDownloadUrl(downloadUrl);
            Image savedImg = imageRepository.save(image);
            imageRepository.save(savedImg);

            ImageDto imageDto = new ImageDto();
            imageDto.setId(savedImg.getId());
            imageDto.setDownloadUrl(savedImg.getDownloadUrl());
            imageDto.setImgName(savedImg.getImgName());
            savedImageDto.add(imageDto);

        }catch (IOException | SQLException exception){
            throw new RuntimeException(exception.getMessage());
        }

            }

        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) throws IOException, SQLException {
        Image image = findById(imageId);
        image.setImgName(file.getOriginalFilename());
        image.setImgType(file.getContentType());
        image.setImage(new SerialBlob(file.getBytes()));
        imageRepository.save(image);
    }
}
