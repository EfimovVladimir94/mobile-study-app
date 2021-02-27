package com.study.mobileback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalInfoDto {

    @NotNull
    private Long id;
    @NotBlank
    @NotEmpty
    private String name;
    @NotBlank
    @NotEmpty
    private String city;
    @NotNull
    private Integer age;
    //TODO: добавить изображение
    @NotBlank
    @NotEmpty
    private String breed;
    @NotBlank
    @NotEmpty
    private String description;
}