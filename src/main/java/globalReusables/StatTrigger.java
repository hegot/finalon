package globalReusables;

import database.setting.DbSettingHandler;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatTrigger {

    private final static String code = "finalon2050";
    private final static String apiEndpoint = "https://www.finstanon.com/app2/apiforstat.php";
    private static Map<CallTypes, Boolean> called = getCalled();


    private static Map<CallTypes, Boolean> getCalled() {
        Map<CallTypes, Boolean> called = new HashMap<>();
        called.put(CallTypes.program_started_times, false);
        called.put(CallTypes.formula_customization_times, false);
        return called;
    }

    private static Boolean wasCalled(CallTypes type) {
        Boolean wasCalled = called.get(type);
        if (wasCalled != null) {
            if (called.get(type).equals(true)) {
                return true;
            } else {
                called.put(type, true);
                return false;
            }
        } else {
            return false;
        }

    }

    public static void call(CallTypes type) {
        if (!wasCalled(type)) {
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(apiEndpoint);

            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            DbSettingHandler dbSettingHandler = new DbSettingHandler();
            String id = dbSettingHandler.getSetting(Setting.appId);
            params.add(new BasicNameValuePair("id", id));
            params.add(new BasicNameValuePair("event", type.toString()));
            params.add(new BasicNameValuePair("code", code));
            try {
                httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    try (InputStream instream = entity.getContent()) {
                        String text = IOUtils.toString(instream, StandardCharsets.UTF_8.name());
                        System.out.println(text);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }
        }
    }
}
