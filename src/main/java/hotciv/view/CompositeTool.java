package hotciv.view;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CompositeTool extends NullTool {
    private UnitMoveTool unitMoveTool;
    private EndOfTurnTool endOfTurnTool;
    private ActionTool actionTool;
    private SetTileFocusTool setTileFocusTool;

    public CompositeTool(Game g, DrawingEditor editor)
    {
        unitMoveTool = new UnitMoveTool(g, editor);
        endOfTurnTool = new EndOfTurnTool(g, editor);
        actionTool = new ActionTool(g, editor);
        setTileFocusTool = new SetTileFocusTool(g);
    }

    @Override
    public void mouseDown(java.awt.event.MouseEvent e, int x, int y)
    {
        unitMoveTool.mouseDown(e,x,y);
        endOfTurnTool.mouseDown(e,x,y);
        actionTool.mouseDown(e,x,y);
        setTileFocusTool.mouseDown(e, x, y);
    }

    @Override
    public void mouseDrag(java.awt.event.MouseEvent e, int x, int y)
    {
        unitMoveTool.mouseDrag(e,x,y);
        actionTool.mouseDrag(e, x, y);
        endOfTurnTool.mouseDrag(e,x,y);
    }

    @Override
    public void mouseUp(java.awt.event.MouseEvent e, int x, int y) {
        unitMoveTool.mouseUp(e,x,y);
        actionTool.mouseUp(e, x, y);
        endOfTurnTool.mouseUp(e,x,y);
    }

    @Override
    public void keyDown(KeyEvent evt, int key) {
        actionTool.keyDown(evt, key);
    }

    public void mouseMove(MouseEvent arg0, int arg1, int arg2) {
        actionTool.mouseMove(arg0, arg1, arg2);
        unitMoveTool.mouseMove(arg0, arg1, arg2);
        endOfTurnTool.mouseMove(arg0, arg1, arg2);
    }
}
