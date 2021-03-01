package hotciv.variants;

import hotciv.framework.*;

public class AlphaUnitActionStrategy implements UnitActionStrategy{

    //AlphaCiv units have no related action
    public boolean getAction(Position p, Game g) {
        return false;
    }
}
