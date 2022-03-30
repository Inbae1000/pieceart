package com.example.pieceart.promotion;

import com.example.pieceart.entity.Promotion;
import com.example.pieceart.entity.Works;


public interface PromotionService {

    PromotionDTO findById(Long id);
    PromotionDTO create(PromotionDTO promotionDTO);
    void delete(Long id);
    PromotionDTO update(Long id, PromotionDTO promotionDTO);

    default Promotion dtoToEntity(PromotionDTO dto){

        // json 으로 파싱할때 타입이 long으로 안넘어와서 오류가 남
    Works works = Works.builder().id(dto.getWorksId()).build();
        Promotion promotion = Promotion.builder()
                .eventTitle(dto.getEventTitle())
                .eventDescription(dto.getEventDescription())
                .works(works)
                .build();
        return promotion;
    }

    default PromotionDTO entityToDTO(Promotion promotion){

        PromotionDTO promotionDTO = PromotionDTO.builder()
                .id(promotion.getId())
                .eventTitle(promotion.getEventTitle())
                .eventDescription(promotion.getEventDescription())
                .works(promotion.getWorks())
                .build();
        return promotionDTO;
    }

}
