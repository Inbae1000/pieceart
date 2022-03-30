//package com.example.pieceart.image;
//
//
//import com.example.pieceart.entity.Image;
//import lombok.Value;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class ImageStore {
//    @Value("${file.dir}/")
//    private String fileDirPath;
//
//    private String extractExt(String imageUrl){
//        int idx = imageUrl.lastIndexOf(".");
//        String ext = imageUrl.substring(idx);
//        return ext;
//    }
//    private String createStoreImageUrl(String imageUrl){
//        String uuid = UUID.randomUUID().toString();
//        String ext = extractExt(imageUrl);
//        String serverImageUrl = uuid + ext;
//
//        return serverImageUrl;
//    }
//
//    public String createPath(String serverImageUrl){
//        return fileDirPath+serverImageUrl;
//    }
//    public Image storeImage(MultipartFile multipartFile) throws IOException{
//        if(multipartFile.isEmpty()){
//            return null;
//        }
//        String imageUrl = multipartFile.getOriginalFilename();
//        String serverImageUrl = createStoreImageUrl(imageUrl);
//        multipartFile.transferTo(new File(createPath(serverImageUrl)));
//
//        return Image.builder()
//                .imageUrl(imageUrl)
//                .serverImageUrl(serverImageUrl)
//                .build();
//    }
//    public List<Image> storeImages(List<MultipartFile> multipartFiles) throws IOException{
//        List<Image> images = new ArrayList<>();
//        for(MultipartFile multipartFile : multipartFiles){
//            if(!multipartFile.isEmpty()){
//                images.add(storeImage(multipartFile));
//            }
//        }
//        return images;
//    }
//}
