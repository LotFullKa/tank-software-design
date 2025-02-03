package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logic.models.GameObject;
import ru.mipt.bit.platformer.visuals.VisualObject;

public interface VisualObjectFactory {
    VisualObject createVisualObject(GameObject gameObject);
}
