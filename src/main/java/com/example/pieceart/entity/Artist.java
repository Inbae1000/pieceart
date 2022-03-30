package com.example.pieceart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Table(name="artist")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "works")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4000)
    private String achieve;

    @Column(length = 4000)
    private String degree;

    @Column(nullable = false, length=50)
    private String name;

    @Column(length = 400)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "artist")
    private List<Works> works =new ArrayList<>();

    public void setAchieve(String achieve) {this.achieve = achieve;}
    public void setDegree(String degree) {this.degree = degree;}
    public void setName(String name) { this.name = name;}
    public void setDescription(String description) {this.description = description;}

}
