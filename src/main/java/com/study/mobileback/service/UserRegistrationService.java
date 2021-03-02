package com.study.mobileback.service;


import com.study.mobileback.Util.PasswordUtils;
import com.study.mobileback.dto.UserDto;
import com.study.mobileback.model.entity.User;
import com.study.mobileback.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.study.mobileback.Util.DataMapper.userDtoToUser;

@Service
@Slf4j
public class UserRegistrationService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public ResponseEntity registration(UserDto dto) {

        User user = userDtoToUser(dto);
        User existUser = getExistUser(dto.getEmail());
        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(dto.getEmail());
            newUser.setPassword(bcryptEncoder.encode(dto.getPassword()));
            log.info("User id: {} save success", newUser.getId());
            repository.save(newUser);
            return new ResponseEntity(HttpStatus.OK);
        }
        log.info("User id: {} save failure.", user.getId());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public boolean recovery(UserDto userDto) {
        User existUser = getExistUser(userDto.getEmail());
        if (existUser != null) {
            User user = userDtoToUser(userDto);
            repository.update(user.getEmail(), bcryptEncoder.encode(user.getPassword()));
            log.info("User id: {} recovery success", existUser.getId());
            return true;
        }
        log.info("User doesn't exist");
        return false;
    }

    public User getExistUser(String email) {
        Optional<User> exist = repository.findByEmail(email);
        return exist.orElse(null);
    }

}
