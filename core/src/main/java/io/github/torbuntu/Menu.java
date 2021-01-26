/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.torbuntu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.mini2Dx.core.Graphics;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.viewport.FitViewport;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.gdx.Input.Keys;

/**
 *
 * @author tor
 */
public class Menu extends BasicGameScreen {

    public static int ID = 0;

    FitViewport viewport;
    HashMap<String, String> gameList;
    ArrayList<String> titles;

    int index = 0;

    Menu(HashMap<String, String> gameList) {
        this.gameList = gameList;
        titles = new ArrayList<>();
        gameList.forEach((title, path) -> {
            titles.add(title);
        });
    }

    @Override
    public void initialise(GameContainer gc) {
        gameList.forEach((e, x) -> {
            System.out.println(e);
        });

        viewport = new FitViewport(360, 180);
    }

    @Override
    public void update(GameContainer gc, ScreenManager sm, float f) {
        if (Mdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            Mdx.platformUtils.exit(true);
        }

        if (Mdx.input.isKeyJustPressed(Keys.RIGHT)) {
            if (index < gameList.size() - 1) {
                index++;
            }
        }
        if (Mdx.input.isKeyJustPressed(Keys.LEFT)) {
            if (index > 0) {
                index--;
            }
        }

        if (Mdx.input.isKeyJustPressed(Keys.ENTER)) {
            String path = gameList.get(titles.get(index));

            try {
                Arrays.asList(Mdx.files.external(path).list()).forEach(item -> {
                    if (!item.isDirectory()) {
                        if ("jar".equals(item.extension())) {
                            var game = new File(path + "/" + item.name());
                            String[] command = new String[3];
                            command[0] = "java";
                            command[1] = "-jar";
                            command[2] = game.getName();
                            try {
                                Runtime.getRuntime().exec(command, null, game.getParentFile());
                            } catch (IOException ex) {
                                Mdx.log.error("Menu", ex.getMessage());
                            }
                        }
                    }
                });
            } catch (IOException ex) {
                Mdx.log.error("Menu", ex.getMessage());
            }

        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        viewport.apply(g);
        g.drawString("Menu", 0, 0, 360, 1);
        g.drawString("Selection:\n" + titles.get(index), 0, 8);
    }

    @Override
    public int getId() {
        return this.ID;
    }

}
