package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Tree;
import ru.mipt.bit.platformer.visuals.VisualObject;
import ru.mipt.bit.platformer.visuals.VisualTree;

public class VisualTreeFactory implements VisualObjectFactory<Tree> {
    @Override
    public VisualObject createVisualObject(Tree tree) {
        return new VisualTree(tree);
    }
}
