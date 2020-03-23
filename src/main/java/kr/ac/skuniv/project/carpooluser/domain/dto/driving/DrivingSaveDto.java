package kr.ac.skuniv.project.carpooluser.domain.dto.driving;

import kr.ac.skuniv.project.carpooluser.domain.entity.Driving;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrivingSaveDto {

    private String departure;
    private String destination;
    private String distance;
    private String date;
    private String userId;

    public Driving toEntity(User user) {
        return Driving.builder()
                .departure(departure)
                .destination(destination)
                .distance(distance)
                .date(date)
                .user(user)
                .build();
    }
}
