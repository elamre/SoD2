package com.deeep.sod2.entities.enemyentities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.entities.projectiles.SnakeBullet;
import com.deeep.sod2.entities.projectiles.TurretBullet;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.ShapeRenderer;
/*
 * Class : Turret
 * Package : com.deeep.sod2.entities
 * Author : Andreas
 * Date : 08-10-13
 */

public class Turret extends AnimatedEnemy {
    protected int level;
    protected float health;
    protected float maxHealth;
    private boolean firstTime = true;
    private int targetX, targetY;
    private Entity target;

    public Turret() {
    }

    public Turret(int id, int x, int y, float health, int level) {
        super(id, -1, x, y, "animations/turret_strip14", 5);
        this.health = health;
        this.maxHealth = health;
        this.level = level;
        targetY = y;
        targetX = x;
        setTextureRegion(Assets.getAssets().getRegion("Tiles/turret"));
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        if (target != null) {
            ShapeRenderer.setColor(new Color(1f, 0, 0, 0.5f));
            ShapeRenderer.drawRectangle(spriteBatch, targetX, targetY, 1, 1, true);
        }
    }

    @Override
    public void implementUpdate_2(float deltaT) {
        for (int i = 0; i < EntityManager.get().entities.size(); i++) {
            if (EntityManager.get().entities.get(i) instanceof Snake)
                target = EntityManager.get().entities.get(i);
        }
        if (target != null) {
            if (target.getDistance(getOriginX(), getOriginY()) > 3.5f) {
                animationTimer = 0;
                target = null;
                firstTime = true;
                return;
            }
            if (firstTime) {
                targetX = (int) target.getX();
                targetY = (int) target.getY();
                firstTime = false;
            }
            if (animation.isAnimationFinished(animationTimer)) {

                /**  ________T
                 *  |       /|
                 *  |     /  |
                 *  |   /    | y
                 *  | /      |
                 *  θ_______|
                 *      x
                 */
                float dx = targetX - this.x;
                float dy = targetY - this.y;

                /** θ = tan^-1(y/x) */
                float theta = (float) Math.atan2(dy, dx);
                EntityManager.get().addEntitySinglePlayer(new TurretBullet(EntityManager.get().getNextSinglePlayerId(), x, y, 5f, 8f, theta));
                if (firstTime) {
                    firstTime = false;
                } else {
                    targetX = (int) target.getX();
                    targetY = (int) target.getY();
                }
            }
        }
    }


    @Override
    public void Collide(Entity entity) {
        if(entity instanceof SnakeBullet)
            die();
    }
}