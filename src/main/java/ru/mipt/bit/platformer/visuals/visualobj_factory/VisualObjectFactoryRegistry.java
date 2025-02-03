package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.Bullet;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Tank;
import ru.mipt.bit.platformer.logics.models.Tree;
import ru.mipt.bit.platformer.visuals.VisualObject;

import java.util.HashMap;
import java.util.Map;

public class VisualObjectFactoryRegistry {
    private static final Map<Class<? extends GameObject>, VisualObjectFactory<?>> registry = new HashMap<>();

    static {
        registry.put(Tank.class, new VisualTankFactory());
        registry.put(Bullet.class, new VisualBulletFactory());
        registry.put(Tree.class, new VisualTreeFactory());
    }

    @SuppressWarnings("unchecked")
    public static <T extends GameObject> VisualObject createVisualObject(T object) {
        VisualObjectFactory<T> factory = (VisualObjectFactory<T>) registry.get(object.getClass());
        if (factory == null) {
            throw new IllegalArgumentException("No factory registered for " + object.getClass().getSimpleName());
        }
        return factory.createVisualObject(object);
    }
}