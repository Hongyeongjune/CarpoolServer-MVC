package kr.ac.skuniv.project.carpooluser.service.driving;

import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingSaveDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.Driving;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.DrivingRepository;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import kr.ac.skuniv.project.carpooluser.service.user.CommonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class DrivingSaveService {

    private final CommonService commonService;
    private final DrivingRepository drivingRepository;
    private final UserRepository userRepository;

    public DrivingSaveService(CommonService commonService, DrivingRepository drivingRepository, UserRepository userRepository) {
        this.commonService = commonService;
        this.drivingRepository = drivingRepository;
        this.userRepository = userRepository;
    }

    /**
     * 운행 요청 작성
     * @param drivingSaveDto : 등록될 운행 요청 정보
     * @param cookie : userId를 조회하기 위한 Cookie 객체
     */
    public void saveDrivingCookie(DrivingSaveDto drivingSaveDto, Cookie cookie) {
        String userId = commonService.getUserIdByCookie(cookie);

        if(userId == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        Driving driving = Driving.builder()
                .departure(drivingSaveDto.getDeparture())
                .destination(drivingSaveDto.getDestination())
                .distance(drivingSaveDto.getDistance())
                .isDriving(false)
                .date(drivingSaveDto.getDate())
                .user(userRepository.findById(userId))
                .build();

        drivingRepository.save(driving);
    }

    /**
     * 운행 요청 작성
     * @param drivingSaveDto : 등록될 운행 요청 정보
     */
    public Driving saveDriving(DrivingSaveDto drivingSaveDto) {

        String userId = drivingSaveDto.getUserId();

        if(userId == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        Driving driving = Driving.builder()
                .departure(drivingSaveDto.getDeparture())
                .destination(drivingSaveDto.getDestination())
                .distance(drivingSaveDto.getDistance())
                .isDriving(false)
                .date(drivingSaveDto.getDate())
                .user(userRepository.findById(userId))
                .driverCall(false)
                .driverFinishCall(false)
                .userFinishCall(false)
                .build();

        return drivingRepository.save(driving);
    }
}
