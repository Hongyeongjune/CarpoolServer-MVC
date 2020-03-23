package kr.ac.skuniv.project.carpooluser.repository.custom;

import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingGetDetailDto;
import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingGetDto;
import org.springframework.data.domain.Page;

public interface DrivingRepositoryCustom {

    Page<DrivingGetDto> getDrivingsByDeparture(int pageNum, String departure);
    Page<DrivingGetDto> getDrivingsByDestination(int pageNum, String destination);
    Page<DrivingGetDto> getAllDrivings(int pageNum);
    Page<DrivingGetDto> getDrivingsByUserId(int pageNum, String userId);
}
