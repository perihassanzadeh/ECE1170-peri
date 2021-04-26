package hotciv.view;

import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;
import minidraw.framework.Figure;
import hotciv.framework.*;

import java.awt.event.MouseEvent;

public class UnitMoveTool extends NullTool {

    Game game;
    Figure figSelected;
    DrawingEditor drawingEditor;
    Position from;
    int xCord, yCord;
    boolean isUnit;

    public UnitMoveTool(Game g, DrawingEditor editor)
    {
        game = g;
        drawingEditor = editor;
        xCord=0;
        yCord=0;
        isUnit=false;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        Position position = GfxConstants.getPositionFromXY(x, y);

        if (valid(position) == true)
        {

            from = position;
            Unit unit = game.getUnitAt(position);

            if (unit != null)
            {
                figSelected = drawingEditor.drawing().findFigure(x, y);
                xCord = x;
                yCord = y;
            }
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y)
    {
        Position position = GfxConstants.getPositionFromXY(x, y);
        if (valid(position) == true)
        {
            from = position;

            Unit unit = game.getUnitAt(position);
            if (unit != null)
            {
                figSelected = drawingEditor.drawing().findFigure(x, y);
                xCord = x;
                yCord = y;
            }
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y)
    {
        if (figSelected != null)
        {

            Position to = GfxConstants.getPositionFromXY(x, y);
            int finalX = GfxConstants.getXFromColumn(to.getColumn());
            int finalY = GfxConstants.getYFromRow(to.getRow());

            figSelected.moveBy(finalX-xCord, finalY - yCord);
            if (valid(to) == false)
            {
                int beginX = GfxConstants.getXFromColumn(from.getColumn());
                int beginY = GfxConstants.getYFromRow(from.getRow());

                figSelected.moveBy(beginX - finalX, beginY - finalY);
            }
            else if (!game.moveUnit(from, to)) {
                int beginX = GfxConstants.getXFromColumn(from.getColumn());
                int beginY = GfxConstants.getYFromRow(from.getRow());

                figSelected.moveBy(beginX - finalX, beginY - finalY);
            }
        }
        figSelected = null;

        Unit unit = game.getUnitAt(from);
        drawingEditor.showStatus("Action performed for " + unit.getTypeString());
    }

    public boolean valid(Position p)
    {
        boolean row = (0 <= p.getRow() && GameConstants.WORLDSIZE > p.getRow());
        boolean col = (0 <= p.getColumn() && GameConstants.WORLDSIZE > p.getColumn());

        boolean val = (row && col);

        return val;
    }
}
