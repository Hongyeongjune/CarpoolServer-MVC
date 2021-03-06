package kr.ac.skuniv.project.carpooluser.service.driver;

import kr.ac.skuniv.project.carpooluser.domain.dto.driver.DriverUpdateDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.Driver;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.DriverRepository;
import kr.ac.skuniv.project.carpooluser.service.user.CommonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class DriverUpdateService {

    private final DriverRepository driverRepository;
    private final CommonService commonService;

    public DriverUpdateService(DriverRepository driverRepository, CommonService commonService) {
        this.driverRepository = driverRepository;
        this.commonService = commonService;
    }

    /**
     * 운전자 정보를 수정
     * @param driverUpdateDto : 등록된 운전자 정보를 수정하는 데이터
     * @param cookie : userID를 조회하기 위한 Cookie 객체
     */
    public void updateDriver(DriverUpdateDto driverUpdateDto, Cookie cookie) {
        String userId = commonService.getUserIdByCookie(cookie);

        if(userId == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        Driver driver = driverRepository.findByUserId(userId);

        driver.updateDriver(driverUpdateDto);

        driverRepository.save(driver);
    }


}
