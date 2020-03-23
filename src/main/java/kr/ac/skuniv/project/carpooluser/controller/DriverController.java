package kr.ac.skuniv.project.carpooluser.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.ac.skuniv.project.carpooluser.domain.dto.driver.DriverGetDto;
import kr.ac.skuniv.project.carpooluser.domain.dto.driver.DriverSaveDto;
import kr.ac.skuniv.project.carpooluser.domain.dto.driver.DriverUpdateDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.Driver;
import kr.ac.skuniv.project.carpooluser.service.driver.DriverDeleteService;
import kr.ac.skuniv.project.carpooluser.service.driver.DriverGetService;
import kr.ac.skuniv.project.carpooluser.service.driver.DriverSaveService;
import kr.ac.skuniv.project.carpooluser.service.driver.DriverUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

@RestController
@RequestMapping("/carpool/driver")
public class DriverController {

    private final DriverDeleteService driverDeleteService;
    private final DriverGetService driverGetService;
    private final DriverSaveService driverSaveService;
    private final DriverUpdateService driverUpdateService;


    public DriverController(DriverDeleteService driverDeleteService, DriverGetService driverGetService, DriverSaveService driverSaveService, DriverUpdateService driverUpdateService) {
        this.driverDeleteService = driverDeleteService;
        this.driverGetService = driverGetService;
        this.driverSaveService = driverSaveService;
        this.driverUpdateService = driverUpdateService;
    }

    @ApiOperation(value = "운전자 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverSaveDto", value = "등록할 운전자 정보", required = true, dataType = "DriverSaveDto")
    })
    @PostMapping("/saveDriver")
    public ResponseEntity<Driver> driverSave(@RequestBody DriverSaveDto driverSaveDto) {
        return ResponseEntity.ok(driverSaveService.driverInformationSave(driverSaveDto));
    }

    @ApiOperation(value = "운전자 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "driverUpdateDto", value = "수정할 운전자 정보", required = true, dataType = "DriverUpdateDto")
    })
    @PutMapping
    public ResponseEntity<Driver> updateDriver(@CookieValue(value = "user", required = false) Cookie cookie, @RequestBody DriverUpdateDto driverUpdateDto) {
        driverUpdateService.updateDriver(driverUpdateDto, cookie);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity removeUser(@CookieValue(value = "user", required = false)Cookie cookie) {
        driverDeleteService.deleteDriver(cookie);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회원 정보 조회")
    @GetMapping("/getInformationByCookie")
    public DriverGetDto getUserInfoByCookie(@CookieValue(value = "user", required = false) Cookie cookie) {
        return driverGetService.getDriverInfoByCookie(cookie);
    }

    @ApiOperation(value = "회원 정보 조회")
    @GetMapping("/getInformation/{userId}")
    public ResponseEntity<DriverGetDto> getUserInfoByCookie(@PathVariable String userId) {
        return ResponseEntity.ok(driverGetService.getDriverInfo(userId));
    }




}
