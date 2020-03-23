package kr.ac.skuniv.project.carpooluser.exception;

import lombok.Getter;

@Getter
public class UserDefineException extends RuntimeException {

    private String originalErrorMessage;
    private String errorMethod;

    public UserDefineException(String message) {
        super(message);
    }

    public UserDefineException(String message, String originalErrorMessage) {
        super(message);
        this.originalErrorMessage = originalErrorMessage;
    }
}
