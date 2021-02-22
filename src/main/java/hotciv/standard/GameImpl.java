package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

import java.lang.Math;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {

  boolean redTurn;
  int age;
  HashMap<Position, Tile> boardTiles = new HashMap<>();
  HashMap<Position, Unit> unitTiles = new HashMap<>();
  HashMap<Position, City> cityTiles = new HashMap<>();

  public GameImpl()
  {
    redTurn = true;
    age = -4000;

    //Initialize Tiles
    for(int i = 0; i<GameConstants.WORLDSIZE; i++)
    {
      for(int j=0; j<GameConstants.WORLDSIZE; j++)
      {
        Position p = new Position(i,j);

        if(i==1 && j==0)
        {
          String type = GameConstants.OCEANS;
          Tile t = new TileImpl(type);
          boardTiles.put(p,t);
        }
        else if(i==0 && j==1)
        {
          String type = GameConstants.HILLS;
          Tile t = new TileImpl(type);
          boardTiles.put(p,t);
        }
        else if(i==2 && j==2)
        {
          String type = GameConstants.MOUNTAINS;
          Tile t = new TileImpl(type);
          boardTiles.put(p,t);
        }
        else
        {
          String type = GameConstants.PLAINS;
          Tile t = new TileImpl(type);
          boardTiles.put(p, t);
        }

      }
    }

    //Red city at (1,1)
    CityImpl red = new CityImpl();
    red.CityImpl(Player.RED, GameConstants.productionFocus);
    Position redCity = new Position(1,1);
    cityTiles.put(redCity, red);

    //Blue city at (4,1)
    CityImpl blue = new CityImpl();
    blue.CityImpl(Player.BLUE, GameConstants.productionFocus);
    Position blueCity = new Position(4,1);
    cityTiles.put(blueCity, blue);

    //Red Archer
    Position rArc = new Position(2,0);
    UnitImpl redArcher = new UnitImpl();
    redArcher.UnitImpl(rArc, GameConstants.ARCHER);
    unitTiles.put(rArc, redArcher);

    //Blue Legion
    Position bLeg = new Position(3,2);
    UnitImpl blueLegion = new UnitImpl();
    blueLegion.UnitImpl(bLeg, GameConstants.LEGION);
    unitTiles.put(bLeg, blueLegion);

    //Red Settler
    Position rSet = new Position(4,3);
    UnitImpl redSettler = new UnitImpl();
    redSettler.UnitImpl(rSet, GameConstants.SETTLER);
    unitTiles.put(rSet, redSettler);

  }

  public Tile getTileAt( Position p )
  {
    return boardTiles.get(p);
  }

  public Unit getUnitAt( Position p )
  {
    return unitTiles.get(p);
  }

  public City getCityAt( Position p )
  {
    return cityTiles.get(p);
  }

  public Player getPlayerInTurn()
  {
    if(redTurn == true)
    {
      return Player.RED;
    }
    else if(redTurn == false)
    {
      return Player.BLUE;
    }
    else
    {
      return null;
    }

  }

  public Player getWinner()
  {
    if(getAge()==3000 && getPlayerInTurn()==Player.RED)
    {
      return Player.RED;
    }
    else
    {
      return null;
    }
  }

  public int getAge()
  {
    return age;
  }

  public boolean moveUnit( Position from, Position to )
  {
    UnitImpl u = new UnitImpl();
    //u = getUnitAt(from);
    unitTiles.remove(from, u);

    //Attacking always wins 
    if(unitTiles.get(to)!= null)
    {
      unitTiles.remove(to);
    }

    unitTiles.put(to, u);

    int from_col = from.getColumn();
    int from_row = from.getRow();

    int to_col = to.getColumn();
    int to_row = to.getRow();

    int col_moves = from_col - to_col;
    int row_moves = from_row - to_row;

    int total_moves = Math.abs(col_moves) + Math.abs(row_moves);

    u.setMovecount(total_moves);

    return Boolean.TRUE;
  }

  public void endOfTurn()
  {
    //End of round increment age and change production
    if(redTurn==false)
    {
      age = age + 100;

      int size = cityTiles.size();
      for(int i=0; i<=16; i++)
      {
        for(int j=0; j<=16; j++)
        {
          Position p = new Position(i, j);
          City c = new CityImpl();
          c = cityTiles.get(p);
          if(c != null)
          {
            int current = c.getTreasury();
            //c.setTreasury(current + 6);
          }
        }
      }

      endOfRound();
    }

    //Change player turn indicator
    if(redTurn == true)
    {
      redTurn = false;
    }
    else
    {
      redTurn = true;
    }

  }

  public void changeWorkForceFocusInCityAt( Position p, String balance )
  {

  }

  public void changeProductionInCityAt( Position p, String unitType )
  {
    City c = cityTiles.get(p);
    //c.setProduction(unitType);
  }

  public void performUnitActionAt( Position p )
  {
    //No associated Action at this time
  }

  public void endOfRound()
  {

  }

}
