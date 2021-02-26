package com.study.mobileback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "email may not be blank")
    private String email;

    @NotBlank(message = "password may not be blank")
    private String password;
}
