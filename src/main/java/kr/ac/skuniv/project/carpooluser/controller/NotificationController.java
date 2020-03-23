package kr.ac.skuniv.project.carpooluser.controller;

import kr.ac.skuniv.project.carpooluser.domain.dto.fcm.SendDto;
import kr.ac.skuniv.project.carpooluser.service.fcm.AndroidPushNotificationsService;
import kr.ac.skuniv.project.carpooluser.service.fcm.AndroidPushPeriodicNotifications;
import kr.ac.skuniv.project.carpooluser.service.fcm.SendSevice;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/carpool/fcm")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    private final SendSevice sendSevice;

    public NotificationController(SendSevice sendSevice) {
        this.sendSevice = sendSevice;
    }

    @PostMapping("/send")
    public @ResponseBody ResponseEntity<String> send(@RequestBody SendDto sendDto) throws JSONException, InterruptedException  {

        SendDto fcmContent = sendSevice.findDeviceToken(sendDto);

        logger.info(fcmContent.getTitle(), fcmContent.getBody() + "확인");

        logger.info("---------------------------");

        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson(
                fcmContent.getDeviceToken(), fcmContent.getTitle(), fcmContent.getBody());

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try{
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
        catch (InterruptedException e){
            logger.debug("got interrupted!");
            throw new InterruptedException();
        }
        catch (ExecutionException e){
            logger.debug("execution error!");
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/test")
    public @ResponseBody ResponseEntity<String> send() throws JSONException, InterruptedException  {
        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJsonTest();

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try{
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
        catch (InterruptedException e){
            logger.debug("got interrupted!");
            throw new InterruptedException();
        }
        catch (ExecutionException e){
            logger.debug("execution error!");
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }
}
