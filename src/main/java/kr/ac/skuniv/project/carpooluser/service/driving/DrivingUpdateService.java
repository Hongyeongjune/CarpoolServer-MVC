package kr.ac.skuniv.project.carpooluser.service.driving;

import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingUpdateDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.Driving;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.DrivingRepository;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import kr.ac.skuniv.project.carpooluser.service.user.CommonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class DrivingUpdateService {

    private final CommonService commonService;
    private final DrivingRepository drivingRepository;
    private final UserRepository userRepository;

    public DrivingUpdateService(CommonService commonService, DrivingRepository drivingRepository, UserRepository userRepository) {
        this.commonService = commonService;
        this.drivingRepository = drivingRepository;
        this.userRepository = userRepository;
    }

    /**
     * 운행 수정
     * @param drivingUpdateDto : 등록된 요청 정보를 수정할 데이터
     * @param cookie : userId를 조회하기 위한 Cookie 객체
     */
    public void updateDrivingByCookie(DrivingUpdateDto drivingUpdateDto, Cookie cookie) {

        String userId = commonService.getUserIdByCookie(cookie);

        if(userId == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        Driving driving = drivingRepository.findByUserIdAndDno(userId, drivingUpdateDto.getDno());

        driving.updateDriving(drivingUpdateDto);
    }

    public void updateDrivingRequest(String userId, Long dno) {

        Driving driving = drivingRepository.findByUserIdAndDno(userId, dno);

        if(driving == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        driving.setDriverCall(true);

        drivingRepository.save(driving);
    }

    public void updateMatchingDriver(String userId, Long dno, String driverId) {

        Driving driving = drivingRepository.findByUserIdAndDno(userId, dno);
        User driver = userRepository.findById(driverId);

        if(driving == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        driving.setDriver(driver);
        drivingRepository.save(driving);
    }

    public void updateMatchingCancel(Long dno) {

        Driving driving = drivingRepository.findByDno(dno);

        if(driving == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        driving.setDriverCall(false);

        drivingRepository.save(driving);
    }

    public void updateFinishByDriver(Long dno) {

        Driving driving = drivingRepository.findByDno(dno);

        if(driving == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        driving.setDriverFinishCall(true);

        drivingRepository.save(driving);
    }

    public void updateFinishByUser(Long dno) {

        Driving driving = drivingRepository.findByDno(dno);

        if(driving == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        driving.setUserFinishCall(true);

        drivingRepository.save(driving);
    }
}
