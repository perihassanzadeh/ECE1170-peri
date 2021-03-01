package hotciv.variants;
import hotciv.framework.*;

import java.util.HashMap;

public interface WorldLayoutStrategy {
    public HashMap<Position, Tile> createLayout(Game g);
    public HashMap<Position, City> createCities(Game g);
    public HashMap<Position, Unit> createUnits(Game g);
}
