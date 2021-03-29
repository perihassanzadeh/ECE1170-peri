package hotciv.standard;

import hotciv.framework.*;

public class TileImpl implements Tile {

    String type;
    Boolean isCity = false;
    Boolean canMove;

    public TileImpl(String g)
    {
        type = g;
        setMoves();
    }

    public String getTypeString()
    {
        return type;
    }

    public void setType(String in_type)
    {
        type = in_type;
    }

    public Boolean getIsCity()
    {
        return isCity;
    }

    public void setIsCity(Boolean change)
    {
        isCity = change;
    }

    public Boolean getValidMove()
    {
        return canMove;
    }

    public void setMoves()
    {
        canMove = false;

        if(type == GameConstants.PLAINS ||type == GameConstants.FOREST || type == GameConstants.HILLS)
        {
            canMove=true;
        }
    }
}
