package gdsc.shine.springlearningsimple.config.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 컴포넌트 스캔을 통한 빈 등록
@Component
public class JwtTokenExpireProvider {
    private long validityInMilliseconds;

    // application.properties의 security.jwt.token.expire-length 값을 활용하여 validityInMilliseconds값 초기화 하기
    @Autowired
    public JwtTokenExpireProvider(@Value("${security.jwt.token.expire-length}") long validityInMilliseconds) {
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public long getValidityInMilliseconds() {
        return validityInMilliseconds;
    }
}
