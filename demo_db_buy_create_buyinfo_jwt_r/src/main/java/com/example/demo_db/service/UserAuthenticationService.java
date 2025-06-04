package com.example.demo_db.service;

import com.example.demo_db.data.dao.AuthenDAO;
import com.example.demo_db.data.dto.AuthenDTO;
import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.data.repository.AuthenRepository;
import com.example.demo_db.exception.AuthenticationErrorException;
import com.example.demo_db.exception.DuplicateIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {
    private final AuthenRepository authenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userNameLong = username;
        String role = userNameLong.split("_")[0];
        String userName = userNameLong.split("_")[1];

        AuthenEntity authenEntity = this.authenRepository.findById(userName).orElse(null);
        if (authenEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        if(role.equals("ADMIN") && !(authenEntity.getRole().equals("ROLE_ADMIN"))){
        throw new BadCredentialsException("일반 사용자의 권한에 맞지 않습니다.");
        }

//        if(!(role.equals("ADMIN")) && !(role.equals(authenEntity.getRole()))){
//            throw new BadCredentialsException("사용자의 권한에 맞지 않습니다.");
//        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority((authenEntity.getRole())));
        return new User(authenEntity.getUsername(),authenEntity.getPassword(),grantedAuthorities);
    }
}
