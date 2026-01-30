import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board = {
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}
        };
        char player = 'X', computer = 'O';
        boolean playerMove = true;
        ComputerPlayer computerPlayer = new ComputerPlayer(computer, DIFFICULTY.IMPOSSIBLE);
        StateChecker checker = new StateChecker();

        while (checker.getGameState(board, player, computer) == GameState.STILL) {
            if (playerMove) {
                Scanner input = new Scanner(System.in);
                System.out.print("choose a row and column: ");
                int row = input.nextInt();
                int col = input.nextInt();
                if (board[row-1][col-1] != '.') {
                    System.out.println("Invalid move!");
                    continue;
                }
                board[row-1][col-1] = player;
                playerMove = false;
            }
            else {
                computerPlayer.computerMove(board);
                playerMove = true;
            }
            System.out.printf("""
                        %c | %c | %c
                        ____________
                        %c | %c | %c
                        ____________
                        %c | %c | %c
                        """, board[0][0], board[0][1], board[0][2], board[1][0], board[1][1], board[1][2],
                    board[2][0],  board[2][1], board[2][2]);
        }
        if (checker.getGameState(board, player, computer) == GameState.Player1Win) System.out.println("You win!");
        else if  (checker.getGameState(board, player, computer) == GameState.Player2Win) System.out.println("You lose!");
        else System.out.println("Draw!");
    }
}
