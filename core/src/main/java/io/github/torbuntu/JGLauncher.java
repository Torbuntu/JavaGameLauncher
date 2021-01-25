package io.github.torbuntu;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.assets.AssetManager;
import org.mini2Dx.core.files.ExternalFileHandleResolver;
import org.mini2Dx.core.files.LocalFileHandleResolver;
import org.mini2Dx.core.font.MonospaceGameFont;
import org.mini2Dx.core.game.ScreenBasedGame;
import org.mini2Dx.core.graphics.Texture;

public class JGLauncher extends ScreenBasedGame {

    public static final String GAME_IDENTIFIER = "JGLauncher";

    Menu menu;
    HashMap<String, String> games;

    @Override
    public void initialise() {
        loadFont();
        games = new HashMap<>();
        try {
            Arrays.asList(Mdx.files.external(System.getProperty("user.home") + "/Games")
                    .list())
                    .forEach(e -> {
                        games.put(e.name(), e.path());
                    });
        } catch (IOException ex) {
            Logger.getLogger(JGLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.addScreen(new Menu(games));
    }

    @Override
    public int getInitialScreenId() {
        return Menu.ID;
    }

    public void loadFont() {
        AssetManager manager = new AssetManager(new ExternalFileHandleResolver());
        manager.load("./ultra_compact_6x4.png", Texture.class);
        manager.finishLoading();
        MonospaceGameFont.FontParameters params = new MonospaceGameFont.FontParameters();
        params.texturePath = "./ultra_compact_6x4.png";
        params.spacing = 0;
        params.frameWidth = 4;
        params.frameHeight = 6;
        MonospaceGameFont font = new MonospaceGameFont(params);
        font.loadExternal();
        Mdx.graphicsContext.setFont(font);
    }
}
