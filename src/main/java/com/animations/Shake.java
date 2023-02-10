package com.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition translateTransition;

    public Shake(Node node)
    {
        translateTransition = new TranslateTransition(Duration.millis(70), node);
        translateTransition.setFromX(0f);
        translateTransition.setFromY(0f);
        translateTransition.setByX(5);
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true);
    }

    public void play_animation()
    {
        translateTransition.playFromStart();
    }
}
