package com.example.authen_session.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;// 세션방식에서 처리하던애라 원래 꼽혀있던 애이다.

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {// 두 필터 인터널 바로 다음에 오는 필터, jwt로그인필터는 위에 있던 애랑 똑같은 틀을 가지면서 위 필터를 덮어씌우면서 꼽으려고 클래스를 만든다.
    // 애는 빨간줄 안뜨니 추상메서드는 없으나
    private final AuthenticationManager authenticationManager;// 인증매니저가 필요함
    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);// 유저네임을 가져옴
        String password = obtainPassword(request);// 패스워드를 가져옴

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password, null);// 요청해서 가져온 유저네임과 패스워드를 넣어주고
        return this.authenticationManager.authenticate(authRequest);
    }// 여기까지가 세션방식이 자동으로 해주던 것임. 세션방식에서는 얘가 꼽혀있고 자동으로 하던 일임, 이 안에서 유저네임만 뽑아서 전달해주게 됨.
    // 여기서 서비스의 유저서비스에 넘겨서 db로 체크하고 맞고 틀리냐 에 따라 어덴티케이트가 성공함수, 실패함수를 호출하는거 까지가 어덴티케이트가 담당하는 일, 이제 꼽아줘야 함
    // 요청해서 들어온 것을 확인하고 서비스에서 db에서 가져온것과 비교함.

    @Override// 부모의 부모안에 들어잇고 권한은 바꿔도 데서 프로텍티드를 퍼블릭으로
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException { // 석세스 핸들러와 같음, db에서 가져온것과 요청에서 한게 같을때 함
        //겟 프린시펄이 리턴하는 자료형을 써줌
        UserDetails userDetails = (UserDetails) authResult.getPrincipal(); // 겟 프린시펄은 리턴형이 오브젝트로 최상형임
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> grantedAuthorities =userDetails.getAuthorities();// 겟 오소리티는 컬렉션으로 커서대면 나옴// ? extends GrantedAuthority 그랜 오소리티이거나 그랜 오소리티 상속받은 자식 아무나 된다고 해놓음. 상위로 해놓음
        //컬렉션 형이여서 이터레이터 만들어서 한개씩 접근해야 함
        Iterator<? extends GrantedAuthority> iterator = grantedAuthorities.iterator();// 컬렉션에서 이터레이터를 가져온 후, 넥스트를 써서 가져오기 시작
        GrantedAuthority grantedAuthority = iterator.next();// 계속 넥스트 하면 다음것을 가져오게 됨. 어짜피 한개만 넣어놔서 한개만 가져오면 댐
        String role= grantedAuthority.getAuthority(); // 이거 해서 롤 정보를 가져옴.

        Map<String,Object> responseData= new HashMap<String, Object>();
        responseData.put("role",role);// 유저네임말고 롤만 넘김. 프론트에게 롤 넘길 피룡가 있으면 함
        responseData.put("result","로그인성공");// 이런 식으로 넣을 수 잇음

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(responseData);
        // 제대로 있다는것 확인후 이제 토큰 만드는게 중요

        String token = this. jwtUtil.createToken(username,role,60*10*1000L);// 롱형은 뒤에 L을 붙여줘야하며, 하나만 붙여주면 댐
        response.addHeader("Authorization", "Bearer " + token);// 여기서 헤더용 이름을 붙이기. 이 두줄이 제일 핵심임, 토큰만들고 헤더에 넣어준 것, 세션 핸들러랑 비슷하고 유일하게 이게 다름, 관례로 Au..로 시작함

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonMessage);


    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String,Object> responseData= new HashMap<>();
        responseData.put("result","로긴실패");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(responseData);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonMessage);

    }
}
