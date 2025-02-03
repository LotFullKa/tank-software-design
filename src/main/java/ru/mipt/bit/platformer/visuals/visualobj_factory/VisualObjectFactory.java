package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.visuals.VisualObject;

public interface VisualObjectFactory<T extends GameObject> {
    VisualObject createVisualObject(T object);
}
