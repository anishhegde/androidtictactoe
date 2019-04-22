package com.tictactoe.multiplayer.game;

import android.support.v7.app.AppCompatActivity;

public class BaseGameActivity extends AppCompatActivity {
    private static final String ID_IDENTIFIER = "id";

    public int getIntIdFromString(String id) {
        return getResources().getIdentifier(id, ID_IDENTIFIER, getPackageName());
    }
}
