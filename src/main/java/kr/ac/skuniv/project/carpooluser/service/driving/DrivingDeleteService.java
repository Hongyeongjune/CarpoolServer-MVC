package kr.ac.skuniv.project.carpooluser.service.driving;

import kr.ac.skuniv.project.carpooluser.domain.entity.Driving;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.DrivingRepository;
import kr.ac.skuniv.project.carpooluser.service.user.CommonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class DrivingDeleteService {

    private final CommonService commonService;
    private final DrivingRepository drivingRepository;


    public DrivingDeleteService(CommonService commonService, DrivingRepository drivingRepository) {
        this.commonService = commonService;
        this.drivingRepository = drivingRepository;
    }

    /**
     * 운행 요청 목록 삭제
     * @param dno : 삭제할 운행 번호
     * @param cookie : userID를 조회하기 위한 Cookie 객체
     */
    public void deleteDrivingByCookie(Long dno, Cookie cookie) {

        String userId = commonService.getUserIdByCookie(cookie);

        if(userId == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        Driving driving = drivingRepository.findByUserIdAndDno(userId, dno);

        drivingRepository.delete(driving);

    }

    /**
     * 운행 요청 목록 삭제
     * @param dno : 삭제할 운행 번호
     */
    public void deleteDriving(Long dno, String userId) {

        Driving driving = drivingRepository.findByUserIdAndDno(userId, dno);

        if(driving == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        drivingRepository.delete(driving);

    }
}
