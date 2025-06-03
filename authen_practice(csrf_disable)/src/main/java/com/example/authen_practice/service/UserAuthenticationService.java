package com.example.authen_practice.service;

import com.example.authen_practice.data.entity.AuthenEntity;
import com.example.authen_practice.data.repository.AuthenRepository;
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
public class UserAuthenticationService implements UserDetailsService {// 유저 디테일서비스를 구현 받아야 인증매니저가 유저네임을 가져와서 비교를 함
    private final AuthenRepository authenRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenEntity authenEntity= this.authenRepository.findById(username).orElse(null);
        if(authenEntity==null){
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();// 롤을 담으려고 가져온 클래스가 그랜드 오소리티, 심플은 이걸 상속받은거
        grantedAuthorities.add(new SimpleGrantedAuthority(authenEntity.getRole()));// 롤을 담아야 인증매니저가 롤로 로그인할때 쓰나?
        return new User(authenEntity.getUsername(),authenEntity.getPassword(),grantedAuthorities);
    }
}
