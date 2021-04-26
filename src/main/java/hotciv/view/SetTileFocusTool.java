package hotciv.view;

import minidraw.standard.NullTool;
import hotciv.framework.*;

import java.awt.event.MouseEvent;

public class SetTileFocusTool extends NullTool{
    private Game game;

    public SetTileFocusTool(Game g)
    {
        game = g;
    }

    public void mouseDown(java.awt.event.MouseEvent e, int x, int y) {
        Position position = GfxConstants.getPositionFromXY(x, y);
        game.setTileFocus(position);


    }
}
