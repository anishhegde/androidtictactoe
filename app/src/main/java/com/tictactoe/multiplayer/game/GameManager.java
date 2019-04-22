package com.tictactoe.multiplayer.game;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.tictactoe.multiplayer.game.StateManager.X_VALUE;

public class GameManager {

    public interface GameListener {
        void onAllBlocksPlayed();

        void onGameEvent(String message);

        void onTurnChange(String turn);
    }

    private static final int ROW_LENGTH = 3;
    private static final int COL_LENGTH = 3;
    public static final int BLOCK_LENGTH = 9;
    private final String LOG_ID_BLOCK = "block";
    public final String GAME_OVER = "Game Over";
    private GameListener gameListener;
    private int blockCounter = 0;

    /*
    Manages the state of the game:
    1. Toggles between O and X
    2. Manages the state
     */
    StateManager stateManager;

    /*
    Game board values:
     0 :  When the block is not set
     1 :  When the block is O
    -1 :  When the block is X
     */
    int[][] gameBoard = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

    private static GameManager gameManager = null;

    public static GameManager getInstance() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }

    public void init(GameListener listener) {
        gameListener = listener;
        initStateManager();
        initGameBoard();
        gameListener.onTurnChange(stateManager.getStateValue());
    }

    public void initGameBoard() {
        for (int i = 0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < COL_LENGTH; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    private void initStateManager() {
        stateManager = StateManager.getInstance();
    }

    public void onBlockClick(Context context, View view) {
        if (stateManager.getState() == StateManager.State.PLAY) {
            setBlockValue(view);
            setBoardValue(context, view);
            blockCounter++;
            if(stateManager.getState() != StateManager.State.END){
                stateManager.next();
                gameListener.onTurnChange(stateManager.getStateValue());
                checkBoardComplete();
            }
        }
    }

    private void checkBoardComplete() {
        if (blockCounter >= BLOCK_LENGTH) {
            stateManager.end();
            gameListener.onTurnChange(GAME_OVER);
            gameListener.onAllBlocksPlayed();
        }
    }

    private void setBoardValue(Context context, View view) {
        String viewName = context.getResources().getResourceEntryName(view.getId());
        // Get the last two numbers
        String substring = viewName.substring(Math.max(viewName.length() - 2, 0));
        int i = Character.getNumericValue(substring.charAt(0));
        int j = Character.getNumericValue(substring.charAt(1));
        setGameBoard(stateManager.getStateValue(), i, j);
        if (checkWinningCondition(stateManager.getStateValue(), i, j)) {
            gameListener.onGameEvent(stateManager.getStateValue() + " win");
            stateManager.end();
            gameListener.onTurnChange(GAME_OVER);
        }
    }

    private void setBlockValue(View view) {
        ((Button) view).setText(getBlockValue());
    }

    private boolean checkWinningCondition(String value, int row, int col) {
        int gameValue = getGameBoardValues(value);
        return (checkVerticalCondition(row, col, gameValue)
                || checkHorizontalCondition(row, col, gameValue)
                || checkDiagonalCondition(row, col, gameValue));
    }

    private boolean checkVerticalCondition(int row, int col, int gameValue) {
        boolean isVertical = true;
        for (int i = 0; i < ROW_LENGTH; i++) {
            if (gameValue != gameBoard[i][col]) {
                isVertical = false;
                break;
            }
        }
        return isVertical;
    }

    private boolean checkHorizontalCondition(int row, int col, int gameValue) {
        boolean isHorizontal = true;
        for (int i = 0; i < COL_LENGTH; i++) {
            if (gameValue != gameBoard[row][i]) {
                isHorizontal = false;
                break;
            }
        }
        return isHorizontal;
    }

    private boolean checkDiagonalCondition(int row, int col, int gameValue) {
        boolean isDiagonal = true;
        if (row == col + 1 || row == col - 1) {
            isDiagonal = false;
        } else if (row == col) {
            for (int i = 0; i < COL_LENGTH; i++) {
                if (gameValue != gameBoard[i][i]) {
                    isDiagonal = false;
                    break;
                }
            }
        } else {
            for (int i = 0; i < COL_LENGTH; i++) {
                if (gameValue != gameBoard[COL_LENGTH - i - 1][i]) {
                    isDiagonal = false;
                    break;
                }
            }
        }

        return isDiagonal;
    }

    public void restart() {
        blockCounter = 0;
        initGameBoard();
        stateManager.play();
        gameListener.onTurnChange(stateManager.getStateValue());
    }

    private void setGameBoard(String value, int row, int column) {
        gameBoard[row][column] = getGameBoardValues(value);

    }

    private int getGameBoardValues(String value) {
        if (value.equalsIgnoreCase(X_VALUE)) {
            return 1;
        } else {
            return -1;
        }
    }

    public String getBlockValue() {
        return stateManager.getStateValue();
    }

    private void printGameBoard() {
        for (int i = 0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < COL_LENGTH; j++) {
                Log.d(LOG_ID_BLOCK + i + " " + j, "" + gameBoard[i][j]);
            }
        }
    }

}
