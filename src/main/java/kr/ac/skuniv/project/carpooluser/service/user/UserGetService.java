package kr.ac.skuniv.project.carpooluser.service.user;

import kr.ac.skuniv.project.carpooluser.domain.dto.user.UserGetDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class UserGetService {

    private final UserRepository userRepository;
    private final CommonService commonService;

    public UserGetService(UserRepository userRepository, CommonService commonService) {
        this.userRepository = userRepository;
        this.commonService = commonService;
    }

    /**
     * 회원 정보 조회
     * @param cookie : userId를 조회하기 위한 객체
     * @return : 조회한 회원 정보
     */
    public UserGetDto getUserInformationByCookie(Cookie cookie) {

        String userId = commonService.getUserIdByCookie(cookie);

        User user = userRepository.findById(userId);

        if(user == null) {
            throw new UserDefineException("회원 정보를 찾을 수 없어 조회할 수 없습니다.");
        }

        return UserGetDto.builder()
                .uno(user.getUno())
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .sex(user.getSex())
                .age(user.getAge())
                .build();
    }

    /**
     * 회원 정보 조회
     * @param userId : userId를 조회하기 위한 문자
     * @return : 조회한 회원 정보
     */
    public UserGetDto getUserInformation(String userId) {

        User user = userRepository.findById(userId);

        if(user == null) {
            throw new UserDefineException("회원 정보를 찾을 수 없어 조회할 수 없습니다.");
        }

        return UserGetDto.builder()
                .uno(user.getUno())
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .sex(user.getSex())
                .age(user.getAge())
                .build();
    }

}
