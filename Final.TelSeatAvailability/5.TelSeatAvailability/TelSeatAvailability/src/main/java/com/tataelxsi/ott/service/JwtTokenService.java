package com.tataelxsi.ott.service;

import com.tataelxsi.ott.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    private static String secret="user_Secret_Key";

    public String GenerateJwtToken(User user) {

        long millisec = System.currentTimeMillis();
        Date issuedate = new Date(millisec);

        Claims claims = Jwts.claims().setIssuedAt(issuedate).setIssuer(String.valueOf
                (user.getEmailId()));

        claims.put("name", user.getEmpName());
        claims.put("emp_id", user.getEmpId());

        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();

        return token;
    }

    public boolean VerifyJwtToken(String validate) throws Exception {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(validate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
