package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Tank;
import ru.mipt.bit.platformer.visuals.VisualObject;
import ru.mipt.bit.platformer.visuals.VisualTank;

public class VisualTankFactory implements VisualObjectFactory<Tank> {
    @Override
    public VisualObject createVisualObject(Tank tank) {
        return new VisualTank(tank);
    }
}