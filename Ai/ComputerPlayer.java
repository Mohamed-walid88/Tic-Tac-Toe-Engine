import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ComputerPlayer {
    private final int INF = Integer.MAX_VALUE - 1;
    private final char side, opponent;
    private DIFFICULTY difficulty;
    private Map<Integer, Integer> dp = new HashMap<>();
    private StateChecker stateChecker;

    public ComputerPlayer(char side, DIFFICULTY difficulty) {
        this.side = side;
        this.difficulty = difficulty;
        this.stateChecker = new StateChecker();

        if (side == 'X') opponent = 'O';
        else opponent = 'X';
    }

    public void computerMove(char[][] board) {

        if (difficulty == DIFFICULTY.EASY) {
            makeRandomMove(board);
            return;
        }

        int bestVal = -INF;
        int bestRow = -1;
        int bestCol = -1;

        dp.clear();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    // 1. Make the move
                    board[i][j] = side;

                    // 2. Evaluate it (It is now opponent's turn, so isMaximizing = false)
                    int moveVal = minimax(board, false, 0);

                    // 3. Undo the move
                    board[i][j] = '.';

                    if (moveVal > bestVal) {
                        bestRow = i;
                        bestCol = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        if (bestRow != -1) {
            board[bestRow][bestCol] = side;
        }
    }

    private int minimax(char[][] board, boolean isMaximizing, int depth) {
        int state = encodeBoard(board) * 2 + (isMaximizing ? 1 : 0);
        if (dp.containsKey(state)) return dp.get(state);

        GameState result = stateChecker.getGameState(board, side, opponent);

        // Terminal States
        if (result == GameState.Player1Win)  // side wins (since side is Player1)
            return 10 - depth;

        if (result == GameState.Player2Win)  // opponent wins (since opponent is Player2)
            return -10 + depth;

        if (result == GameState.TIE) {
            dp.put(state, 0);
            return 0;
        }

        int bestScore;

        if (isMaximizing) {
            bestScore = -INF;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == '.') {
                        board[row][col] = side;
                        int score = minimax(board, false, depth + 1);
                        bestScore = Math.max(bestScore, score);
                        board[row][col] = '.';
                    }
                }
            }
        } else {
            bestScore = INF;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == '.') {
                        board[row][col] = opponent;
                        int score = minimax(board, true, depth + 1);
                        bestScore = Math.min(bestScore, score);
                        board[row][col] = '.';
                    }
                }
            }
        }

        dp.put(state, bestScore);
        return bestScore;
    }

    private void makeRandomMove(char[][] board) {
        Random rand = new Random();
        int emptyCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') emptyCells++;
            }
        }

        if (emptyCells == 0) return;  // No moves possible

        while (true) {
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            if (board[row][col] == '.') {
                board[row][col] = side;
                break;
            }
        }
    }

    private int encodeBoard(char[][] board) {
        int state = 0, p = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                int v = 0;

                if (board[i][j] == side) v = 1;        // AI
                else if (board[i][j] == opponent) v = 2; // Human

                state += v * p;
                p *= 3;
            }
        }

        return state;
    }

}