package kr.ac.skuniv.project.carpooluser.repository;

import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(String userId);
}
