package pong;

import com.ibq2d.engine.core.SceneManager;
import com.ibq2d.engine.core.SpriteBatch;
import com.ibq2d.engine.core.Texture;
import com.ibq2d.engine.core.Time;
import com.ibq2d.engine.geometry.Vector2;
import com.ibq2d.engine.ui.Text;

import java.util.ArrayList;

public class PauseMenu extends Menu {

    Texture blackTexture;
    SpriteBatch spriteBatch;

    class BtResume extends Button {

        Text text;

        @Override
        public void awake() {
            text = new Text("Resume", 20);
            text.setPosition(new Vector2(-13, -38));
        }

        @Override
        public void onClick() {
            Time.timeScale = 1;
            SceneManager.unloadAdditive(SceneManager.getSceneIndex("PauseScreen"));
        }

        @Override
        public void draw() {
            text.draw();
        }
    }

    class BtGoToMainMenu extends Button {

        Text text;

        @Override
        public void awake() {
            text = new Text("Main menu", 20);
            text.setPosition(new Vector2(1, -13));
        }

        @Override
        public void onClick() {
            Time.timeScale = 1;
            SceneManager.unloadAdditive(SceneManager.getSceneIndex("PauseScreen"));
            SceneManager.load("StartScreen");
        }

        @Override
        public void draw() {
            text.draw();
        }
    }

    @Override
    public void awake() {
        Time.timeScale = 0;

        spriteBatch = new SpriteBatch();
        blackTexture = new Texture("black.jpg");
        buttons = new ArrayList<>();

        buttons.add(new BtResume());
        buttons.add(new BtGoToMainMenu());
        buttons.add(new BtSoundManager());

        for (Button bt : buttons)
            bt.awake();
    }

    @Override
    public void draw() {
        spriteBatch.draw(blackTexture, 0, 0, 16, 16, 0);

        for (Button bt : buttons)
            bt.draw();
    }
}
