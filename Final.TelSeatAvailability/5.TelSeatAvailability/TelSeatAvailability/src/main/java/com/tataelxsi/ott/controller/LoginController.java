package com.tataelxsi.ott.controller;

import com.tataelxsi.ott.entity.User;
import com.tataelxsi.ott.response.ForgotResponse;
import com.tataelxsi.ott.response.LoginResponse;
import com.tataelxsi.ott.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping("/login")
    LoginResponse doLogin(@RequestBody User user)
    {
        try{
            return loginService.loginUser(user.getEmailId(), user.getPassword());
        } catch (Exception e){
            return null;
        }
    }

    @PostMapping("/forgot-password")
    ForgotResponse forgotPassword(@RequestBody User user)
    {
        try{
            return loginService.forgotPassword(user.getEmailId(), user.getPassword());
        } catch (Exception e){
            return null;
        }
    }

}
