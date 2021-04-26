package hotciv.view;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ActionTool extends NullTool {
    Game game;
    DrawingEditor drawingEditor;
    Position position;

    public ActionTool(Game g, DrawingEditor editor)
    {
        game=g;
        drawingEditor=editor;
    }

    @Override
    public void mouseDown(java.awt.event.MouseEvent e, int x, int y)
    {
        if(e.isShiftDown()==true)
        {
            position = GfxConstants.getPositionFromXY(x, y);
        }
    }

    @Override
    public void mouseUp(java.awt.event.MouseEvent e, int x, int y)
    {
        if(e.isShiftDown()==true)
        {
            Unit unit = game.getUnitAt(position);

            if(unit !=null)
            {
                game.performUnitActionAt(position);
                drawingEditor.showStatus("Action performed for " + unit.getTypeString());
            }
        }
    }
}
