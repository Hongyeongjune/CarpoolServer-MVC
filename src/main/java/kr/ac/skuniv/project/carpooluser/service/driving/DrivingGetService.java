package kr.ac.skuniv.project.carpooluser.service.driving;

import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingGetDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.Driving;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.exception.UserDefineException;
import kr.ac.skuniv.project.carpooluser.repository.DrivingRepository;
import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import kr.ac.skuniv.project.carpooluser.service.user.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrivingGetService {

    private final CommonService commonService;
    private final DrivingRepository drivingRepository;
    private final UserRepository userRepository;

    public DrivingGetService(CommonService commonService, DrivingRepository drivingRepository, UserRepository userRepository) {
        this.commonService = commonService;
        this.drivingRepository = drivingRepository;
        this.userRepository = userRepository;
    }

    /**
     * 내가 신청한 운행 리스트만 조회(마이 페이지 개념)
     * @param : 사용자 아이디
     * @return : 운행 요청 목록 리턴
     */
    public List<DrivingGetDto> getDrivingByUserId(String userId) {

        List<Driving> drivings = drivingRepository.findByUserId(userId);

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {

            if(!driving.isDriverCall()) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getUser().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isDriverFinishCall())
                                .build()
                );
            }
        }

        return drivingGetDtos;

    }

    /**
     * 내가 신청한 운행 리스트만 조회(마이 페이지 개념)
     * @param : 사용자 아이디
     * @return : 운행 요청 목록 리턴
     */
    public List<DrivingGetDto> getDrivingByUserIdAndFinish(String userId) {

        List<Driving> drivings = drivingRepository.findByUserId(userId);

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {

            if(driving.isDriverFinishCall() && driving.isUserFinishCall()) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getUser().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isDriverFinishCall())
                                .build()
                );
            }
        }

        return drivingGetDtos;

    }


    /**
     * 모든 운행 리스트 조회
     * @return : 모든 운행 가능 리스트 조회
     */
    public List<DrivingGetDto> getAllDrivings() {

        List<Driving> drivings = drivingRepository.findAll();

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {
            if (!driving.isDriverCall()) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getUser().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isDriverFinishCall())
                                .build()
                );
            }
        }
        return drivingGetDtos;
    }

    /**
     * 출발지 기준으로 조회
     * @param first : 서울 / 인천 / 경기 / 대전 / 대구 / 부산 / 광주 ...
     * @param second : 성북구 / 구리시 / 연수구 ....
     * @return
     */
    public List<DrivingGetDto> getDrivingByDeparture(String first, String second) {

        List<Driving> drivings = drivingRepository.findAll();

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {
            if (driving.getDeparture().contains(first) && driving.getDeparture().contains(second) && !driving.isDriverCall()) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getUser().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isDriverFinishCall())
                                .build()
                );
            }
        }

        return drivingGetDtos;
    }

    /**
     * 도착지 기준으로 조회
     * @param first : 서울 / 인천 / 경기 / 대전 / 대구 / 부산 / 광주 ...
     * @param second : 성북구 / 구리시 / 연수구 ....
     * @return
     */
    public List<DrivingGetDto> getDrivingByDestination(String first, String second) {

        List<Driving> drivings = drivingRepository.findAll();

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {
            if (driving.getDestination().contains(first) && driving.getDestination().contains(second) && !driving.isDriverCall()) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getUser().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isDriverFinishCall())
                                .build()
                );
            }
        }

        return drivingGetDtos;
    }


    public List<DrivingGetDto> getMatchingList(String driverId) {

        List<Driving> drivings = drivingRepository.findByDriverId(driverId);

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {

            if(driving.isDriverCall() && (!driving.isDriverFinishCall() || !driving.isUserFinishCall())) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getUser().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isUserFinishCall())
                                .build()
                );
            }
        }

        return drivingGetDtos;
    }

    public List<DrivingGetDto> getMatchingListByUserId(String userId) {

        List<Driving> drivings = drivingRepository.findByUserId(userId);

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {

            if(driving.isDriverCall() && (!driving.isDriverFinishCall() || !driving.isUserFinishCall())) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getDriver().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isUserFinishCall())
                                .build()
                );
            }
        }

        return drivingGetDtos;
    }

    public List<DrivingGetDto> getFinishListByDriver(String userId) {

        List<Driving> drivings = drivingRepository.findByDriverId(userId);

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {
            if(driving.isDriverCall() && driving.isDriverFinishCall()  && driving.isUserFinishCall()) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getDriver().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isDriverFinishCall())
                                .build()
                );
            }
        }

        return drivingGetDtos;
    }

    public List<DrivingGetDto> getFinishListByUser(String userId) {

        List<Driving> drivings = drivingRepository.findByUserId(userId);

        List<DrivingGetDto> drivingGetDtos = new ArrayList<DrivingGetDto>();

        for(Driving driving : drivings) {
            if(driving.isDriverCall() && driving.isDriverFinishCall()  && driving.isUserFinishCall()) {
                drivingGetDtos.add(
                        DrivingGetDto.builder()
                                .dno(driving.getDno())
                                .distance(driving.getDistance())
                                .departure(driving.getDeparture())
                                .destination(driving.getDestination())
                                .isDriving(driving.isDriving())
                                .date(driving.getDate())
                                .regDate(driving.getRegDate() + "")
                                .userId(driving.getDriver().getId())
                                .driverCall(driving.isDriverCall())
                                .driverFinishCall(driving.isDriverFinishCall())
                                .userFinishCall(driving.isDriverFinishCall())
                                .build()
                );
            }
        }

        return drivingGetDtos;
    }
    /**
     * 모든 운행 리스트 조회
     * @param pageNum : 리스트 화면에서의 페이지 번호
     * @return : 페이지 번호에 맞게 해당 작품들 반환
     */
    public Page<DrivingGetDto> getAllDrivingsByPage(int pageNum) {
        return drivingRepository.getAllDrivings(pageNum);
    }

    /**
     * 내가 신청한 운행 리스트만 조회(마이 페이지 개념)
     * @param cookie : userId를 조회하기 위한 Cookie 객체
     * @param pageNum : 페이지 번호에 맞게 해당 작품들 반환
     * @return : 페이지 번호에 맞게 해당 작품들 반환
     */
    public Page<DrivingGetDto> getDrivingsUserId(Cookie cookie, int pageNum) {
        String userId = commonService.getUserIdByCookie(cookie);

        if(userId == null) {
            throw new UserDefineException("로그인이 필요합니다.");
        }

        return drivingRepository.getDrivingsByUserId(pageNum, userId);
    }

    /**
     * 내가 선택한 출발지 기준 리스트 조회
     * @param pageNum : 페이지 번호에 맞게 해당 작품들 반환
     * @return : 페이지 번호에 맞게 해당 작품들 반환
     */
    public Page<DrivingGetDto> getDrivingsDeparture(int pageNum, String departure) {

        return drivingRepository.getDrivingsByDeparture(pageNum, departure);

    }

    /**
     * 내가 선택한 출발지 기준 리스트 조회
     * @param pageNum : 페이지 번호에 맞게 해당 작품들 반환
     * @return : 페이지 번호에 맞게 해당 작품들 반환
     */
    public Page<DrivingGetDto> getDrivingsDestination(int pageNum, String destination) {

        return drivingRepository.getDrivingsByDestination(pageNum, destination);

    }

}
