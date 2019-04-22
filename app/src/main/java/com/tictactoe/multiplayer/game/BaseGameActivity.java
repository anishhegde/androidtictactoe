package com.tictactoe.multiplayer.game;

import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class BaseGameActivity extends AppCompatActivity {
    private static final String ID_IDENTIFIER = "id";

    public int getIntIdFromString(String id) {
        return getResources().getIdentifier(id, ID_IDENTIFIER, getPackageName());
    }

    public void animateButton(Button button) {
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        button.startAnimation(animShake);
    }
}
