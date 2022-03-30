package com.example.pieceart.file;

import com.example.pieceart.entity.Notice;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private Long id;

    private String fileName;

    private Notice notice;

}
