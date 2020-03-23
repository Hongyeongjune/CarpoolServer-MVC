package kr.ac.skuniv.project.carpooluser.domain.entity;

import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Driving {

    //운행 요청 리스트 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dno;

    private String departure;
    private String destination;
    private String distance;
    private String date;
    private boolean driverCall;
    private boolean driverFinishCall;
    private boolean userFinishCall;

    @Column(nullable = false)
    private boolean isDriving;

    @CreationTimestamp
    private LocalDate regDate; //등록일

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Driving(String departure,String destination, String distance, boolean isDriving, String date, boolean driverCall, boolean driverFinishCall, boolean userFinishCall, LocalDate regDate, User user, User driver) {
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
        this.isDriving = isDriving;
        this.date = date;
        this.regDate = regDate;
        this.user = user;
        this.driverCall = driverCall;
        this.driverFinishCall = driverFinishCall;
        this.userFinishCall = userFinishCall;
        this.driver = driver;
    }

    public void updateDriving(DrivingUpdateDto drivingUpdateDto) {
        this.departure = drivingUpdateDto.getDeparture();
        this.destination = drivingUpdateDto.getDestination();
        this.distance = drivingUpdateDto.getDistance();
        this.date = drivingUpdateDto.getDate();
    }
}
