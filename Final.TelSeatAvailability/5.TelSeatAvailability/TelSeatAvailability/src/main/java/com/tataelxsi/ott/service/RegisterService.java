package com.tataelxsi.ott.service;

import com.tataelxsi.ott.entity.User;
import com.tataelxsi.ott.repository.UserRepository;
import com.tataelxsi.ott.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;


    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,15}";


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegisterResponse register(User users) {

        int empID = users.getEmpId();
        List<User> findAllUser = userRepository.findAll();

        boolean presentAlready=false;
        for (User user:findAllUser) {
            if (user.getEmpId()==empID){
                presentAlready=true;
            }
        }
        if(!presentAlready){

            if(validate(users.getPassword())) {
                String encode = passwordEncoder.encode(users.getPassword());
                users.setPassword(encode);
                Optional<User> checkEmail = userRepository.getUserByEmail(users.getEmailId());
                if (!checkEmail.isPresent()){
                    User user = userRepository.save(users);
                    return new RegisterResponse(200, "SignedUp successfully", user);
                } else {
                    return new RegisterResponse(400, "Email Already present", null);
                }
            } else {
                return new RegisterResponse(400, "invalid password pattern", null);
            }
        }
        return new RegisterResponse(400,"User already present", null);
    }

    public  boolean validate(String password) {
        Pattern pattern=Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher=pattern.matcher(password);
        if(matcher.matches()) {
            return true;
        }else {
            return false;
        }
    }
}