package kr.ac.skuniv.project.carpooluser.domain.dto.driver;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverGetDto {

    private String licenseNumber;
    private String licenseType;
    private String licenseClosingDate;

    private String carNumber;
    private String carType;

    private boolean isInsurance;

}
