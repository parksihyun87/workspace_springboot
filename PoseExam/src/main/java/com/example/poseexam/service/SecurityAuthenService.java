package com.example.poseexam.service;

import com.example.poseexam.data.entity.AuthenEntity;
import com.example.poseexam.data.repository.AuthenRepository;
import lombok.RequiredArgsConstructor;
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
public class SecurityAuthenService implements UserDetailsService {// 유디서
    private final AuthenRepository authenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenEntity authenEntity = this.authenRepository.findById(username).orElse(null);
        if (authenEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(authenEntity.getRole()));
        return new User(authenEntity.getUsername(), authenEntity.getPassword(), grantedAuthorities);
    }
}
