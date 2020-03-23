package kr.ac.skuniv.project.carpooluser.domain.dto.driving;

import lombok.*;

import javax.servlet.annotation.WebServlet;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrivingGetDto {

    private Long dno;
    private String departure;
    private String destination;
    private String distance;
    private boolean isDriving;
    private String date;
    private String regDate;
    private String userId;
    private boolean driverCall;
    private boolean driverFinishCall;
    private boolean userFinishCall;

}
