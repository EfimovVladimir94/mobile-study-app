package com.study.mobileback.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "animal")
public class Animal {

    @Id
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String city;
    private Integer age;
    //https://stackoverflow.com/questions/54500/storing-images-in-postgresql
    private byte[] image;
    private String breed;
    private String description;
    private String gender;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

}
