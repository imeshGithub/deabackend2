package com.petwelness1.deabackend2.service;


import com.petwelness1.deabackend2.dto.LoginRequest;
import com.petwelness1.deabackend2.dto.LoginResponse;
import com.petwelness1.deabackend2.entity.Role;
import com.petwelness1.deabackend2.entity.User;
import com.petwelness1.deabackend2.repo.UserRepo;
import com.petwelness1.deabackend2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =userRepo.findById(username).get();

       if(user!=null){
           return new org.springframework.security.core.userdetails.User(
                   user.getUsername(),
                   user.getPassword(),
                   getAuthority(user)
           );
       }else{
           throw new UsernameNotFoundException("User not found with username:"+username);
       }
    }
    private Set getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities =new HashSet<>();
        for(Role role:user.getRole()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));

        }
        return authorities;

    }


    public LoginResponse createJwtToken(LoginRequest loginRequest) throws Exception{
      String username =loginRequest.getUsername();
      String password=loginRequest.getPassword();

        authenticate(username,password );
        UserDetails userDetails = loadUserByUsername(username);
        String newGeneratedToken= jwtUtil.generateToken(userDetails);
        User user =userRepo.findById(username).get();

        LoginResponse loginResponse =new LoginResponse(
                user,
                newGeneratedToken
        );
        return loginResponse;

    }
    private void authenticate(String username,String password ) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);

        }
    }
}
