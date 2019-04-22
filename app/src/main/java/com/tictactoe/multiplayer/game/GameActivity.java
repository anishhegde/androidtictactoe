package com.tictactoe.multiplayer.game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.tictactoe.multiplayer.game.GameManager.BLOCK_LENGTH;

public class GameActivity extends BaseGameActivity implements GameManager.GameListener {


    String[] buttonIds = {"block_00", "block_01", "block_02",
            "block_10", "block_11", "block_12",
            "block_20", "block_21", "block_22"};

    GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initGameManager();
        initGameBlocks();
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
        }
    }

    @Override
    public void onAllBlocksPlayed() {
        Toast.makeText(this, "end", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGameEvent(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
