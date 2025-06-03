package com.example.authen_session.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {// 원스퍼리퀘스트필터를 상속받음. 모어 엑션 추상메서드 완성
    private final JwtUtil jwtUtil;
    @Override
    // 이 필터는 프론트가 요청을 보낼때 제일 먼저 받는 필터
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {//필터 만들어 꽂기, 토큰이 받았거나 없거나 할거임
        String token = request.getHeader("Authorization");// 어서라이제이션에 헤더에 토큰(어서라이제이션이여도 꼭 토큰이 아닐수도 있다)
        if (token == null || !token.startsWith("Bearer ")) {// 거기에 토큰이 없다,널이거나 있는데 스타투윗 비어러이면(jwt토큰은 관례적으로 이걸로 시작)-
            filterChain.doFilter(request, response); // 이걸로 요청을 다음번 필터한테 넘긴다. 이 필터에서는 검증을 하는 필터
            return;
        }
        token = token.split(" ")[1];// 토큰 떼고 뒤에것만 가지고 인증

        try{
            this.jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e){ // 익셉션 발생하면 유효기간이 지난것이므로 받아주면 안됨.
            response.getWriter().write("token expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);// 토큰이 만료되었고 인증이 안되었다.
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        User user= new User(username,"",authorities);//유저 디테일스의 유저

        Authentication auth=  new UsernamePasswordAuthenticationToken(user,null,authorities);// 유저네임패스워드어스토큰(유저객체, 크레덴셜, 롤 정보 리스트들어감) 두 번 들어간것 같으나
        SecurityContextHolder.getContext().setAuthentication(auth);// 시큐리티 콘텍스트 홀더는 잠시 인증자의 정보를 갖고 있는데 jwt 방식에서도 갖고 잇어야 하므로 여기다가 세팅하여야 함. 이렇게 틀에 맞춰야 함.
        filterChain.doFilter(request,response); //토큰 만료되면 넘기고, 안만료면 유저이름이랑 정보 가져와서 지역인증정보에 세팅해줌. 콘텍스트는 리스폰스가 넘어갈때까지는 유효하다. 끝났으니깐 요청을 다음 필터에게 넘기면서 끝남.
    }
}
