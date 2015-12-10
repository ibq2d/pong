package pong;

import com.ibq2d.engine.Application;
import com.ibq2d.engine.core.*;
import com.ibq2d.engine.geometry.*;
import com.ibq2d.engine.physics.*;


public class Ball extends GameListener {

    Texture texture;
    Sprite sprite;
    Collider collider;
    RigidBody rigidBody;
    SpriteBatch spriteBatch;

    float maxSpeed = 6;

    public static Vector2 velocity;
    public static Vector2 position;

    Audio hitSound;

    @Override
    public void awake() {
        hitSound = new Audio("hitSound.wav", false, Audio.PlayMode.PLAY_AS_SOUNDEFFECT);

        texture = new Texture("ball.png");
        spriteBatch = new SpriteBatch();
        sprite = new Sprite(texture);

        collider = new CircleCollider(new Circle(sprite), false, new ContactListener() {
            @Override
            public void onContactEnter(Collider collider) {
                if (collider.tag == "Platform") {
                    AudioSource.instance.play(hitSound);
                }
            }
        });
        collider.tag = "Ball";
        rigidBody = new RigidBody(new Vector2(0f, -1f).multiplyBy(5), collider);

        velocity = rigidBody.getVelocity();
        position = sprite.getPosition();
    }

    @Override
    public void update() {
        rigidBody.update();
        velocity = rigidBody.getVelocity();
        position = sprite.getPosition();

        rigidBody.getVelocity().setY(Math.min(maxSpeed, rigidBody.getVelocity().getY()));

        if (position.getY() > Application.HALF_HEIGHT) {
            Score.score++;
            SceneManager.load(SceneManager.loadedScene());
        }
        else if (position.getY() < -Application.HALF_HEIGHT) {
            Score.score--;
            SceneManager.load(SceneManager.loadedScene());
        }
    }

    @Override
    public void draw() {
        sprite.draw(spriteBatch);
    }
}