package kr.ac.skuniv.project.carpooluser.service.driver;

import kr.ac.skuniv.project.carpooluser.domain.dto.driver.DriverSaveDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.Driver;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.DriverRepository;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import kr.ac.skuniv.project.carpooluser.service.user.CommonService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class DriverSaveService {

    private final DriverRepository driverRepository;
    private final CommonService commonService;
    private final UserRepository userRepository;


    public DriverSaveService(DriverRepository driverRepository, CommonService commonService, UserRepository userRepository) {
        this.driverRepository = driverRepository;
        this.commonService = commonService;
        this.userRepository = userRepository;
    }

    /**
     * 운전자 정보 등록
     * @param driverSaveDto : 등록될 운전자 정보
     * @param cookie : userId를 조회하기 위한 Cookie 객체
     */
    public Driver driverInformationSaveByCookie(DriverSaveDto driverSaveDto, Cookie cookie) {
        String userId = commonService.getUserIdByCookie(cookie);

        if(userId == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        Driver driver = Driver.builder()
                .licenseNumber(driverSaveDto.getLicenseNumber())
                .licenseType(driverSaveDto.getLicenseType())
                .licenseClosingDate(driverSaveDto.getLicenseClosingDate())
                .carNumber(driverSaveDto.getCarNumber())
                .carType(driverSaveDto.getCarType())
                .isInsurance(driverSaveDto.isInsurance())
                .user(userRepository.findById(userId))
                .build();

        return driverRepository.save(driver);
    }

    /**
     * 운전자 정보 등록
     * @param driverSaveDto : 등록될 운전자 정보
     */
    public Driver driverInformationSave(DriverSaveDto driverSaveDto) {

        String userId = driverSaveDto.getUserId();

        if(userId == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        Driver driver = Driver.builder()
                .licenseNumber(driverSaveDto.getLicenseNumber())
                .licenseType(driverSaveDto.getLicenseType())
                .licenseClosingDate(driverSaveDto.getLicenseClosingDate())
                .carNumber(driverSaveDto.getCarNumber())
                .carType(driverSaveDto.getCarType())
                .isInsurance(driverSaveDto.isInsurance())
                .user(userRepository.findById(userId))
                .build();

        return driverRepository.save(driver);
    }
}
