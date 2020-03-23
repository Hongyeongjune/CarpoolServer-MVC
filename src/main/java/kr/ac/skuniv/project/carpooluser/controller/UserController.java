package kr.ac.skuniv.project.carpooluser.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.ac.skuniv.project.carpooluser.domain.dto.user.*;
import kr.ac.skuniv.project.carpooluser.domain.entity.User;
import kr.ac.skuniv.project.carpooluser.service.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/carpool/user")
public class UserController {

    private final SignInService signInService;
    private final SignUpService signUpService;
    private final UserDeleteService userDeleteService;
    private final UserGetService userGetService;
    private final UserUpdateService userUpdateService;

    public UserController(SignInService signInService, SignUpService signUpService, UserDeleteService userDeleteService, UserGetService userGetService, UserUpdateService userUpdateService) {
        this.signInService = signInService;
        this.signUpService = signUpService;
        this.userDeleteService = userDeleteService;
        this.userGetService = userGetService;
        this.userUpdateService = userUpdateService;
    }

    @ApiOperation(value = "회원가입")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signUpDto", value = "회원가입 고객 정보", required = true, dataType = "SignUpDto")
    })
    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(signUpService.signUp(signUpDto));
    }

//    @ApiOperation(value = "로그인")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "signInDto", value="로그인 고객 정보", required = true, dataType = "SignInDto")
//    })
//    @PostMapping("/signIn")
//    public ResponseEntity<LoginDto> signIn(@RequestBody SignInDto signInDto, HttpServletResponse response) {
//        return ResponseEntity.ok(signInService.signIn(signInDto,response));
//    }

    @ApiOperation(value = "로그인")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signInDto", value="로그인 고객 정보", required = true, dataType = "SignInDto")
    })
    @PostMapping("/signIn")
    public ResponseEntity<LoginDto> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }

    @ApiOperation(value = "회원 정보 조회")
    @GetMapping("/getInformationByCookie")
    public UserGetDto getUserInfo(@CookieValue(value = "user", required = false) Cookie cookie) {
        return userGetService.getUserInformationByCookie(cookie);
    }
    @ApiOperation(value = "회원 정보 조회")
    @GetMapping("/getInformation/{userId}")
    public ResponseEntity<UserGetDto> getUserInformation(@PathVariable String userId) {
        return ResponseEntity.ok(userGetService.getUserInformation(userId));
    }

    @ApiOperation(value = "회원 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUpdateDto", value="수정할 데이터", required = true, dataType = "UserUpdateDto")
    })
    @PutMapping
    public ResponseEntity updateUser(@CookieValue(value = "user", required = false)Cookie cookie, @RequestBody UserUpdateDto userUpdateDto) {
        userUpdateService.updateUser(userUpdateDto, cookie);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity removeUser(@CookieValue(value = "user", required = false)Cookie cookie) {
        userDeleteService.deleteUser(cookie);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "ID 중복 체크(중복된 ID가 없으면 True)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkUserIdDto", value="중복 체크할 아이디", required = true, dataType = "CheckUserIdDto")
    })
    @PostMapping("/checkUserId")
    public ResponseEntity<Boolean> checkUserId(@RequestBody CheckUserIdDto checkUserIdDto){
        return ResponseEntity.ok(signUpService.checkUserId(checkUserIdDto));
    }


    @ApiOperation(value = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response){
        signInService.logout(response);
        return ResponseEntity.ok().build();
    }

}
