package kr.ac.skuniv.project.carpooluser.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingGetDto;
import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingSaveDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.Driving;
import kr.ac.skuniv.project.carpooluser.service.driving.DrivingDeleteService;
import kr.ac.skuniv.project.carpooluser.service.driving.DrivingGetService;
import kr.ac.skuniv.project.carpooluser.service.driving.DrivingSaveService;
import kr.ac.skuniv.project.carpooluser.service.driving.DrivingUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carpool/driving")
public class DrivingController {

    private final DrivingDeleteService drivingDeleteService;
    private final DrivingGetService drivingGetService;
    private final DrivingSaveService drivingSaveService;
    private final DrivingUpdateService drivingUpdateService;

    public DrivingController(DrivingDeleteService drivingDeleteService, DrivingGetService drivingGetService, DrivingSaveService drivingSaveService, DrivingUpdateService drivingUpdateService) {
        this.drivingDeleteService = drivingDeleteService;
        this.drivingGetService = drivingGetService;
        this.drivingSaveService = drivingSaveService;
        this.drivingUpdateService = drivingUpdateService;
    }

    @ApiOperation(value = "운행정보등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "drivingSaveDto", value = "회원가입 고객 정보", required = true, dataType = "DrivingSaveDto")
    })
    @PostMapping("/saveDriving")
    public ResponseEntity<Driving> saveDriving(@RequestBody DrivingSaveDto drivingSaveDto) {
        return ResponseEntity.ok(drivingSaveService.saveDriving(drivingSaveDto));
    }

    @ApiOperation(value = "회원 요청 리스트 조회")
    @GetMapping("/getDrivingListByUserId/{userId}")
    public ResponseEntity<List<DrivingGetDto>> getDrivingListByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(drivingGetService.getDrivingByUserId(userId));
    }

    @ApiOperation(value = "회원 요청 리스트 조회 운행 완료")
    @GetMapping("/getDrivingListByUserIdAndFinish/{userId}")
    public ResponseEntity<List<DrivingGetDto>> getDrivingListByUserIdAndFinish(@PathVariable String userId) {
        return ResponseEntity.ok(drivingGetService.getDrivingByUserIdAndFinish(userId));
    }

    @ApiOperation(value = "회원 요청 리스트 조회 출발지 기준")
    @GetMapping("/getDrivingByDeparture/{first}/{second}")
    public ResponseEntity<List<DrivingGetDto>> getDrivingByDeparture(@PathVariable String first, @PathVariable String second) {
        return ResponseEntity.ok(drivingGetService.getDrivingByDeparture(first, second));
    }

    @ApiOperation(value = "회원 요청 리스트 조회 도착지 기준")
    @GetMapping("/getDrivingByDestination/{first}/{second}")
    public ResponseEntity<List<DrivingGetDto>> getDrivingByDestination(@PathVariable String first, @PathVariable String second) {
        return ResponseEntity.ok(drivingGetService.getDrivingByDestination(first, second));
    }

    @ApiOperation(value = "회원 요청 리스트 조회")
    @GetMapping("/getAllDrivings")
    public ResponseEntity<List<DrivingGetDto>> getAllDrivings() {
        return ResponseEntity.ok(drivingGetService.getAllDrivings());
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/removeDrivingList/{userId}/{dno}")
    public ResponseEntity removeDriving(@PathVariable String userId,@PathVariable Long dno) {
        drivingDeleteService.deleteDriving(dno, userId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "운행 신청")
    @PutMapping("/updateDrivingRequest/{userId}/{dno}")
    public ResponseEntity updateDrivingRequest(@PathVariable String userId, @PathVariable Long dno) {
        drivingUpdateService.updateDrivingRequest(userId, dno);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "운행 응답")
    @PutMapping("/updateMatchingCancel/{dno}")
    public ResponseEntity updateMatchingIsDriving(@PathVariable Long dno) {
        drivingUpdateService.updateMatchingCancel(dno);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "매칭된 운전자 변경")
    @PutMapping("/updateMatchingDriver/{userId}/{dno}/{driverId}")
    public ResponseEntity updateDrivingRequest(@PathVariable String userId, @PathVariable Long dno, @PathVariable String driverId) {
        drivingUpdateService.updateMatchingDriver(userId, dno, driverId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "매칭 리스트 조회")
    @GetMapping("/getMatchingList/{userId}")
    public ResponseEntity<List<DrivingGetDto>> getMatchingList(@PathVariable String userId) {
        return ResponseEntity.ok(drivingGetService.getMatchingList(userId));
    }

    @ApiOperation(value = "매칭 리스트 조회")
    @GetMapping("/getMatchingListByUserId/{userId}")
    public ResponseEntity<List<DrivingGetDto>> getMatchingListByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(drivingGetService.getMatchingListByUserId(userId));
    }

    @ApiOperation(value = "운행 완료 운전자")
    @PutMapping("/updateFinishByDriver/{dno}")
    public ResponseEntity updateFinishByDriver(@PathVariable Long dno) {
        drivingUpdateService.updateFinishByDriver(dno);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "운행 완료 사용자")
    @PutMapping("/updateFinishByUser/{dno}")
    public ResponseEntity updateFinishByUser(@PathVariable Long dno) {
        drivingUpdateService.updateFinishByUser(dno);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "운행 완료 운전자")
    @GetMapping("/getFinishByDriver/{userId}")
    public ResponseEntity<List<DrivingGetDto>> getFinishByDriver(@PathVariable String userId) {
        return ResponseEntity.ok(drivingGetService.getFinishListByDriver(userId));
    }

    @ApiOperation(value = "운행 완료 사용자")
    @GetMapping("/getFinishByUser/{userId}")
    public ResponseEntity<List<DrivingGetDto>> getFinishByUser(@PathVariable String userId) {
        return ResponseEntity.ok(drivingGetService.getFinishListByUser(userId));
    }



}
