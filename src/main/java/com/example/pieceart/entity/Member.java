package com.example.pieceart.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Table(name="member")
@Getter
@ToString(exclude = "notices, roleSet")
@AllArgsConstructor
@NoArgsConstructor //기본 생성자
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "name is required")
    @Column(nullable = false, unique = true, length=50)
    private String name;

    @NotNull(message = "password is required")
    @Column(nullable = false)
    private String password;

    private boolean isGoogle;

    @Column(name="created_date")
    private LocalDate createdDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    @OneToMany(mappedBy= "member", cascade = CascadeType.ALL)
    private Set<Notice> notices = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

    @PrePersist //entity 저장 전 실행
    public void onPrePersist(){
        this.createdDate = LocalDate.now();
    }
}
