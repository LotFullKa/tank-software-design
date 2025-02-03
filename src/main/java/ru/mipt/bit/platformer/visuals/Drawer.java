package ru.mipt.bit.platformer.visuals;

import ru.mipt.bit.platformer.logic.models.Level;

public interface Drawer {
    // render visuals
    void drawVisuals(Level level);

    void dispose();
}
