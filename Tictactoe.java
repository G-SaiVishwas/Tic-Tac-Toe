import java.util.*;

class GameBoard {
    private char grid[][];
    private int size = 3;
    private char symbol1, symbol2;
    private int moves;

    public final static int RESULT_1 = 1;
    public final static int RESULT_2 = 2;
    public final static int RESULT_3 = 3;
    public final static int RESULT_4 = 4;
    public final static int RESULT_5 = 5;

    public GameBoard(char symbol1, char symbol2) {
        grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
    }

    public int action(char symbol, int a, int b) {
        if (a < 0 || a >= size || b < 0 || b >= size || grid[a][b] != ' ') {
            return RESULT_5;
        }
        grid[a][b] = symbol;
        moves++;
        if (grid[a][0] == grid[a][1] && grid[a][0] == grid[a][2]) {
            return symbol == symbol1 ? RESULT_1 : RESULT_2;
        }
        if (grid[0][b] == grid[1][b] && grid[0][b] == grid[2][b]) {
            return symbol == symbol1 ? RESULT_1 : RESULT_2;
        }
        if (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
            return symbol == symbol1 ? RESULT_1 : RESULT_2;
        }
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) {
            return symbol == symbol1 ? RESULT_1 : RESULT_2;
        }
        if (moves == size * size) return RESULT_3;
        return RESULT_4;
    }

    public void display() {
        System.out.println("-------------------------------");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("| " + grid[i][j] + " |");
            }
            System.out.println();
        }
    }
}

class Participant {
    private String title;
    private char sign;

    public Participant(String title, char sign) {
        setTitle(title);
        setSign(sign);
    }

    public void setTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setSign(char sign) {
        if (sign != '\0') {
            this.sign = sign;
        }
    }

    public char getSign() {
        return this.sign;
    }
}

public class Tictactoe{
    private Participant p1, p2;
    private GameBoard board;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Tictactoe m = new Tictactoe();
        m.begin();
    }

    public void begin() {
        p1 = playerDetails(1);
        p2 = playerDetails(2);

        while (p1.getSign() == p2.getSign()) {
            System.out.println("Symbol already Taken !! Pick another symbol !!");
            char sign = input.next().charAt(0);
            p2.setSign(sign);
        }

        board = new GameBoard(p1.getSign(), p2.getSign());
        boolean turn = true;
        int result = GameBoard.RESULT_4;

        while (result == GameBoard.RESULT_4 || result == GameBoard.RESULT_5) {
            if (turn) {
                System.out.println("Player 1 - " + p1.getTitle() + "'s turn");
                System.out.println("Enter x: ");
                int x = input.nextInt();
                System.out.println("Enter y: ");
                int y = input.nextInt();
                result = board.action(p1.getSign(), x, y);
                if (result != GameBoard.RESULT_5) {
                    turn = false;
                    board.display();
                } else {
                    System.out.println("Invalid Move !! Try Again !! ");
                }
            } else {
                System.out.println("Player 2 - " + p2.getTitle() + "'s turn");
                System.out.println("Enter x: ");
                int x = input.nextInt();
                System.out.println("Enter y: ");
                int y = input.nextInt();
                result = board.action(p2.getSign(), x, y);
                if (result != GameBoard.RESULT_5) {
                    turn = true;
                    board.display();
                } else {
                    System.out.println("Invalid Move !! Try Again !! ");
                }
            }
        }

        if (result == GameBoard.RESULT_1) {
            System.out.println("Player 1 - " + p1.getTitle() + " wins !!");
        } else if (result == GameBoard.RESULT_2) {
            System.out.println("Player 2 - " + p2.getTitle() + " wins !!");
        } else {
            System.out.println(" !! DRAW !!");
        }
    }

    private Participant playerDetails(int num) {
        System.out.println("Enter Player " + num + " name: ");
        String title = input.next();
        System.out.println("Enter Player " + num + " symbol: ");
        char sign = input.next().charAt(0);
        Participant p = new Participant(title, sign);
        return p;
    }
}
