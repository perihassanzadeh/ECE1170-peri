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

  public Tile getTileAt(Position position )
  {
    return boardTiles.get(position);
  }

  public Unit getUnitAt( Position position )
  {
    return unitTiles.get(position);
  }

  public City getCityAt( Position position )
  {
    return cityTiles.get(position);
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

    boolean validMove = false;
    Unit unit = new UnitImpl();
    unit = getUnitAt(from);

    if(unit != null)
    {
      for (Position position : Utility.get8neighborhoodOf(from))
      {
        int positionRow = position.getRow();
        int positionCol = position.getColumn();

        if (positionRow == to.getRow() && positionCol == to.getColumn() && getTileAt(position).getValidMove() && (getUnitAt(to) == null || unit.getOwner() != getUnitAt(to).getOwner()))
        {
          validMove = true;
          break;
        }
      }
      unitTiles.remove(from, unit);
    }

    //Attacking always wins
    if(unitTiles.get(to)!= null)
    {
      unitTiles.remove(to);
    }

    unitTiles.put(to, unit);

    int total_moves = calcMoveCount(to, from);

    unit.setMovecount(total_moves);

    return Boolean.TRUE;

  }

  private int calcMoveCount(Position to, Position from)
  {
    int from_col = from.getColumn();
    int from_row = from.getRow();

    int to_col = to.getColumn();
    int to_row = to.getRow();

    int col_moves = from_col - to_col;
    int row_moves = from_row - to_row;

    int total_moves = Math.abs(col_moves) + Math.abs(row_moves);

    return total_moves;
  }

  public void endOfTurn()
  {
    //End of round increment age and change production
    if(redTurn==false)
    {
      age = ageStrategy.calcNextWorldAge(age);

      int size = cityTiles.size();

      for(int row=0; row<=size; row++)
      {
        for(int column=0; column<=size; column++)
        {
          Position position = new Position(row, column);
          City city = new CityImpl();
          city = cityTiles.get(position);

          if(city != null)
          {
            int current = city.getTreasury();
            city.setTreasury(current + 6);
          }
        }
      }
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

  public void changeProductionInCityAt( Position position, String unitType )
  {
    City city = cityTiles.get(position);
    city.setProduction(unitType);
  }

  public void performUnitActionAt( Position position )
  {
    boolean action;

    action = unitActionStrategy.getAction(position, this);
  }

  public void createCity(Position position, Player owner)
  {
    City city = new CityImpl();
    city.setOwner(owner);
    cityTiles.put(position, city);

  }

  public void removeUnit(Position position)
  {
    unitTiles.remove(position);
  }

  public void setTileType(Position position, Tile tile)
  {
    boardTiles.put(position,tile);
  }

}
