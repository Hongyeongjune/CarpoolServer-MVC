package kr.ac.skuniv.project.carpooluser.domain.dto.driver;

import kr.ac.skuniv.project.carpooluser.domain.entity.Driver;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverSaveDto {

    private String licenseNumber;
    private String licenseType;
    private String licenseClosingDate;

    private String carNumber;
    private String carType;

    private boolean isInsurance;

    private String userId;

    public Driver toEntity(User user) {
        return Driver.builder()
                .licenseNumber(licenseNumber)
                .licenseType(licenseType)
                .licenseClosingDate(licenseClosingDate)
                .carNumber(carNumber)
                .carType(carType)
                .isInsurance(isInsurance)
                .user(user)
                .build();
    }
}
