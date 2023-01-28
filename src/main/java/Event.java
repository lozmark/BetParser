
public class Event {
    private String first_team;
    private String second_team;
    private String date;

    public Event(String first_team, String second_team, String date) {
        this.first_team = first_team;
        this.second_team = second_team;
        this.date = date;
    }
    public Event(String first_team, String date) {
        this.first_team = first_team;
        this.date = date;
    }

    @Override
    public String toString() {
        if (second_team==null){
            return getFirst_team() + " " + getDate();
        }
        else {
            return getFirst_team() + " - " + getSecond_team() + " " + getDate();
        }
    }

    public String getFirst_team() {
        return first_team;
    }

    public String getSecond_team() {
        return second_team;
    }

    public String getDate() {
        return date;
    }
}
