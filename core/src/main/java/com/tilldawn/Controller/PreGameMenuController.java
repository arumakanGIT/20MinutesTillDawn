package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.View.PreGameMenu;

public class PreGameMenuController {
    private final PreGameMenu menu;

    public PreGameMenuController(PreGameMenu menu) {
        this.menu = menu;
        initialize();
    }

    private void initialize() {

        // avatar left

        menu.getAvatarLeft().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.setIndex((menu.getIndex() + menu.getAvatars().size() - 1) % menu.getAvatars().size());
//                float oldWidth = menu.getAvatar().getPrefWidth();
//                float oldHeight = menu.getAvatar().getPrefHeight();
                menu.update();
//                menu.getAvatar().setSize(menu.getAvatar().getPrefWidth(), menu.getAvatar().getPrefHeight());
//                menu.getAvatar().setPosition(menu.getAvatar().getX() - ((menu.getAvatar().getPrefWidth() - oldWidth) / 2), menu.getAvatar().getY() - ((menu.getAvatar().getPrefHeight() - oldHeight) / 2));
            }
        });

        // avatar right

        menu.getAvatarRight().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                menu.setIndex((menu.getIndex() + menu.getAvatars().size() + 1) % menu.getAvatars().size());
//                float oldWidth = menu.getAvatar().getPrefWidth();
//                float oldHeight = menu.getAvatar().getPrefHeight();
                menu.update();
//                menu.getAvatar().setSize(menu.getAvatar().getPrefWidth(), menu.getAvatar().getPrefHeight());
//                menu.getAvatar().setPosition(menu.getAvatar().getX() - ((menu.getAvatar().getPrefWidth() - oldWidth) / 2), menu.getAvatar().getY() - ((menu.getAvatar().getPrefHeight() - oldHeight) / 2));
            }
        });


    }
}
