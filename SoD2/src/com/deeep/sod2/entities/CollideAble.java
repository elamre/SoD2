package com.deeep.sod2.entities;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/9/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CollideAble {
    public void Collide(EntityManager entityManager, Entity entity);
}
