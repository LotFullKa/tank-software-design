package ru.mipt.bit.platformer.logic;

import ru.mipt.bit.platformer.logic.actions.Action;
import ru.mipt.bit.platformer.logic.actions.MoveAction;
import ru.mipt.bit.platformer.logic.models.Direction;
import ru.mipt.bit.platformer.logic.models.Level;
import ru.mipt.bit.platformer.logic.models.Tank;

import java.util.ArrayList;
import java.util.List;

public class AITanksActionsGenerator implements ActionsGenerator{
    private final Level level;

    public AITanksActionsGenerator(Level level){
        this.level = level;
    }

    @Override
    public ArrayList<Action> generate() {
        ArrayList<Action> actions = new ArrayList<>();

        List<Tank> tanks = level.getTanks();
        for (Tank tank : tanks) {
            if(tank != level.getPlayerTank()){
                actions.add(new MoveAction(tank, level, getRandomDirection()));
            }
        }
        return actions;
    }

    private Direction getRandomDirection(){
        List<Direction> directions = List.of(Direction.UP, Direction.RIGHT, Direction.LEFT, Direction.DOWN);
        int idx = (int) (Math.random() * 100) % directions.size();
        return directions.get(idx);
    }
}
