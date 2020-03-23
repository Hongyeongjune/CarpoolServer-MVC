package kr.ac.skuniv.project.carpooluser.service.user;

import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class UserDeleteService {

    public final UserRepository userRepository;
    public final CommonService commonService;

    public UserDeleteService(UserRepository userRepository, CommonService commonService) {
        this.userRepository = userRepository;
        this.commonService = commonService;
    }

    /**
     * 회원탈퇴
     * @param cookie : userId를 조회하기 위한 Cookie 객체
     */
    public void deleteUser(Cookie cookie) {
        String userId = commonService.getUserIdByCookie(cookie);

        User user = userRepository.findById(userId);

        if(user == null) {
            throw new UserDefineException("원 정보를 찾을 수 없어 삭제할 수 없습니다.");
        }

        userRepository.delete(user);
    }


}
