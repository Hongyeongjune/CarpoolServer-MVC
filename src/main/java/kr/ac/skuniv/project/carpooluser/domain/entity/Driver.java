package kr.ac.skuniv.project.carpooluser.domain.entity;

import kr.ac.skuniv.project.carpooluser.domain.dto.driver.DriverUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Driver {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lno;

    private String licenseNumber;
    private String licenseType;
    private String licenseClosingDate;

    private String carNumber;
    private String carType;

    private boolean isInsurance;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Driver(String licenseNumber, String licenseType, String licenseClosingDate, String carNumber, String carType, boolean isInsurance, User user) {
        this.licenseNumber = licenseNumber;
        this.licenseType = licenseType;
        this.licenseClosingDate = licenseClosingDate;
        this.carNumber = carNumber;
        this.carType = carType;
        this.isInsurance = isInsurance;
        this.user = user;
    }

    public void updateDriver(DriverUpdateDto driverUpdateDto) {
        this.licenseNumber = driverUpdateDto.getLicenseNumber();
        this.licenseType = driverUpdateDto.getLicenseType();
        this.licenseClosingDate = driverUpdateDto.getLicenseClosingDate();
        this.carNumber = driverUpdateDto.getCarNumber();
        this.carType = driverUpdateDto.getCarType();
        this.isInsurance = driverUpdateDto.isInsurance();
    }
}
