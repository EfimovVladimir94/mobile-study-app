package com.study.mobileback.service;


import com.study.mobileback.Util.PasswordUtils;
import com.study.mobileback.dto.UserDto;
import com.study.mobileback.entity.User;
import com.study.mobileback.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.study.mobileback.Util.DataMapper.userDtoToUser;

@Service
@Slf4j
public class UserRegistrationService {

    @Autowired
    private UserRepository repository;
    private String salt = "wZOAmOsv2Q7oL66FKwKzb7U2t4IZCQ";

    public boolean registration(UserDto dto) {
        if (validation(dto)) {
            User user = userDtoToUser(dto);
            User existUser = getExistUser(dto.getEmail());
            if (existUser == null) {
                encodePass(user);
                repository.save(user);
                return true;
            }
        }
        return false;
    }

    private static boolean validation (UserDto dto) {
        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            return false;
        }
        return true;
    }

    public boolean authorization(UserDto userDto) {
        User existUser = getExistUser(userDto.getEmail());
        if (existUser != null) {
            return PasswordUtils.verifyUserPassword(userDto.getPassword(), existUser.getPassword(), salt);
        }
        return false;
    }

    public boolean recovery(UserDto userDto) {
        User existUser = getExistUser(userDto.getEmail());
        if (existUser != null) {
            User user = userDtoToUser(userDto);
            encodePass(user);
            repository.update(user.getEmail(), user.getPassword());
            return true;
        }
        return false;
    }

    public User getExistUser(String email) {
        Optional<User> exist = repository.findByEmail(email);
        return exist.orElse(null);
    }

    private void encodePass(User user) {
        String result = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
        user.setPassword(result);
    }

}
