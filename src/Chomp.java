import java.util.Random;
import java.util.Scanner;

//Spiel Chomp
public class Chomp extends Game {
    public GameMove gamemove;
    public Chomp(){
        player1 =  new Player("Jonas", false);

        //Computer oder Spieler
        System.out.println("Choose your Opponent(0 = player, 1 = computer)");
        Scanner comp = new Scanner(System.in);
        int com = comp.nextInt();
        boolean computer = false;
        if (com == 0) {
            computer = false;
        }
        else {
            computer = true;
        }
        player2 =  new Player("Filip", computer);
        player1.setID(1);
        player2.setID(2);

        //Spielfeldgröße
        System.out.println("Enter 2 int for size of playingfield (Max 16 16)");
        Scanner scanfield = new Scanner(System.in);
        int SizeX = scanfield.nextInt();
        int SizeY = scanfield.nextInt(); // Was wenn >16
        field = new Field(SizeX,SizeY);
        ended = false;
        winner = null;
    }




    // x Horizontal, y Vertikal
    @Override
    public void round() {
        play(player1);
        play(player2);
    }

    @Override
    public void run() {

        while (!ended) {
            round();
        }
    }

    public Boolean inBounds(int x, int y) {

        if (x < 0 || x >= 16 || y < 0 || y >= 16 || field.getValue(x,y) == 1) {
           return false;
        }
        return true;
    }

    public void move(int x, int y){

                for (int j = y; j < field.getSizeY(); j++) {
                    for (int i = x; i < field.getSizeX(); i++) {
                        field.setValue(i, j, 1);
                    }

                }
                field.draw();

    }

    @Override
    public void play(Player player) {

        if(!ended) {
            end(player);
        }

        int x = 16;
        int y = 16;
        int z = 0;

        //Player moves
        if (!ended && !player.isComputer()) {
            while(!inBounds(x, y)) {
                z = z + 1;
                if(z>1){
                    System.out.println("Out of bounds.");
                }

                System.out.println(player.getName() + "'s input (Enter 2 int): ");
                Scanner scan = new Scanner(System.in);
                x = scan.nextInt();
                y = scan.nextInt();
            }

            move(x,y);

    }

        //Computer moves
        if (!ended && player.isComputer()) {
            if (field.getValue(0, 1) == 1 && field.getValue(1,0) == 0) {
                move(1, 0);
            }

            else if (field.getValue(1,0) == 1 && field.getValue(0,1) == 0){
                move(0,1);
            }

            else if (field.getValue(0, 1) == 1 && field.getValue(1,0) == 1) {
                move(0, 0);
            }

            else {
                Random random = new Random();
                int randomInt = random.nextInt(2);

                if (randomInt == 1) {
                    int j = 0;
                    for (j = 0; j < field.getSizeX(); j++) {
                        if (field.getValue(j, 0) == 1) {
                            break;
                        }

                    }
                    move(j - 1, 0);
                }
                else
                {
                    int s = 0;
                    for (s = 0; s < field.getSizeY(); s++) {
                        if (field.getValue(0, s) == 1) {
                            break;
                        }

                    }
                    move(0, s-1);

                }
            }
        }

    }
    //check if end
    public void end(Player player){
        if (field.getValue(0,0) == 1) {
            ended = true;
            winner = player;
            System.out.println(winner.getName() + " won");
        }
    }



}
