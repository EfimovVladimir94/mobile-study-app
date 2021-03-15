package com.study.mobileback.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "animal")
public class Animal {

    @Id
    @Column(name = "animal_id")
    private Long id;
    private String name;
    private String city;
    private Integer age;
    private String breed;
    private String description;
    private String gender;
    private String ownerName;
    private LocalDate registrationDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(mappedBy = "animal")
    private Image image;
}
