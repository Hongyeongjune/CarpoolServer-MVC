package kr.ac.skuniv.project.carpooluser.repository;

import kr.ac.skuniv.project.carpooluser.domain.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver findByUserId(String userId);

}
