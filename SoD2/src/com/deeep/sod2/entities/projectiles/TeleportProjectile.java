package com.deeep.sod2.entities.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.CollideAble;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.Obstacle;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.graphics.ShapeRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class TeleportProjectile extends Entity implements CollideAble {
    private int previousX, previousY;
    private float timer = 0;
    private Color color;
    private Snake.Direction direction;
    private Snake snake; //Reference to the snake so we can actually teleprot it

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public TeleportProjectile() {
    }

    public TeleportProjectile(int id, int owner, float x, float y, Snake.Direction direction, Snake snake) {
        super(id, owner, x, y);
        this.direction = direction;
        this.snake = snake;
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        timer += deltaT;
        if (timer >= 0.05) {
            switch (direction) {
                case NORTH:
                    y++;
                    break;
                case EAST:
                    x++;
                    break;
                case SOUTH:
                    y--;
                    break;
                case WEST:
                    x--;
                    break;
            }
            timer -= 0.05;
        }
    }

    @Override
    public void Collide(Entity entity) {
        if (entity instanceof Obstacle) {
            snake.setDirection(direction.getOpposite());
            snake.setX(x);
            snake.setY(y);
            die();
        }
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        color = new Color((float) 148 / 255, 0f, (float) 211 / 255, 0.7f);
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        ShapeRenderer.setColor(color);
        ShapeRenderer.drawRectangle(spriteBatch, x, y, 1, 1, true);
    }
}
