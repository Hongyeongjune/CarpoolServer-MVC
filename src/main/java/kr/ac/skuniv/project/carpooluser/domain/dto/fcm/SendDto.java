package kr.ac.skuniv.project.carpooluser.domain.dto.fcm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendDto {

    private String userId;
    private String deviceToken;
    private String title;
    private String body;

}
