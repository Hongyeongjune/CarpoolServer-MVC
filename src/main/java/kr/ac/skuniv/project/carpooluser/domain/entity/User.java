package kr.ac.skuniv.project.carpooluser.domain.entity;

import kr.ac.skuniv.project.carpooluser.domain.dto.user.UserUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long uno;

    //사용자 아이디
    @Column(unique = true)
    private String id;

    String name;
    String password;
    String sex;
    String age;
    String phone;

    String deviceToken;

    @Builder
    public User(String id, String name, String password, String sex, String age, String phone, String deviceToken) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.deviceToken = deviceToken;
    }

    public void updateUser(UserUpdateDto userUpdateDto) {
        this.name = userUpdateDto.getName();
        this.password = userUpdateDto.getPassword();
        this.sex = userUpdateDto.getSex();
        this.age = userUpdateDto.getSex();
        this.phone = userUpdateDto.getPhone();
    }
}
