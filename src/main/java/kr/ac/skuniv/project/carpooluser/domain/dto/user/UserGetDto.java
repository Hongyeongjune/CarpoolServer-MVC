package kr.ac.skuniv.project.carpooluser.domain.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGetDto {

    private Long uno;
    private String id;
    private String name;
    private String sex;
    private String age;
    private String phone;
}
