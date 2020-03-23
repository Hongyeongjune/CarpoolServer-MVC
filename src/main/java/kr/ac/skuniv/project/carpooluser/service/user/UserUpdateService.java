package kr.ac.skuniv.project.carpooluser.service.user;

import kr.ac.skuniv.project.carpooluser.domain.dto.user.UserUpdateDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class UserUpdateService {

    private final UserRepository userRepository;
    private final CommonService commonService;

    public UserUpdateService(UserRepository userRepository, CommonService commonService) {
        this.commonService = commonService;
        this.userRepository = userRepository;
    }

    /**
     * 회원정보 수정
     * @param userUpdateDto : 수정할 회원 정보를 담은 데이터
     * @param cookie : userId를 조회하기 위한 객체
     */
    public void updateUser(UserUpdateDto userUpdateDto, Cookie cookie) {
        String userId = commonService.getUserIdByCookie(cookie);

        User user =  userRepository.findById(userId);

        if(user == null) {
            throw new UserDefineException("회원 정보를 찾을 수 없어 수정할 수 없습니다.");
        }

        user.updateUser(userUpdateDto);

        userRepository.save(user);
    }

}
