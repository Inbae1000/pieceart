package com.example.pieceart.promotion;


import com.example.pieceart.entity.Promotion;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor

public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;

    @Override
    @Transactional
    public PromotionDTO findById(Long id){
        Optional<Promotion> promotion = promotionRepository.findById(id);
        if(promotion.isPresent()){
            Promotion promotion1 = promotion.get();
            return entityToDTO(promotionRepository.save(promotion1));
        }
        return null;
    }

    @Override
    @Transactional
    public PromotionDTO create(PromotionDTO promotionDTO){
        Promotion created  = promotionRepository.save(dtoToEntity(promotionDTO));
        return entityToDTO(created);
    }


    public void delete(Long id){
        promotionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PromotionDTO update(Long id, PromotionDTO promotionDTO){
        return promotionRepository.findById(id)
                .map(promotion->{
                    promotion.setEventDescription(promotionDTO.getEventDescription());
                    promotion.setEventTitle(promotionDTO.getEventTitle());
                    return entityToDTO(promotionRepository.save(promotion));
                }).get();
    }
}
