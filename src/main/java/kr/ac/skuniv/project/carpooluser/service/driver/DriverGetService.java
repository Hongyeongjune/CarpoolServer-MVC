package kr.ac.skuniv.project.carpooluser.service.driver;

import kr.ac.skuniv.project.carpooluser.domain.dto.driver.DriverGetDto;
import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingGetDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.Driver;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.DriverRepository;
import kr.ac.skuniv.project.carpooluser.service.user.CommonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class DriverGetService {

    private final CommonService commonService;
    private final DriverRepository driverRepository;


    public DriverGetService(CommonService commonService, DriverRepository driverRepository) {
        this.commonService = commonService;
        this.driverRepository = driverRepository;
    }

    /**
     * 운전자 정보 조회
     * @param cookie
     * @return
     */
    public DriverGetDto getDriverInfoByCookie(Cookie cookie) {
        String userId = commonService.getUserIdByCookie(cookie);

        Driver driver = driverRepository.findByUserId(userId);

        if(driver == null) {
            throw new UserDefineException("존재하지 않는 회원입니다.");
        }

        return DriverGetDto.builder()
                .licenseNumber(driver.getLicenseNumber())
                .licenseType(driver.getLicenseType())
                .licenseClosingDate(driver.getLicenseClosingDate())
                .carNumber(driver.getCarNumber())
                .carType(driver.getCarType())
                .isInsurance(driver.isInsurance())
                .build();
    }

    /**
     * 운전자 정보 조회
     *
     * @return
     */
    public DriverGetDto getDriverInfo(String userId) {

        Driver driver = driverRepository.findByUserId(userId);

        if(driver == null) {
            throw new UserDefineException("존재하지 않는 회원입니다.");
        }

        return DriverGetDto.builder()
                .licenseNumber(driver.getLicenseNumber())
                .licenseType(driver.getLicenseType())
                .licenseClosingDate(driver.getLicenseClosingDate())
                .carNumber(driver.getCarNumber())
                .carType(driver.getCarType())
                .isInsurance(driver.isInsurance())
                .build();
    }

}
