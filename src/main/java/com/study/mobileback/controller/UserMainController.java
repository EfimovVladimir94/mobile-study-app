package com.study.mobileback.controller;

import com.study.mobileback.dto.UserDto;
import com.study.mobileback.entity.User;
import com.study.mobileback.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserMainController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping(path = "/v1/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody UserDto userDto) {
        boolean registration = userRegistrationService.registration(userDto);
        return new ResponseEntity<>(registration, HttpStatus.OK);
    }

    @PostMapping(path = "/v1/authorization")
    public ResponseEntity<?> authorization(@RequestBody UserDto userDto) {
        boolean authorizationResult = userRegistrationService.authorization(userDto);
        return new ResponseEntity<>(authorizationResult, HttpStatus.OK);
    }

    @PostMapping(path = "/v1/recovery")
    public ResponseEntity<?> recoveryPassword(@RequestBody UserDto userDto) {
        boolean recoveryResult = userRegistrationService.recovery(userDto);
        return new ResponseEntity<>(recoveryResult, HttpStatus.OK);
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
