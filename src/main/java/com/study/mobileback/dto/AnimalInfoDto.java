package com.study.mobileback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
    @NotBlank
    @NotEmpty
    private String breed;
    private String gender;
    private String ownerName;
    @NotBlank
    @NotEmpty
    private String description;
    private LocalDate registrationDate;
    private byte[] image;
}
