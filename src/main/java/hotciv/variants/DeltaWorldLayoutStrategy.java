package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy{

    public HashMap<Position, Tile> createLayout(Game g) {
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
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


    public HashMap<Position, City> createCities(Game g) {

        HashMap<Position, City> cities = new HashMap<Position, City>();

        //Red city at (1,1)
        CityImpl red = new CityImpl();
        red.CityImpl(Player.RED);
        Position redCity = new Position(8,12);
        cities.put(redCity, red);

        //Blue city at (4,1)
        CityImpl blue = new CityImpl();
        blue.CityImpl(Player.BLUE);
        Position blueCity = new Position(4,5);
        cities.put(blueCity, blue);

        return cities;
    }


    public HashMap<Position, Unit> createUnits(Game g) {
        HashMap<Position, Unit> units = new HashMap<Position, Unit>();

        //Red Archer
        Position rArc = new Position(2,0);
        UnitImpl redArcher = new UnitImpl();
        redArcher.UnitImpl(rArc, GameConstants.ARCHER);
        units.put(rArc, redArcher);

        //Blue Legion
        Position bLeg = new Position(3,2);
        UnitImpl blueLegion = new UnitImpl();
        blueLegion.UnitImpl(bLeg, GameConstants.LEGION);
        units.put(bLeg, blueLegion);

        //Red Settler
        Position rSet = new Position(4,3);
        UnitImpl redSettler = new UnitImpl();
        redSettler.UnitImpl(rSet, GameConstants.SETTLER);
        units.put(rSet, redSettler);

        return units;
    }
}
