package ru.mipt.bit.platformer.models;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.models.actions.Action;
import ru.mipt.bit.platformer.models.actions.NoneAction;

import java.util.HashMap;

public class PlayerInput {
    private final HashMap<Integer, Action> keyRegister;

    public PlayerInput(Level level){
        keyRegister = new HashMap<>();
        KeyTools.registerKeys(keyRegister, level);
    }

    public Action getAction(){
        Action result = null;
        for(Integer key : keyRegister.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                result = keyRegister.get(key);
                break;
            }
        }
        if(result != null){
            return result;
        }

        return new NoneAction();
    }
}
