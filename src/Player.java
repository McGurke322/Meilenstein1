//Spieler
public class Player {
    private String name;
    private boolean computer;
    private int id;

    public Player(String name, boolean computer) {
        this.name = name;
        this.computer = computer;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}

