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
        else
        {
            computer = true;
        }
        player2 =  new Player("Filip", computer);
        player1.setID(1);
        player2.setID(2);
        //Spielfeldgröße
        System.out.println("Enter 2 int for size of playingfield ( <16 )");
        Scanner scanfield = new Scanner(System.in);
        int SizeX = scanfield.nextInt();
        int SizeY = scanfield.nextInt();
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
        if (winner != null) {
            System.out.println(winner.getName() + " won!");
        }
    }

    public Boolean inBounds(int x, int y) {

        if (x < 0 || x >= field.getSizeX() || y < 0 || y >= field.getSizeY() || field.getValue(x,y) == 1) {
           return false;
        }
        return true;
    }
    @Override
    public void play(Player player) {

        /*boolean comp = player.isComputer();
        if (comp) {
            System.out.println("Computer");
        }*/

        int x = 16;
        int y = 16;
        int z = 0;
        if (!ended) {
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

            if (inBounds(x, y)) {
            for (int j = y; j < field.getSizeY(); j++) {
                for (int i = x; i < field.getSizeX(); i++) {
                    field.setValue(i, j, 1);
                }

            }
            field.draw();
        }
    }
        end();
        boolean comp = player.isComputer();
        if (comp) {
            System.out.println("Computer");
        }

    }

    public void end(){
        if (field.getValue(0,0) == 1) {
            ended = true;
            winner = player1;
            System.out.println(winner.getName() + " win!");
        }
    }



}
