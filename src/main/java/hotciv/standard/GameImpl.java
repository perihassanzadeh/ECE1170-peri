package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.*;

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
  private AgeStrategy ageStrategy;
  private WinnerStrategy winnerStrategy;
  private UnitActionStrategy unitActionStrategy;
  private WorldLayoutStrategy worldLayoutStrategy;

  public GameImpl(Strategy strategy)
  {
    redTurn = true;
    age = -4000;
    this.ageStrategy = strategy.makeAlphaAgingStrategy();
    this.winnerStrategy = strategy.makeAlphaWinnerStrategy();
    this.unitActionStrategy = strategy.makeAlphaUnitActionStrategy();
    this.worldLayoutStrategy = strategy.makeAlphaWorldLayoutStrategy();

    boardTiles = worldLayoutStrategy.createLayout(this);
    unitTiles = worldLayoutStrategy.createUnits(this);
    cityTiles = worldLayoutStrategy.createCities(this);

  }

  public void setAgeStrategy(AgeStrategy ageStrategy)
  {
    this.ageStrategy = ageStrategy;
  }

  public Tile getTileAt(Position p )
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
    return winnerStrategy.getWinner(this);
  }

  public int getAge()
  {
    return age;
  }

  public boolean moveUnit( Position from, Position to )
  {
    Unit u = new UnitImpl();
    u = getUnitAt(from);
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
      age = ageStrategy.calcNextWorldAge(age);

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
            c.setTreasury(current + 6);
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
    c.setProduction(unitType);
  }

  public void performUnitActionAt( Position p )
  {
    boolean action;

    action = unitActionStrategy.getAction(p, this);
  }

  public void endOfRound()
  {

  }

  public void createCity(Position p, Player owner)
  {
    City c = new CityImpl();
    c.setOwner(owner);
    cityTiles.put(p, c);

  }

  public void removeUnit(Position p)
  {
    unitTiles.remove(p);
  }

  public void setTileType(Position p, Tile t)
  {
    boardTiles.put(p,t);
  }

}
