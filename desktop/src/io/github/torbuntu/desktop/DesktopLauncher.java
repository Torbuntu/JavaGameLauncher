package io.github.torbuntu.desktop;

import com.badlogic.gdx.backends.lwjgl3.DesktopMini2DxGame;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Mini2DxConfig;
import io.github.torbuntu.JGLauncher;

public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3Mini2DxConfig config = new Lwjgl3Mini2DxConfig(JGLauncher.GAME_IDENTIFIER);
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        new DesktopMini2DxGame(new JGLauncher(), config);
    }
}
