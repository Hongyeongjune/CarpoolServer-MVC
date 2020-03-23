package kr.ac.skuniv.project.carpooluser.service.fcm;

import kr.ac.skuniv.project.carpooluser.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson(String token, String title, String content) throws JSONException {

        //String sampleData[] = {"c2emMi0JGmI:APA91bFGZY7kXbR_Dt143_KyTxGATNTyUX_Q3JS8IzvKuP651-_VGTRrEdQIiB6JnlAkR0FkkkBoGDADR96gM49VvMoJvUuJu7fIviM1vCdQab2DVvcsm9mN6ieQ9xONeGqgi9m70nzv"};

        String tokenData[] = {token};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<tokenData.length; i++){
            tokenlist.add(tokenData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", tokenData);

        JSONObject notification = new JSONObject();
        notification.put("title", title);
        notification.put("body", content);

        body.put("notification", notification);

        System.out.println(body.toString());

        return body.toString();
    }

    public static String PeriodicNotificationJsonTest() throws JSONException {
        LocalDate localDate = LocalDate.now();

        String sampleData[] = {"c2emMi0JGmI:APA91bFGZY7kXbR_Dt143_KyTxGATNTyUX_Q3JS8IzvKuP651-_VGTRrEdQIiB6JnlAkR0FkkkBoGDADR96gM49VvMoJvUuJu7fIviM1vCdQab2DVvcsm9mN6ieQ9xONeGqgi9m70nzv"};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title","hello!");
        notification.put("body","Today is "+localDate.getDayOfWeek().name()+"!");

        body.put("notification", notification);

        System.out.println(body.toString());

        return body.toString();
    }
}
