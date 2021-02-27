package com.study.mobileback.service;


import com.study.mobileback.Util.PasswordUtils;
import com.study.mobileback.dto.UserDto;
import com.study.mobileback.entity.User;
import com.study.mobileback.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.study.mobileback.Util.DataMapper.userDtoToUser;

@Service
@Slf4j
public class UserRegistrationService {

    @Autowired
    private UserRepository repository;
    @Value("${app.cypher.salt.value}")
    private String salt;

    public boolean registration(UserDto dto) {
        User user = userDtoToUser(dto);
        User existUser = getExistUser(dto.getEmail());
        if (existUser == null) {
            encodePass(user);
            repository.save(user);
            log.info("User id: {} save success", user.getId());
            return true;
        }
        log.info("User id: {} save failure. User exist", user.getId());
        return false;
    }

    public boolean authorization(UserDto userDto) {
        User existUser = getExistUser(userDto.getEmail());
        if (existUser != null) {
            boolean authorize = PasswordUtils.verifyUserPassword(userDto.getPassword(), existUser.getPassword(), salt);
            log.info("User id: {} authorization : {}", existUser.getId(), authorize);
            return authorize;
        }
        return false;
    }

    public boolean recovery(UserDto userDto) {
        User existUser = getExistUser(userDto.getEmail());
        if (existUser != null) {
            User user = userDtoToUser(userDto);
            encodePass(user);
            repository.update(user.getEmail(), user.getPassword());
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

    private void encodePass(User user) {
        String result = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
        user.setPassword(result);
    }

}
