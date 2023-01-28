import java.util.List;

public class Sport {
    private String name;
    private List<League> leagues;
    public Sport(String name, List<League> list) {
        this.name = name;
        this.leagues = list;
    }
    public String getLine(){
        StringBuilder builder = new StringBuilder(name + "\n");
        for (League league : leagues){
            builder.append( league.getName() + "\n");
            for (Event event : league.getEvents()){
                builder.append(event.toString() + "\n");
            }
        }
        return builder.toString();
    }
}
