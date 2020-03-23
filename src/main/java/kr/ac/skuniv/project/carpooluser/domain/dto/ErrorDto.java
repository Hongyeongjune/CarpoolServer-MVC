package kr.ac.skuniv.project.carpooluser.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String originalErrorMessage;
    private String errorMessage;
    private String requestUrl;

    @Builder
    public ErrorDto(String originalErrorMessage, String errorMessage, String requestUrl) {
        this.originalErrorMessage = originalErrorMessage;
        this.errorMessage = errorMessage;
        this.requestUrl = requestUrl;
    }
}
