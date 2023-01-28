import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static HttpsURLConnection connection;

    static {
        try {
            connection = (HttpsURLConnection)
                    new URL("https://ad.betcity.ru/d/off/events").openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getLine() throws IOException, ParseException {
        BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
        Reader reader = new InputStreamReader(bis);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader);
        JSONObject reply = (JSONObject) object.get("reply");
        JSONObject jsonsports = (JSONObject) reply.get("sports");
        List<Sport> sports = getSports(jsonsports);
        for (Sport sport : sports){
            System.out.println(sport.getLine());
        }
    }
    private static List<Sport> getSports(JSONObject json_sports){
        List<Sport> line = new ArrayList<>();
        for (Object object_sports : json_sports.values()) {
            JSONObject sport = (JSONObject) object_sports;
            JSONObject jsonchmps = (JSONObject) sport.get("chmps");
            List<League> chmps = getChmps(jsonchmps);
            line.add(new Sport(sport.get("name_sp").toString(),chmps));
        }
        return line;
    }

    private static  List<League> getChmps(JSONObject json_chmps){
        List<League> chmps = new ArrayList<>();
        for (Object object_chmps : json_chmps.values()){
            JSONObject chmp = (JSONObject) object_chmps;
            JSONObject jsonevents = (JSONObject) chmp.get("evts");
            List<Event> events = getEvents(jsonevents);
            chmps.add(new League(chmp.get("name_ch").toString(),events));
        }
        return chmps;

    }
    private static List<Event> getEvents(JSONObject jsonevents){
        List<Event> events = new ArrayList<>();
        for (Object object_event : jsonevents.values()) {
            JSONObject event = (JSONObject) object_event;
            if (event.get("name_at") == null){
                events.add(new Event(event.get("name_ht").toString(),
                        event.get("date_ev_str").toString()));
            }
            else {
                events.add(new Event(event.get("name_ht").toString(),event.get("name_at").toString(),
                        event.get("date_ev_str").toString()));
            }
        }
        return events;
    }
}
