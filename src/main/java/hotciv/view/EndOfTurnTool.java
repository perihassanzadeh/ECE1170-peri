package hotciv.view;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;
import hotciv.framework.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EndOfTurnTool extends NullTool {
    Game game;
    Figure figSelected;
    DrawingEditor drawingEditor;

    public EndOfTurnTool(Game g, DrawingEditor editor)
    {
        game = g;
        drawingEditor = editor;
    }

    @Override
    public void mouseDown(java.awt.event.MouseEvent e, int x, int y) {
        figSelected = drawingEditor.drawing().findFigure(x, y);
    }

    @Override
    public void mouseUp(java.awt.event.MouseEvent e, int x, int y) {
        if (figSelected != null)
        {
            Rectangle box = figSelected.displayBox();


            if ((GfxConstants.TURN_SHIELD_X >= box.x
                    && GfxConstants.TURN_SHIELD_X <= box.x + box.width)&&(GfxConstants.TURN_SHIELD_Y >= box.y
                    && GfxConstants.TURN_SHIELD_Y <= box.y + box.height))
            {

                System.out.println("End of Turn");
                drawingEditor.showStatus("End of " + game.getPlayerInTurn() + " turn");
                game.endOfTurn();
            }
        }
    }
}

