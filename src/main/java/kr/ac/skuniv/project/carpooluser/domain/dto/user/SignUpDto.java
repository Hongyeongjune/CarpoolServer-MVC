package kr.ac.skuniv.project.carpooluser.domain.dto.user;

import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String id;
    private String name;
    private String password;
    private String sex;
    private String age;
    private String phone;
    private String deviceToken;

    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .password(password)
                .sex(sex)
                .age(age)
                .phone(phone)
                .deviceToken(deviceToken)
                .build();
    }
}
