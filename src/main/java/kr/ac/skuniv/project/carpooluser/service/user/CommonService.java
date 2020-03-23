package kr.ac.skuniv.project.carpooluser.service.user;

import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.Cookie;

@Service
public class CommonService  {

    /**
     * 고객의 아이디를 얻어내는 메소드
     * @param cookie : 고객 정보가 담긴 토큰이 있는 쿠키
     * @return : 사용자 아이디 String
     */
    public String getUserIdByCookie(Cookie cookie) {
        if(cookie == null) {
            throw new UserDefineException("먼저 로그인을 해야 합니다.");
        }

        String token = cookie.getValue();
        return token;
    }

}
