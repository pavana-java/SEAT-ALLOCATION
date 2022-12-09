package com.tataelxsi.ott.controller;

import com.tataelxsi.ott.entity.User;
import com.tataelxsi.ott.response.RegisterResponse;
import com.tataelxsi.ott.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public RegisterResponse doRegister(@RequestBody User users)

    {
        try{
            return  registerService.register(users);
        } catch (Exception e){
            return null;
        }
    }


}
