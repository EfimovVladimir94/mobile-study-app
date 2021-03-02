package com.study.mobileback.controller;


import com.study.mobileback.config.JwtTokenUtil;
import com.study.mobileback.dto.UserDto;
import com.study.mobileback.model.JwtResponse;
import com.study.mobileback.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/v1/authorization", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthorizationToken(@RequestBody UserDto userDto) throws Exception {

        authenticate(userDto.getEmail(), userDto.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userDto.getEmail());
        JwtResponse authorizationUser = jwtTokenUtil.createAuthorizationUser(userDetails);
        return ResponseEntity.ok(new JwtResponse(authorizationUser.getJwttoken(), authorizationUser.getUserId()));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}