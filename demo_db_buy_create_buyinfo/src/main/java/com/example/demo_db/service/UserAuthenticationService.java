package com.example.demo_db.service;

import com.example.demo_db.data.dao.AuthenDAO;
import com.example.demo_db.data.dto.AuthenDTO;
import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.data.repository.AuthenRepository;
import com.example.demo_db.exception.AuthenticationErrorException;
import com.example.demo_db.exception.DuplicateIdException;
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
public class UserAuthenticationService implements UserDetailsService {
    private final AuthenRepository authenRepository;
    private final AuthenDAO authenDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenEntity authenEntity = this.authenRepository.findById(username).orElse(null);
        if (authenEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority((authenEntity.getRole())));
        return new User(authenEntity.getUsername(),authenEntity.getPassword(),grantedAuthorities);
    }

    public AuthenDTO joinAdmin(AuthenDTO authenDTO) {
        AuthenEntity authenEntity= this.authenDAO.joinAdmin(authenDTO.getUsername(), authenDTO.getPassword());
        if(!(authenDTO.getConfirm().equals("1234"))){
            throw new AuthenticationErrorException("인증번호 오류입니다..");
        }
        if(authenEntity!=null){
                AuthenDTO SaveAuthenDTO= AuthenDTO.builder()
                        .username(authenEntity.getUsername())
                        .password(authenEntity.getPassword())
                        .build();
                return SaveAuthenDTO;
        }
        throw new DuplicateIdException("해당 아이디는 중복입니다.");
    }
}
