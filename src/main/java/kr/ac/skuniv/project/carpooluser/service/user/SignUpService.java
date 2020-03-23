package kr.ac.skuniv.project.carpooluser.service.user;

import kr.ac.skuniv.project.carpooluser.domain.dto.user.CheckUserIdDto;
import kr.ac.skuniv.project.carpooluser.domain.dto.user.SignUpDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SignUpService.class);

    private final UserRepository userRepository;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     * @param signUpDto : 회원가입 데이터
     */
    public User signUp(SignUpDto signUpDto) {

        User user = signUpDto.toEntity();

        return userRepository.save(user);

    }

    /**
     * ID 중복 체크
     * @param checkUserIdDto : 중복 체크를 하기 위한 userId
     * @return : 중복된 ID가 없으면 true
     */
    public Boolean checkUserId(CheckUserIdDto checkUserIdDto) {

        User user = userRepository.findById(checkUserIdDto.getUserId());

        if(user != null) {
            logger.info("존재하는 아이디");
            throw new UserDefineException("존재하는 아이디입니다.");
        } else {
            logger.info("아이디 중복 체크 완료");
        }

        return true;
    }
}
