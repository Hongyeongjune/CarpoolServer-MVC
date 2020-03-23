package kr.ac.skuniv.project.carpooluser.repository;

import kr.ac.skuniv.project.carpooluser.domain.entity.Driving;
import kr.ac.skuniv.project.carpooluser.repository.custom.DrivingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrivingRepository extends JpaRepository<Driving, Long>, DrivingRepositoryCustom {

    Driving findByUserIdAndDno(String userId, Long dno);
    List<Driving> findByDriverId(String driverId);
    List<Driving> findByUserId(String userId);

    Driving findByDno(Long dno);

}
