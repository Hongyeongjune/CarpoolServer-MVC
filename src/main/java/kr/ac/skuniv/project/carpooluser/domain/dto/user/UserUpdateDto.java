package kr.ac.skuniv.project.carpooluser.domain.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {
    private String name;
    private String password;
    private String sex;
    private String age;
    private String phone;
}
