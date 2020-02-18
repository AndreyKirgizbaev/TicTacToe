import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] field = new char[3][3];

        int xs = 0;
        int os = 0;
        boolean xxx = false;
        boolean ooo = false;

        char[] players = {'X', 'O'};
        int currentPlayer = 0;

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                field[i][k] = ' ';
            }
        }

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("| %c %c %c |", field[i][0], field[i][1], field[i][2]));
        }
        System.out.println("---------");

        String result;

        while (true) {

            System.out.print("Enter the coordinates: ");

            int row;
            int col;

            try {
                col = scanner.nextInt();
                row = scanner.nextInt();
                if (!(col >= 1 && col <= 3) || !(row >= 1 && row <= 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (field[3 - row][col - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    field[3 - row][col - 1] = players[currentPlayer%2];
                    currentPlayer++;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }

            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.println(String.format("| %c %c %c |", field[i][0], field[i][1], field[i][2]));
            }
            System.out.println("---------");

            // check if row or column or any of diagonals is win for someone
            for (int i = 0; i < 3; i++) {
                int rw = 0;
                int clm = 0;
                int mDiag = 0;
                int aDiag = 0;

                for (int j = 0; j < 3; j++) {
                    rw += field[i][j];
                    clm += field[j][i];
                    mDiag += field[j][j];
                    aDiag += field[j][2-j];
                }

                // ASCII value for X is 88 (X+X+X is 264)
                // ASCII value for O is 79 (O+O+O is 237)
                xxx = xxx || rw == 264 || clm == 264 || mDiag == 264 || aDiag == 264;
                ooo = ooo || rw == 237 || clm == 237 || mDiag == 237 || aDiag == 237;
            }

            result = Math.abs(xs-os) > 1 || xxx && ooo ? "Impossible"
                    : xxx ? "X wins"
                    : ooo ? "O wins"
                    : xs + os == 9 ? "Draw"
                    : "Game not finished";

            if (result.equals("X wins") || result.equals("O wins") || result.equals("Draw")){
                break;
            }
        }

        System.out.println(result);
    }
}
