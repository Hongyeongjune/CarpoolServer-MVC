package kr.ac.skuniv.project.carpooluser.domain.dto.driving;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrivingUpdateDto {

    private Long dno;
    private String departure;
    private String destination;
    private String distance;
    private String date;

}
