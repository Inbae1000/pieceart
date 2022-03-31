package com.example.pieceart.works;

import com.example.pieceart.entity.Image;
import com.example.pieceart.entity.Notice;
import com.example.pieceart.entity.Works;
import com.example.pieceart.notice.NoticeDTO;
import com.example.pieceart.promotion.PromotionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Request;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2

public class WorksServiceImpl implements WorksService {

    private final WorksRepository worksRepository;
    private final PromotionRepository promotionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<WorksDTO> findByPage(int page){
        Iterable<Works> works = worksRepository.findAll(PageRequest.of(page-1, 10, Sort.by("id")));
        List<WorksDTO> list = new ArrayList<>();
        works.forEach(work -> {
            list.add(entityToDTO(work));
        });
        return list;
    }
    

    @Override
    @Transactional(readOnly = true)
    public List<WorksDTO> findAll(){
        Iterable<Works> works = worksRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<WorksDTO> list = new ArrayList<>();
        works.forEach(work -> {
            list.add(entityToDTO(work));
        });
        return list;
    }

    @Override
    @Transactional
    public WorksDTO findById(Long id){
        Optional<Works> works = worksRepository.findById(id);
            if(works.isPresent()){
                Works iWorks = works.get();
                return entityToDTO(worksRepository.save(iWorks));
            }
        return null;
    }

    @Override
    @Transactional
    public List<WorksDTO> findByArtistName(String name){
        List<Works> works = worksRepository.findWorksByArtist(name);
        List<WorksDTO> list = new ArrayList<>();

        works.forEach(work->{
            WorksDTO worksDTO = entityToDTO(work);
            list.add(worksDTO);
        });
        return list;
    }

    @Override
    @Transactional
    public List<WorksDTO> findByWorksName(String name){
        List<Works> works = worksRepository.findByWorks(name);
        List<WorksDTO> list = new ArrayList<>();

        works.forEach(work -> {
            WorksDTO worksDTO = entityToDTO(work);
            list.add(worksDTO);
        });
        return list;
    }

    @Override
    @Transactional
    public WorksDTO create(WorksDTO worksDTO){
        Works created  = worksRepository.save(dtoToEntity(worksDTO));
        return entityToDTO(created);
    }


    @Transactional
    @Override
    public void delete(Long id){
        worksRepository.deleteById(id);
    }

    @Override
    @Transactional
    public WorksDTO update(Long id, WorksDTO worksDTO){
        return worksRepository.findById(id)
                .map(works->{
                    works.setName(worksDTO.getName());
                    works.setCategory(worksDTO.getCategory());
                    works.setCreatedYear(worksDTO.getCreatedYear());
                    works.setDescription(worksDTO.getDescription());
                    return entityToDTO(worksRepository.save(works));
                }).get();
    }

}
