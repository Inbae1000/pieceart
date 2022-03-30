package com.example.pieceart.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "works")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(nullable = false, length=100)
    private String eventTitle;

    @Column(length=4000)
    private String eventDescription;

    @OneToOne
    @JoinColumn(name="works_id")
    private Works works;

    public void setEventDescription(String eventDescription) {this.eventDescription = eventDescription;}
    public void setEventTitle(String eventTitle) {this.eventTitle = eventTitle;}

}