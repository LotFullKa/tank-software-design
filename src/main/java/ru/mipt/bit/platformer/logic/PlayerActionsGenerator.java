package ru.mipt.bit.platformer.logic;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.logic.actions.Action;
import ru.mipt.bit.platformer.logic.actions.NoneAction;
import ru.mipt.bit.platformer.logic.input_controller.KeyTools;
import ru.mipt.bit.platformer.logic.models.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PlayerActionsGenerator implements ActionsGenerator {

    private final HashMap<Integer, Action> keyRegister;

    public PlayerActionsGenerator(Level level) {
        keyRegister = new HashMap<>();
        KeyTools.registerKeys(keyRegister, level);
    }

    @Override
    public ArrayList<Action> generate() {
        return new ArrayList<>(Collections.singletonList(getAction()));
    }

    public Action getAction() {
        Action result = null;
        for (Integer key : keyRegister.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                result = keyRegister.get(key);
                break;
            }
        }
        if (result != null) {
            return result;
        }

        return new NoneAction();
    }

}
