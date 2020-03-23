package kr.ac.skuniv.project.carpooluser.service.fcm;

import kr.ac.skuniv.project.carpooluser.domain.dto.fcm.SendDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import org.omg.CORBA.UserException;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
public class SendSevice {

    private final UserRepository userRepository;

    public SendSevice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SendDto findDeviceToken(SendDto sendDto) {

        User user = userRepository.findById(sendDto.getUserId());

        if(user == null) {
            throw new UserDefineException("사용자 정보가 업습니다.");
        }
        
        return SendDto.builder()
                .userId(user.getId())
                .deviceToken(user.getDeviceToken())
                .title(sendDto.getTitle())
                .body(sendDto.getBody())
                .build();

    }
}
