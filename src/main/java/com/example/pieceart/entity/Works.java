package com.example.pieceart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@Table(name="works")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "artist")
public class Works {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=100)
    private String name;

    @Column(nullable = false, length=50)
    private String category;

    @Column(nullable = false, length=4000)
    private String description;

    @Column(length=4)
    private String createdYear; //2020

    @Column
    private LocalDate auctionStartDate;

    @Column
    private LocalDate auctionEndDate;

    @PositiveOrZero
    @Column
    private Integer initialPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "works", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new HashSet<>();

    @Column(length = 200)
    private String size;

    public void setName(String name) { this.name = name;}
    public void setCategory(String category) {this.category = category;}
    public void setDescription(String description) {this.description = description;}
    public void setCreatedYear(String createdYear) {this.createdYear = createdYear;}



}