package com.example.authen_session.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component //* 구조를 추출하는 역할로 만들거임 시크릿키도 만듬
public class JwtUtil {
    private SecretKey secretKey;// 시크릿키라는 객체가 존재함
    public JwtUtil(@Value("${jwt.secret.key}") String secretKey ) {// 밸류가 롬복이 아니고 스프링프레임워크/ 전역변수 이름을 가져올때 밸류 이렇게 가져와서 매개변수 스트링에 담아줌.
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),"HmacSHA256");// 스프링 암호화알고리즘 이름:HmacSHA256임. 위의 문자열을 바이츠 스프링으로 쭉피고(utf-8) 암호화알고리즘으로 변경하며
        // 이건 대칭키라고 해서 같은 키로 암호화, 복호화 를 다 시킴. 이 암호화 키의 객체를 만들어 냄.
        // 빈으로 한번 호출될때 이게 등록되고 그게 계속 사용되게 됨
    }

    public String createToken(String username,String role, Long expiration) {//토큰 만들기
        // 패워는 넣지 않음, 익스파이레이션꼭 넣어야 함, 만료기간이며 이건 밀리초 세컨으로 넣음, 그 외에는 넣고 싶은것 넣음. 리프레시 토큰 일때는 달라지기도 함.
        return Jwts.builder() // jwts아까 설치에서 제공
                .claim("username",username)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))//기준점이 되는 날짜 정보(어느 시점부터 유효한지)
                .expiration(new Date(System.currentTimeMillis()+expiration)) //커런트 밀스는 현재까지 시스템이 잰 날짜의 밀리초임 두개 더해서 만료 기한날짜
                .signWith(this.secretKey)// 기존것 넣어주고
                .compact();// 컴팩트 하면 완료. 토큰은 문자열 형태로 리턴됨
    }
    //분리 추출할 함수
    public String getUsername(String token) {//프론트가 가져온 토큰을 확인
        return Jwts.parser().verifyWith(this.secretKey).build().parseSignedClaims(token).getPayload().get("username").toString();//jwts클래스에서 파싱 객체 만들때 시크릿 키 객체를 넘겨줘야 하며, 그 객체의 파스사인드클래임스 토큰가져와서 그 안에 있는 정보를 가져옴(패이로드는 전부)
                // 그중에 유저내임만 가져오고, 바이트스트링이여서 투스트링으로 변환해 가져옴.
                //parse: 흩어서 찢어 낸다는 의미, 만들떄 시크릿키가 있으면 뜯을 때도 시크릿 키가 필요함.
    }

    public String getRole(String token) {//프론트가 가져온 토큰을 확인
        return Jwts.parser().verifyWith(this.secretKey).build()
                .parseSignedClaims(token).getPayload().get("role").toString();
        // 그중에 유저내임만 가져오고, 바이트스트링이여서 투스트링으로 변환해 가져옴.
        //parse: 흩어서 찢어 낸다는 의미, 만들떄 시크릿키가 있으면 뜯을 때도 시크릿 키가 필요함.
    }

    public boolean isExpired(String token){// 만료 여부 확인
        return Jwts.parser().verifyWith(this.secretKey).build()
        .parseSignedClaims(token).getPayload().getExpiration().before(new Date());//페이로드 뒤가 다름. 겟 익스파이레이션, 비포가 들어감. 뉴 데이트는 날짜 객체 하나 넣어줌. 익스파이레이션 해서 오늘 날짜랑 비교함. 비포로 해서 그 날짜가 오늘 보다 이전이냐 하면 만료 트루다(6/1이면 6/2보다 이전임)
        // 만료가 되면 오히려 익셉션이 발생한다. 트라이 - 캐치로 써서 캐치로 간다.
    }

}
