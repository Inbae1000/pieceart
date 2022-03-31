package com.example.pieceart.image;

import com.example.pieceart.entity.Works;
import lombok.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long id;
    private String type;
    private String fileName;
    private String uuid;
    private String folderPath;
    private Works works;

    public String getImageURL(){
        try{
            return URLEncoder.encode(folderPath+"/" +uuid+"_" +fileName, "UTF-8");
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

}