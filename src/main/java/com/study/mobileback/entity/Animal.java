package com.study.mobileback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String city;
    private Integer age;
    //TODO: добавить изображение
    private String breed;
    private String description;

}
