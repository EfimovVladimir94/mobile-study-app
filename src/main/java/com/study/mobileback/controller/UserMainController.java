package com.study.mobileback.controller;

import com.study.mobileback.dto.UserDto;
import com.study.mobileback.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserMainController {

    private final UserRegistrationService userRegistrationService;

//    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
//    public ResponseEntity<?> getAccount() {
//        List<Account> accounts = repository.findAll();
//        log.debug("accounts : {} ", accounts);
//        return new ResponseEntity<>(accounts, HttpStatus.OK);
//    }

    @PostMapping(path = "/v1/registration")
    public ResponseEntity<?> registration(@RequestBody UserDto userDto) {
        boolean registration = userRegistrationService.registration(userDto);
        log.debug("registration complete : {} ", registration);
        return new ResponseEntity<>(registration, HttpStatus.OK);
    }

    @PostMapping(path = "/v1/authorization")
    public ResponseEntity<?> authorization(@RequestBody UserDto userDto) {
        boolean authorizationResult = userRegistrationService.authorization(userDto);
        log.debug("authorization : {} ", authorizationResult);
        return new ResponseEntity<>(authorizationResult, HttpStatus.OK);
    }

    @PostMapping(path = "/v1/recovery")
    public ResponseEntity<?> recoveryPassword(@RequestBody UserDto userDto) {
        boolean recoveryResult = userRegistrationService.recovery(userDto);
        log.debug("authorization : {} ", recoveryResult);
        return new ResponseEntity<>(recoveryResult, HttpStatus.OK);
    }

}
