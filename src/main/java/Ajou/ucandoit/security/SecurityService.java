package Ajou.ucandoit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {

    @Value("${jwt.secret}")
    private String SECRET_KEY; // 이건 나중에 숨겨야함
    // login
    public String createToken(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 알고리즘 설정

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY); //시크릿키를 바이트 단위로 만듬. 로직에선 바이트 단위로 써야함
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName()); // 암호화 키를 만들어줌.

        return Jwts.builder()
                .setSubject(subject) // 보통 유저 아이디를 서브젝트로, 비밀번호를 시크릿키 만드는데 사용함
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(new Date(System.currentTimeMillis() + (2*60*1000*60L)))
                .compact();

    }

    // valid token 체크
    public String getSubject(String token) {
        // claims 페이로드에 담기는 정보
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token) // 토큰을 넣어서 풀어줌
                .getBody();

        return claims.getSubject();
    }

    public Claims getClamims(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token) // 토큰을 넣어서 풀어줌
                .getBody();
    }
}
