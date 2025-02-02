package ru.mipt.bit.platformer.models.actions;

public class ActionHandler {
    public static void handle(Action action){
        action.process();
    }
}

