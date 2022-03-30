package com.example.pieceart.member;

import com.example.pieceart.entity.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String name;
    private String password;
    private boolean isGoogle;
    private LocalDate createdDate;
    private String role;
}