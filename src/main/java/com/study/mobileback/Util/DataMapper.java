package com.study.mobileback.Util;


import com.study.mobileback.dto.UserDto;
import com.study.mobileback.entity.User;

public class DataMapper {

    public static User registrationDtoToUser (UserDto userDto) {


        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

}
