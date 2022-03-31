package com.example.pieceart.image;

import com.example.pieceart.entity.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2


public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public ImageDTO create(ImageDTO imageDTO){
        Image created  = imageRepository.save(dtoToEntity(imageDTO));
        return entityToDTO(created);
    }

}
