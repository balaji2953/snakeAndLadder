import java.util.Scanner;
import java.util.Random;

class Player {
    private String playerName;
    private char playerSymbol;
    private int playerPositionValue, playerPositionRow, playerPositionColumn;

    Player() {
        playerPositionValue = 0;
        playerPositionRow = 0;
        playerPositionColumn = 0;
    }


    //Getter & Setter For Player Name
    public void setName(String name) {
        this.playerName = name;

    }
    public String getName() {
        return playerName;

    }

    //Getter & Setter For Player Symbol
    public void setSymbol(char sym) {

        this.playerSymbol = Character.toLowerCase(sym);
    }
    public char getSymbol()
    {
        return playerSymbol;
    }

    //Getter & Setter For Player Position
    public int getPlayerPosition() {
        return playerPositionValue;
    }
    public void setPlayerPosition(int pos) {
        this.playerPositionValue = pos;
    }

    //Getter & Setter For Player Row In Board
    public void setPlayerRow(int val) {
        this.playerPositionRow = val;
    }
    public int getPlayerRow() {
        return playerPositionRow;
    }

    //Getter & Setter For Player Column In Board
    public void setPlayerColumn(int val) {
        this.playerPositionColumn = val;
    }

    public int getPlayerColumn() {
        return playerPositionColumn;
    }

    public void display() {
        System.out.println("-------------------");
        System.out.println(playerName);
        System.out.println(playerSymbol);
        System.out.println(playerPositionValue);
        System.out.println(playerPositionRow);
        System.out.println(playerPositionColumn);
    }
}


                                // Main Class
public class SnakeAndLadder_1 {
    static Scanner input = new Scanner(System.in);


    static void findRowAndColumn(Player[] player, int pos, int i) {
        int quo = pos / 10;
        int rem = pos % 10;
        int board = 10;

        if (rem == 0) {
            if (quo % 2 == 0) {
                player[i].setPlayerRow(board - quo);
                player[i].setPlayerColumn(0);
            } else {
                player[i].setPlayerRow(board - quo);
                player[i].setPlayerColumn(9);
            }
        } else if (quo % 2 == 1) {
            player[i].setPlayerRow((board - quo) - 1);
            player[i].setPlayerColumn(board - rem);
        } else {
            player[i].setPlayerRow((board - quo) - 1);
            player[i].setPlayerColumn(rem - 1);
        }

    }

    

    static void printBoard(Player[] player, int NoOfPlayer) {
        int BOARD = 10;
        int[][] board = new int[BOARD][BOARD];
        int counter = BOARD * BOARD;
            for (int i = 0; i < BOARD; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < BOARD; j++) {
                        
                        board[i][j] = counter--;
                    }
                } else {
                    for (int j = BOARD - 1; j >= 0; j--) {
                        
                        board[i][j] = counter--;
                    }
                }
            }
            
            for (int a = 0; a < NoOfPlayer; a++) 
            {
            int row = player[a].getPlayerRow();
            int col = player[a].getPlayerColumn();
            board[row][col] = player[a].getSymbol();
            }

            
        
            for (int i = 0; i < BOARD; i++) {
                for (int j = 0; j < BOARD; j++) {
                    if(board[i][j]>100)
                    {
                        System.out.print(String.format("%10c", board[i][j]));
                        continue;
                    }
                    System.out.print(String.format("%10d", board[i][j]));
                }
                System.out.println("\n");
            }

    }

    static int isSnakeOrLadder(int playerPosition, int randomNumber) {
        int[] snake$ladderStart = { 4, 13, 27, 33, 40, 42, 43, 50, 54, 62, 66, 74, 76, 89, 99 };
        int[] snake$ladderEnd = { 25, 46, 5, 49, 3, 63, 18, 69, 31, 81, 45, 92, 58, 52, 41 };
        int currerntPosition = playerPosition + randomNumber;
        if (currerntPosition == 100)
            return currerntPosition;
        if (currerntPosition < 100) {
            int i = 0;
            while (currerntPosition >= snake$ladderStart[i]) {
                if (currerntPosition == snake$ladderStart[i]) {
                    return snake$ladderEnd[i];
                }
                i++;
            }
            return currerntPosition;
        }
        return playerPosition;
    }


    static int NoOfPlayer() {
        int NoOfPlayer;
        while (true) {
            System.out.print("Number Of Player (Max:4 Min:2) : ");
            NoOfPlayer = input.nextInt();
            if (NoOfPlayer > 4)
                System.out.println("Sorry ! Maximum 4 Player");
            else if (NoOfPlayer < 2)
                System.out.println("Sorry ! Minimun 2 Player");
            else
                break;
        }
        return NoOfPlayer;
    }


    static void getPlayerDetails(Player[] player, int NoOfPlayer) {
        for (int i = 0; i < NoOfPlayer; i++) {
            player[i] = new Player();

            System.out.println("Enter Player " + (i + 1) + " Name : ");
            player[i].setName(input.nextLine());

            //

            System.out.println("Select Symbol ( w, x, y, z) :");
            player[i].setSymbol(input.next().charAt(0));

            input.nextLine();
        }
        

    }

    static int randomNumber() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    static void startGame(Player[] player,int NoOfPlayer)
    {
        int i = 0;
        while (true) {
            System.out.print("\n" + player[i].getName() + " Turn (Press Enter to Roll Dice) : ");
            input.nextLine();

            int randomNumber = randomNumber();// Get random Number
            System.out.println(randomNumber);

            int newPosition = isSnakeOrLadder(player[i].getPlayerPosition(), randomNumber);
            player[i].setPlayerPosition(newPosition);
            findRowAndColumn(player, newPosition, i);
            player[i].display();
            // player[i].setPlayerColumn(findColumn(newPosition));

            if (newPosition == 100) {
                System.out.println(player[i].getName() + " Wins");
                printBoard(player, NoOfPlayer);
                break;
            }

            printBoard(player, NoOfPlayer);

            i++;
            if (i == NoOfPlayer)
                i = 0;
        }
    }

    


public static void main(String[] args) {

        // Get Number of Player From users
        int NoOfPlayer = NoOfPlayer();

        // Create Array For Players
        Player[] player = new Player[NoOfPlayer];

        // Get Player Details
        input.nextLine();
        getPlayerDetails(player, NoOfPlayer);

        startGame(player,NoOfPlayer);
        
}
}
