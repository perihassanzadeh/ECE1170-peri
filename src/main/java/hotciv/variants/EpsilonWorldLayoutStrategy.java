package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.WorldLayoutStrategy;

import java.util.HashMap;

public class EpsilonWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public HashMap<Position, Unit> createUnits(Game g) {

        HashMap<Position, Unit> units = new HashMap<Position, Unit>();

        //Red Archers
        Position rArc = new Position(2,0);
        UnitImpl redArcher = new UnitImpl();
        redArcher.UnitImpl(rArc, GameConstants.ARCHER);
        redArcher.setOwner(Player.RED);
        units.put(rArc, redArcher);

        Position rArc2 = new Position(3,0);
        UnitImpl redArcher2 = new UnitImpl();
        redArcher2.UnitImpl(rArc2, GameConstants.ARCHER);
        redArcher2.setOwner(Player.RED);
        units.put(rArc2, redArcher2);

        Position rArc3 = new Position(4,0);
        UnitImpl redArcher3 = new UnitImpl();
        redArcher3.UnitImpl(rArc3, GameConstants.ARCHER);
        redArcher3.setOwner(Player.RED);
        units.put(rArc3, redArcher3);

        //Blue Archers
        Position bArc = new Position(2,1);
        UnitImpl blueArcher = new UnitImpl();
        blueArcher.UnitImpl(bArc, GameConstants.ARCHER);
        blueArcher.setOwner(Player.BLUE);
        units.put(bArc, blueArcher);

        Position bArc2 = new Position(3,1);
        UnitImpl blueArcher2 = new UnitImpl();
        blueArcher2.UnitImpl(bArc2, GameConstants.ARCHER);
        blueArcher2.setOwner(Player.BLUE);
        units.put(bArc2, blueArcher2);

        Position bArc3 = new Position(4,1);
        UnitImpl blueArcher3 = new UnitImpl();
        blueArcher3.UnitImpl(bArc3, GameConstants.ARCHER);
        blueArcher3.setOwner(Player.BLUE);
        units.put(bArc3, blueArcher3);

        return units;
    }

    @Override
    public HashMap<Position, City> createCities(Game g) {

        HashMap<Position, City> cities = new HashMap<Position, City>();

        //Red city at (1,1)
        CityImpl red = new CityImpl();
        red.CityImpl(Player.RED);
        Position redCity = new Position(1,1);
        cities.put(redCity, red);

        //Blue city at (4,1)
        CityImpl blue = new CityImpl();
        blue.CityImpl(Player.BLUE);
        Position blueCity = new Position(4,1);
        cities.put(blueCity, blue);

        return cities;
    }

    @Override
    public HashMap<Position, Tile> createLayout(Game g) {
        String[] layout =
                new String[] {
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                        "oooooooooooooooo",
                };
        // Conversion...
        HashMap<Position,Tile> theWorld = new HashMap<Position,Tile>();
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                theWorld.put( p, new TileImpl(type));
            }
        }

        return theWorld;
    }
}
