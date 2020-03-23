package kr.ac.skuniv.project.carpooluser.service.user;

import kr.ac.skuniv.project.carpooluser.domain.dto.user.LoginDto;
import kr.ac.skuniv.project.carpooluser.domain.dto.user.SignInDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class SignInService {

    private static final Logger logger = LoggerFactory.getLogger(SignInService.class);

    final private UserRepository userRepository;

    public SignInService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 로그인
     * @param signInDto : 로그인할 데이터(ID, PASSWORD)
     * @param response : 로그인 정보를 cookie에 담기 위한 객체

    public LoginDto signIn(SignInDto signInDto, HttpServletResponse response) {

        User user = userRepository.findById(signInDto.getId());

        if(user.getId() == null) {
            throw new UserDefineException("존재하지 않는 아이디입니다.");
        }

        if(!user.getPassword().equals(signInDto.getPassword())) {
            throw new UserDefineException("비밀번호가 일치하지않습니다.");
        }

        Cookie cookie = new Cookie("user", user.getId());
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new LoginDto("Login Success");

    }
     */

    public LoginDto signIn(SignInDto signInDto) {

        User user = userRepository.findById(signInDto.getId());

        if(user.getId() == null) {
            throw new UserDefineException("존재하지 않는 아이디입니다.");
        }

        if(!user.getPassword().equals(signInDto.getPassword())) {
            throw new UserDefineException("비밀번호가 일치하지않습니다.");
        }

        return new LoginDto(signInDto.getId());

    }

    /**
     * 로그아웃
     * @param response : 로그인 정보를 cookie에서 삭제하기 위한 객체
     */
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("user", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        logger.info("로그아웃 성공");
    }


}
