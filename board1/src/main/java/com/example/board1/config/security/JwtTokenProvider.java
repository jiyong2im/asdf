//package com.example.board1.config.security;
//
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//
//
//@Component
//@RequiredArgsConstructor
//public class JwtTokenProvider {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
//    private  final UserDetailsService userDetailsService;
//
//    @Value("${springboot.jwt.secret}")
//    private String secretKey = "secretKey";
//    private  final long toKenValidMillisecond = 1000L * 60 * 60;
//
//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public String createToken(String userUid, List<String> roles) {
//        Claims claims = Jwts.claims().setSubject(userUid);
//        claims.put("roles", roles);
//        Date now = new Date();
//
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + toKenValidMillisecond))
//                .signWith(SignatureAlgorithm.ES256, secretKey)
//                .compact();
//
//        return token;
//    }
//
//    public Authentication getAuthentication(String token){
//        UserDetails userDetails =  userDetailsService.loadUserByUsername(this.getUser.getUsername(token));
//        userDetails.getUsername();
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
//
//
//
//}
