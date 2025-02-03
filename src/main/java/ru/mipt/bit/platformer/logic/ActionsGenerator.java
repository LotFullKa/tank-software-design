package ru.mipt.bit.platformer.logic;

import ru.mipt.bit.platformer.logic.actions.Action;

import java.util.Collection;

public interface ActionsGenerator {
    Collection<Action> generate();
}
