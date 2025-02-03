package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.Bullet;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.visuals.VisualBullet;
import ru.mipt.bit.platformer.visuals.VisualObject;

public class VisualBulletFactory implements VisualObjectFactory<Bullet> {
    @Override
    public VisualObject createVisualObject(Bullet bullet) {
        return new VisualBullet(bullet);
    }
}
