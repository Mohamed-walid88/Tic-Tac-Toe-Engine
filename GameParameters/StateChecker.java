public class StateChecker {

    public GameState getGameState(char[][] board, char Player1) {

        // 1. Check Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '.' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return (board[i][0] == Player1) ? GameState.Player1Win : GameState.Player2Win;
            }
        }

        // 2. Check Columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '.' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return (board[0][i] == Player1) ? GameState.Player1Win : GameState.Player2Win;
            }
        }

        // 3. Check Diagonals
        // Top-left to bottom-right
        if (board[0][0] != '.' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return (board[0][0] == Player1) ? GameState.Player1Win : GameState.Player2Win;
        }
        // Bottom-left to top-right
        if (board[2][0] != '.' && board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            return (board[2][0] == Player1) ? GameState.Player1Win : GameState.Player2Win;
        }

        // 4. Check for TIE (if board is full)
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    isFull = false;
                    break;
                }
            }
        }
        if (isFull) return GameState.TIE;

        // 5. Game is still going
        return GameState.STILL;
    }
}