package com.example.demo.security;



import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUtil {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;


    public String generateToken(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("warehouse")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey())
                .claim("roles", userDetails.getAuthorities())
                .compact();

    }

    private Key getSignKey() {
        byte[] decode = Decoders.BASE64.decode("1234567891011131123456789101113112345678910111311234567891011131");
        return Keys.hmacShaKeyFor(decode);
    }

    public boolean isValid(String token) {
        Claims claims = getClaims(token);
        return true;
    }

    public String getUsername(String token){

        Claims claims = getClaims(token);
        return claims.getSubject();

    }
    public List<Role> grantedAuthorities(String token){

        Claims claims = getClaims(token);
        String username = claims.getSubject();
        User byUsername = userRepository.findByUsername(username);
        return  byUsername.getRoles();

////        String roles = claims.get("roles", String.class);
////        System.out.println();
////        String[] split = roles.split(",");
////        List<SimpleGrantedAuthority> list = Arrays.stream(split).map(SimpleGrantedAuthority::new).toList();
////        return list;
//
//        List<LinkedHashMap<String, Object>> rolesMap = (List<LinkedHashMap<String, Object>>) claims.get("roles");
//        List<String> roles = rolesMap.stream()
//                .map(roleMap -> (String) roleMap.get("authority"))
//                .toList();
//        List<GrantedAuthority> collect = roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        System.out.println(collect);
//        return collect;
    }
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateRefreshToken(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("funn_app")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))
                .signWith(getSignKey())
                .claim("roles", userDetails.getAuthorities())
                .compact();

    }
}
