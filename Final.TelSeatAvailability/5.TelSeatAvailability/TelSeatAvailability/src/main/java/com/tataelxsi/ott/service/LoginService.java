package com.tataelxsi.ott.service;


import com.tataelxsi.ott.entity.Seat;
import com.tataelxsi.ott.entity.User;
import com.tataelxsi.ott.repository.SeatRepository;
import com.tataelxsi.ott.repository.UserRepository;
import com.tataelxsi.ott.response.ForgotResponse;
import com.tataelxsi.ott.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginService {

    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,15}";

//    User user = new User();

    List<Seat> seat=new ArrayList<Seat>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    RegisterService registerService;

    @Autowired
    JwtTokenService jwtTokenService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder() ;

    public LoginResponse loginUser(String emailId, String password)
    {
        Optional<User> registeredUserCheck = userRepository.getUserByEmail(emailId);

        if(!registeredUserCheck.isPresent())
        {
            return new LoginResponse(401, "User doesn't exist. Kindly Register.", null,null);
        }

        String encodedPassword = registeredUserCheck.get().getPassword();

        if(	bCryptPasswordEncoder.matches(password, encodedPassword))
        {
            try {
                String token = jwtTokenService.GenerateJwtToken(registeredUserCheck.get());

                return new LoginResponse(200,"Login successful",token,registeredUserCheck.get());
            }catch (Exception e){
                return new LoginResponse(401,"failed to generate JWT token",null,null);
            }
        }
        return new LoginResponse(401, "Please enter valid mailId and password", null,null);

    }


    //forget Password
    public ForgotResponse forgotPassword(String emailId, String password) {
        Optional<User> updateUser = userRepository.getUserByEmail(emailId);

        if (!updateUser.isPresent()) {
            return new ForgotResponse(401, "User doesn't exist");
        }

        if (updateUser != null) {
            if(validate(password)) {
                updateUser.get().setPassword(bCryptPasswordEncoder.encode(password));
                userRepository.save(updateUser.get());
            }else
            {
                return new ForgotResponse(400, "invalid password pattern");
            }
        }
        return new ForgotResponse(201, "New password updated successfully");
    }

    public boolean validate(String password) {
        Pattern pattern=Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher=pattern.matcher(password);
        if(matcher.matches()) {
            return true;
        }else {
            return false;
        }
    }

}