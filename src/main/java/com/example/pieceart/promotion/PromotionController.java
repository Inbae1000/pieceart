package com.example.pieceart.promotion;


import com.example.pieceart.entity.Promotion;
import com.example.pieceart.works.WorksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

    private final PromotionService promotionService;
    // 프로모션 정보 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getWorkById(@PathVariable("id") Long id){
        PromotionDTO promotionDTO = promotionService.findById(id);
        Map<String, Object> map = new HashMap<>();

        if(promotionDTO == null){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        map.put("eventTitle", promotionDTO.getEventTitle());
        map.put("eventDescription", promotionDTO.getEventDescription());
        map.put("worksName", promotionDTO.getWorks().getName());
        map.put("artist", promotionDTO.getWorks().getArtist().getName());
        map.put("worksImg", promotionDTO.getWorks().getImages());

        return ResponseEntity.ok().body(map);
    }

    // 프로모션 등록
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPromotion(@Valid @RequestBody PromotionDTO promotionDTO){
        PromotionDTO created = promotionService.create(promotionDTO);
        Map<String, Object> map = new HashMap<>();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        map.put("data", created);
        return ResponseEntity.created(location).body(map);
    }

    // 프로모션 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePromotion(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        if(promotionService.findById(id) == null){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        promotionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 프로모션 수정
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePromotion(@PathVariable("id") Long id, @Valid @RequestBody PromotionDTO updated){
        PromotionDTO updatedPromotion = promotionService.update(id, updated);
        Map<String, Object> map = new HashMap<>();
        if(updatedPromotion == null){
            return createPromotion(updated);
        } else{
            map.put("data", updatedPromotion);
            return ResponseEntity.ok().body(map);
        }
    }


}
