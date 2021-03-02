package com.study.mobileback.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String city;
    private Integer age;
    //TODO: добавить изображение
    //https://stackoverflow.com/questions/54500/storing-images-in-postgresql
    private byte[] image;
    private String breed;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
