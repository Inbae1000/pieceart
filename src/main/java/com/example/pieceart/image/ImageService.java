package com.example.pieceart.image;

import com.example.pieceart.entity.Image;
import com.example.pieceart.entity.Promotion;
import com.example.pieceart.promotion.PromotionDTO;


public interface ImageService {
    ImageDTO create(ImageDTO imageDTO);


    default Image dtoToEntity(ImageDTO dto){

        Image image = Image.builder()
                .imageName(dto.getImageURL())
                .type(dto.getType())
                .build();
        return image;
    }
    default ImageDTO entityToDTO(Image image){

        ImageDTO imageDTO = ImageDTO.builder()
                .id(image.getId())
                .build();
        return imageDTO;
    }
}
