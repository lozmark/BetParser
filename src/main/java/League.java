
import java.util.List;

public class League {
    private String name;
    private List<Event> events;

    public String getName() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public League(String name,List<Event> list) {
        this.name = name;
        this.events = list;
    }
}
