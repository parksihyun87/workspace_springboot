package com.example.authen_session.service;

import com.example.authen_session.data.entity.AuthenEntity;
import com.example.authen_session.data.repository.AuthenRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenEntity authenEntity = this.authenRepository.findById(username).orElse(null);// 오어 엘스 널.
        if(authenEntity == null){
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();// 롤에 해당되는 정보를 담을 수 있는 클래스다.(grantedauthority), 필요하다면 한 유저에게 여러 롤을 줄 수도 있다.
        grantedAuthorities.add(new SimpleGrantedAuthority(authenEntity.getRole())); //그랜티드어소리티를 상속받은게 심플 그랜티드어소리티임. 롤정보 객체를 만들어서 이 배열에 넣게 된다.
        return new User(authenEntity.getUsername(), authenEntity.getPassword(), grantedAuthorities);
    }// 유저 디테일스서비스, 이걸 해놓은면 인증매니저가 유저네임을 가져와서 알아서 다 비교함.
    // 유저 네임하고 패스워드를 바디에 넣어서 보냄. 다시 인증매니저가 받음. 백에서 온거랑, 프론트에서 온 패스워드도 갖고 있음, 인증매니저가 패스워드 인코더로 암호화도 시킴.
    // 패스워드를 비교해서 맞다고 되면 인증 객체를 만듬.
    // db에서 가져온것 하고
}
