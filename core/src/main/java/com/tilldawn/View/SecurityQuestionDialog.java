package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.tilldawn.Models.GameAudioManager;
import com.tilldawn.Models.SFX;

import java.util.ArrayList;
import java.util.Arrays;

public class SecurityQuestionDialog extends Window {
    private final SelectBox<String> questionList;
    private final TextField answerField;
    private SecurityQuestionListener listener;

    public SecurityQuestionDialog(Skin skin) {
        super("", skin, "win3");
        top();

        ArrayList<String> questions = new ArrayList<>(Arrays.asList(
            "What is your mother's maiden name?",
            "What was the name of your first pet?",
            "What was the name of your first school?",
            "In what city were you born?",
            "What is the name of your favorite childhood friend?",
            "What was the make and model of your first car?",
            "What was the name of your favorite teacher?",
            "What street did you grow up on?",
            "What is the name of the hospital where you were born?",
            "What is your father's middle name?"));

        Label title = new Label("Security Question", skin, "chvyExprs_PINK_36");

        Label chooseLabel = new Label("Choose your security question from the list below", skin, "OpSa_WHITE_16");
        questionList = new SelectBox<>(skin, "default2");
        questionList.setItems(questions.toArray(new String[0]));
        questionList.setAlignment(Align.center);

        Label answerLabel = new Label("Answer your security question", skin, "OpSa_WHITE_16");
        answerField = new TextField("", skin, "default4");

        TextButton exit = new TextButton("Cancel", skin, "chvy_PINK_16");
        TextButton done = new TextButton("Done", skin, "chvy_PINK_16_ui");

        add(title).padTop(20).row();
        add(chooseLabel).padTop(40).row();
        add(questionList).padTop(10).padLeft(40).padRight(40).row();
        add(answerLabel).padTop(30).row();
        add(answerField).padTop(10).width(questionList.getPrefWidth()).height(questionList.getPrefHeight()).row();
        add(done).padTop(40).width(150).height(40).row();
        add(exit).padTop(10).padBottom(20);

        setModal(true);
        pack();

        exit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                answerField.setText("");
                questionList.setSelectedIndex(0);
                hide();
            }
        });

        done.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GameAudioManager.getInstance().playSound(SFX.click.getPath(), false, GameAudioManager.sfxVolume);
                int selectedQuestion = questionList.getSelectedIndex();
                String answer = answerField.getText().trim();
                if (listener != null)
                    listener.onDone(selectedQuestion, answer);
            }
        });
    }

    public void show(Stage stage) {
        if (!hasParent()) {
            setPosition(stage.getViewport().getWorldWidth() / 2f - this.getWidth() / 2f,
                stage.getViewport().getWorldHeight() / 2f - this.getHeight() / 2f);
            stage.addActor(this);
        }
    }

    public void hide() {
        answerField.setText("");
        this.remove();
    }

    public interface SecurityQuestionListener {
        void onDone(int selectedIndex, String answer);
    }

    public void setListener(SecurityQuestionListener listener) {
        this.listener = listener;
    }

    public void setForgetPasswordMode(int securityQuestionID) {
        questionList.setDisabled(true);
        questionList.setSelectedIndex(securityQuestionID);
    }
}
