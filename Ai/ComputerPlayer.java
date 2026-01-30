import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ComputerPlayer {
    private final int INF = Integer.MAX_VALUE - 1;
    private final char side, opponent;
    private DIFFICULTY difficulty;
    private Map<Integer, Integer> dp = new HashMap<>();
    private StateChecker stateChecker;
    private Random rand = new Random();

    public ComputerPlayer(char side, DIFFICULTY difficulty) {
        this.side = side;
        this.difficulty = difficulty;
        this.stateChecker = new StateChecker();

        if (side == 'X') opponent = 'O';
        else opponent = 'X';
    }

    public void computerMove(char[][] board) {
        switch (difficulty) {
            case EASY:
                makeRandomMove(board);
                break;
            case MEDIUM:
                makeMediumMove(board);
                break;
            case IMPOSSIBLE:
                makeHardMove(board);
                break;
        }
    }

    private void makeMediumMove(char[][] board) {
        // 1. Always take immediate win if available
        if (tryWinningMove(board)) return;

        // 2. Always block opponent's immediate win
        if (tryBlockingMove(board)) return;

        // 3. 60% chance of optimal move, 40% random mistake
        if (rand.nextDouble() < 0.6) {
            makeHardMove(board);
        } else {
            makeRandomMove(board);
        }
    }

    private boolean tryWinningMove(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    board[i][j] = side;
                    if (stateChecker.getGameState(board, side, opponent) == GameState.Player1Win) {
                        return true; // Winning move placed
                    }
                    board[i][j] = '.'; // Undo
                }
            }
        }
        return false;
    }

    private boolean tryBlockingMove(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    board[i][j] = opponent;
                    // If opponent would win next turn, block it
                    if (stateChecker.getGameState(board, opponent, side) == GameState.Player1Win) {
                        board[i][j] = side; // Place block
                        return true;
                    }
                    board[i][j] = '.'; // Undo
                }
            }
        }
        return false;
    }

    private void makeHardMove(char[][] board) {
        int bestVal = -INF;
        int bestRow = -1;
        int bestCol = -1;

        dp.clear();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    board[i][j] = side;
                    int moveVal = minimax(board, false, 0);
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

        if (result == GameState.Player1Win)
            return 10 - depth;
        if (result == GameState.Player2Win)
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
        List<int[]> emptyCells = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') emptyCells.add(new int[]{i, j});
            }
        }

        if (!emptyCells.isEmpty()) {
            int[] move = emptyCells.get(rand.nextInt(emptyCells.size()));
            board[move[0]][move[1]] = side;
        }
    }

    private int encodeBoard(char[][] board) {
        int state = 0, p = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int v = 0;
                if (board[i][j] == side) v = 1;
                else if (board[i][j] == opponent) v = 2;
                state += v * p;
                p *= 3;
            }
        }
        return state;
    }
}