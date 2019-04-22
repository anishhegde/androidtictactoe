package com.tictactoe.multiplayer.game.GameView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tictactoe.multiplayer.game.R;
import com.tictactoe.multiplayer.game.GameEngine.GameManager;

import static com.tictactoe.multiplayer.game.GameEngine.GameManager.BLOCK_LENGTH;

public class GameActivity extends BaseGameActivity implements GameManager.GameListener {


    String[] buttonIds = {"block_00", "block_01", "block_02",
            "block_10", "block_11", "block_12",
            "block_20", "block_21", "block_22"};

    GameManager gameManager;
    Button restartButton;
    Button topTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initViews();
        initGameManager();
        initGameBlocks();
    }

    private void initViews() {
        topTextButton = findViewById(R.id.tv_top_title);
        restartButton = findViewById(R.id.btn_restart);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGameBlocks();
                gameManager.restart();
            }
        });
    }

    private void initGameManager() {
        gameManager = GameManager.getInstance();
        gameManager.init(this);
    }

    View.OnClickListener blockClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gameManager.onBlockClick(getApplicationContext(), view);
        }
    };

    private void initGameBlocks() {
        for (int i = 0; i < BLOCK_LENGTH; i++) {
            int id = getIntIdFromString(buttonIds[i]);
            final Button blockButton = findViewById(id);
            blockButton.setOnClickListener(blockClickListener);
            animateButton(blockButton);
        }
    }

    private void resetGameBlocks() {
        for (int i = 0; i < BLOCK_LENGTH; i++) {
            int id = getIntIdFromString(buttonIds[i]);
            final Button blockButton = findViewById(id);
            blockButton.setText("");
            animateButton(blockButton);
        }
    }

    @Override
    public void onAllBlocksPlayed() {
        showWinnerDialog(getString(R.string.end));
    }

    @Override
    public void onGameEvent(String message) {
        showWinnerDialog(message);
    }

    @Override
    public void onTurnChange(String turn) {
        if(turn.equalsIgnoreCase(gameManager.GAME_OVER)) {
            topTextButton.setText(getString(R.string.game_over));
        } else {
            topTextButton.setText(turn + getString(R.string.turn_string));
        }
    }

    private void showWinnerDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setMessage(message)
                .setTitle(R.string.game_over);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
