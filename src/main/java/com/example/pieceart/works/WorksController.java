package com.example.pieceart.works;


import com.example.pieceart.notice.NoticeDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/works")
public class WorksController {

    private final WorksService worksService;

    // 작품 페이지 처리
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getWorks(@RequestParam(value="page", required = false, defaultValue = "1") int page){
        int totalCount = worksService.findAll().size();

        List<WorksDTO> works = worksService.findByPage(page);

        Map<String, Object> map = new HashMap<>();
        if(works.isEmpty()){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }

        map.put("count", works.size());
        map.put("totalCount", totalCount);
        map.put("data", works);

        return ResponseEntity.ok().body(map);
    }


    // 작품 아이디로 검색
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getWorkById(@PathVariable("id") Long id){
        WorksDTO works = worksService.findById(id);
        Map<String, Object> map = new HashMap<>();

        if(works == null){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        map.put("data", works);

        return ResponseEntity.ok().body(map);
    }

    // 작가 이름으로 검색
    @GetMapping("/a")
    public ResponseEntity<Map<String, Object>> getWorksByArtistName(@RequestParam(value = "name", required = false)String name){
        List<WorksDTO> works = worksService.findByArtistName(name);
        Map<String, Object> map = new HashMap<>();

        if(name.equals("") ){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        map.put("data", works);

        return ResponseEntity.ok().body(map);
    }


    // 작품 이름으로 검색
    @GetMapping("/b")
    public ResponseEntity<Map<String, Object>> getWorksByWorkName(@RequestParam(value = "name", required = false)String name){
        List<WorksDTO> works = worksService.findByWorksName(name);
        Map<String, Object> map = new HashMap<>();

        if(name.equals("") ){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        map.put("data", works);

        return ResponseEntity.ok().body(map);
    }

    // 작품 등록
    @PostMapping
    public ResponseEntity<Map<String, Object>> createWorks(@Valid @RequestBody WorksDTO worksDTO){
        WorksDTO created = worksService.create(worksDTO);
        Map<String, Object> map = new HashMap<>();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        map.put("data", created);
        return ResponseEntity.created(location).body(map);
    }


    // 작품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteWorks(@PathVariable("id") Long id){
        Map<String, Object> map = new HashMap<>();
        if(worksService.findById(id) == null){
            map.put("errorCode", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        worksService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 작품 수정
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateWorks(@PathVariable("id") Long id, @Valid @RequestBody WorksDTO updated){
        WorksDTO updatedWorks = worksService.update(id, updated);
        Map<String, Object> map = new HashMap<>();
        if(updatedWorks == null){
            return createWorks(updated);
        } else{
            map.put("data", updatedWorks);
            return ResponseEntity.ok().body(map);
        }
    }

}
