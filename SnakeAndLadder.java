import java.util.*;
/*class Player{

    String playerName;
    int positon;
    
    Player(){
        positon=0;
    }
    public void setName(String name)
    {
        playerName=name;
        System.out.println("Palyer name : "+playerName);
    }
}*/
/*public class SnakeAndLadder {
    public static void main(String[] args) {
        int noOfPlayer;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter How Many Player : ");
        noOfPlayer=scan.nextInt();
        //createPlayers(noOfPlayer);
        scan.close();

    }*/
/*static void createPlayers(int noOfPlayer)
{
    Scanner scan = new Scanner(System.in);
    Player[] players=new Player[noOfPlayer];
    String name;
    for(int i=0;i<noOfPlayer;i++)
    {
        players[i] = new Player();
        System.out.println("Enter Player "+(i+1)+" Name : ");
        name=scan.nextLine();
        players[i].setName(name);
        
    }
    scan.close();
    //startGame(players);
}
//static void startGame(players)

}*/

public class SnakeAndLadder {
    /**
     * @param args
     */
    public static void main(String[] args) {
        int noOfPlayer;
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Enter How Many Player : ");
        noOfPlayer = scan.nextInt();
        scan.nextLine();
        String[] player = new String[noOfPlayer];
        int[] playerPosition = new int[noOfPlayer];
        for (int i = 0; i < noOfPlayer; i++) {
            System.out.print("\nEnter Player " + (i + 1) + " Name : ");
            player[i] = scan.nextLine();
            playerPosition[i] = 0;
        }
        int i = 0;
        while (true) {
            System.out.print("\n"+player[i] + " Turn (Press Enter to Roll Dice) : ");
            scan.nextLine();
            int randomNumber = random.nextInt(6);
            System.out.print(randomNumber + 1);

            int newPosition = isSnakeOrLadder(playerPosition[i], randomNumber+1);
            playerPosition[i] = newPosition;

            if (playerPosition[i] == 100) {
                System.out.println(player[i] + " Wins");
                break;
            } else {
                if (i == 0) {
                    System.out.println("\n"+player[i] + " Position :" + playerPosition[i]);
                    System.out.println("\n"+player[i + 1] + " Position :" + playerPosition[i + 1]);
                    i++;
                } else {
                    System.out.println("\n"+player[i - 1] + " Position :" + playerPosition[i - 1]);
                    System.out.println("\n"+player[i] + " Position :" + playerPosition[i]);
                    i--;
                }
            }

        }
        scan.close();
    }

    static int isSnakeOrLadder(int playerPosition, int randomNumber) {
        int[] snake$ladderStart = { 4, 13, 27, 33, 40, 42, 43, 50, 54, 62, 66, 74, 76, 89, 99 };
        int[] snake$ladderEnd = { 25, 46, 5, 49, 3, 63, 18, 69, 31, 81, 45, 92, 58, 52, 41 };
        int currerntPosition = playerPosition + randomNumber;
        if(currerntPosition==100)
         return currerntPosition;
        if (currerntPosition < 100) 
        {
            for (int i = 0; i < snake$ladderStart.length; i++)
            {
                if (currerntPosition == snake$ladderStart[i])
                    return snake$ladderEnd[i];
            }   
        }
        return currerntPosition;
    }
}