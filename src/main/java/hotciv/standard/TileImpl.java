package hotciv.standard;

import hotciv.framework.*;

public class TileImpl implements Tile {

    String type;
    Boolean isCity = false;

    public TileImpl(String g)
    {
        type = g;
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
}
