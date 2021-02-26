package com.study.mobileback.dto;

import com.study.mobileback.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {

    private Long id;
    private String name;
    private String city;
    private Integer age;
    //TODO: добавить изображение
    private String breed;
    private String description;
    private User user;
}
