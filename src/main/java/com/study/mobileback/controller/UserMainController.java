package com.study.mobileback.controller;

import com.study.mobileback.dto.UserDto;
import com.study.mobileback.model.entity.User;
import com.study.mobileback.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserMainController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping(path = "/v1/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody UserDto userDto) {
        return userRegistrationService.registration(userDto);
    }

    @PostMapping(path = "/v1/recovery")
    public ResponseEntity<?> recoveryPassword(@RequestBody UserDto userDto) {
        return userRegistrationService.recovery(userDto);
    }

    @GetMapping(path = "/v1/email")
    public String checkEmail(@RequestParam String email) {
        User existUser = userRegistrationService.getExistUser(email);
        if (existUser == null) {
            return "Ура! E-mail свободен! Идем дальше...";
        }
        return "Такой e-mail занят :(";
    }
}
