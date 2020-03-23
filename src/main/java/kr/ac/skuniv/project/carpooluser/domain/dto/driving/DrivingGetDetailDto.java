package kr.ac.skuniv.project.carpooluser.domain.dto.driving;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrivingGetDetailDto {

    private Long dno;
    private String departure;
    private String destination;
    private String distance;
    private boolean isDriving;
    private Date date;

    private String id;
    private String name;
    private String age;
    private String phone;
    private String sex;
}
