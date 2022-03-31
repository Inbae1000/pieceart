package com.example.pieceart.image;


import com.example.pieceart.promotion.PromotionDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Log4j2
@RestController
public class ImageController {

    private ImageService imageService;

    @Value("C:\\upload")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<ImageDTO>> uploadFile(MultipartFile[] uploadFiles, ImageDTO imageDTO){
        List<ImageDTO> imageDTOList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles){
            //실제 파일 이름 IE 나 Edge는 전체 경로가 들어옴
            if(uploadFile.getContentType().startsWith("image") == false){
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName:" + fileName);

            String folderPath = makeFolder();

            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);
            try{
                uploadFile.transferTo(savePath);
                imageDTOList.add(new ImageDTO());
            }catch(IOException e){
                e.printStackTrace();
            }
        } // end for
        return new ResponseEntity<>(imageDTOList, HttpStatus.OK);
    }
    private String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder---------
        File uploadPathFolder = new File(uploadPath, folderPath);
        if(uploadPathFolder.exists() == false){
            uploadPathFolder.mkdir();
        }
        return folderPath;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){
        ResponseEntity<byte[]> result = null;

        try{
            String srcFilename = URLDecoder.decode(fileName, "UTF-8");
            log.info("fileName : " + srcFilename);
            File file = new File(uploadPath + File.separatorChar + srcFilename);
            log.info("file " + file);
            HttpHeaders header = new HttpHeaders();
            //MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
