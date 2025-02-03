package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.models.actions.Action;
import ru.mipt.bit.platformer.models.actions.MoveAction;

import java.util.HashMap;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyTools {
    public static void registerKeys(HashMap<Integer, Action> keyAction, Level level) {
        keyAction.put(UP, new MoveAction(level.getPlayerTank(), level, Direction.UP));
        keyAction.put(W, new MoveAction(level.getPlayerTank(), level, Direction.UP));
        keyAction.put(LEFT, new MoveAction(level.getPlayerTank(), level, Direction.LEFT));
        keyAction.put(A, new MoveAction(level.getPlayerTank(), level, Direction.LEFT));
        keyAction.put(DOWN, new MoveAction(level.getPlayerTank(), level, Direction.DOWN));
        keyAction.put(S, new MoveAction(level.getPlayerTank(), level, Direction.DOWN));
        keyAction.put(RIGHT, new MoveAction(level.getPlayerTank(), level, Direction.RIGHT));
        keyAction.put(D, new MoveAction(level.getPlayerTank(), level, Direction.RIGHT));

    }
}
