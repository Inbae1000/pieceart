package com.example.pieceart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Table(name="works_image")
@AllArgsConstructor
@NoArgsConstructor
public class Image {
//    @JsonIgnore 적으면 해당 정보 안넘어감
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2)
    private String type;

    @NotNull
    @Column
    private String fileName;

    @NotNull
    @Column
    private String serverImageUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="works_id")
    private Works works;
}
