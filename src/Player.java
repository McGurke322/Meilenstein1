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

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComputer(){
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    public int getID() {
        return id;
    }
}

