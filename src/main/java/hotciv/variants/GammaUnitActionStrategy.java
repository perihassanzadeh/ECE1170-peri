package hotciv.variants;
import hotciv.framework.*;
import hotciv.standard.*;

public class GammaUnitActionStrategy implements UnitActionStrategy {

    public boolean getAction(Position p, Game g) {

        if(g.getUnitAt(p)==null)
        {
            return false;
        }

        Unit u = g.getUnitAt(p);

        if(u.getTypeString() == GameConstants.SETTLER && g.getCityAt(p)==null)
        {
            g.removeUnit(p);
            Player play = u.getOwner();
            g.createCity(p, play);
            return true;
        }
        else if(u.getTypeString() == GameConstants.ARCHER)
        {
            u.fortifyUnit();
            return true;
        }

        return false;
    }
}