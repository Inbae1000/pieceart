package com.example.pieceart.artist;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getArtistById(@PathVariable("id") Long id){
        ArtistDTO artistDTO = artistService.findById(id);
        Map<String, Object> map = new HashMap<>();

        if(artistDTO == null){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        map.put("data", artistDTO);

        return ResponseEntity.ok().body(map);
    }



    // 작가 등록
    @PostMapping
    public ResponseEntity<Map<String, Object>> createArtist(@Valid @RequestBody ArtistDTO artistDTO){
        ArtistDTO created = artistService.create(artistDTO);
        Map<String, Object> map = new HashMap<>();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        map.put("data", created);
        return ResponseEntity.created(location).body(map);
    }


    // 작가 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteArtist(@PathVariable("id") Long id){
        Map<String, Object> map = new HashMap<>();
        if(artistService.findById(id) == null){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 작가 수정
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateArtist(@PathVariable("id") Long id, @Valid @RequestBody ArtistDTO updated){
        ArtistDTO updatedWorks = artistService.update(id, updated);
        Map<String, Object> map = new HashMap<>();
        if(updatedWorks == null){
            return createArtist(updated);
        } else{
            map.put("data", updatedWorks);
            return ResponseEntity.ok().body(map);
        }
    }


}
