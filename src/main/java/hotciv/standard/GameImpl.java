package hotciv.standard;
import java.util.*;
import hotciv.framework.*;
import hotciv.standard.*;
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
  int age, round;
  HashMap<Position, Tile> boardTiles = new HashMap<>();
  HashMap<Position, Unit> unitTiles = new HashMap<>();
  HashMap<Position, City> cityTiles = new HashMap<>();
  private List<Battle> battles;
  private AgeStrategy ageStrategy;
  private WinnerStrategy winnerStrategy;
  private UnitActionStrategy unitActionStrategy;
  private WorldLayoutStrategy worldLayoutStrategy;
  private AttackStrategy attackStrategy;

  public GameImpl(Strategy strategy)
  {
    redTurn = true;
    age = -4000;
    round = 0;
    battles = new ArrayList<Battle>();
    this.ageStrategy = strategy.makeAlphaAgingStrategy();
    this.winnerStrategy = strategy.makeAlphaWinnerStrategy();
    this.unitActionStrategy = strategy.makeAlphaUnitActionStrategy();
    this.worldLayoutStrategy = strategy.makeAlphaWorldLayoutStrategy();
    this.attackStrategy = strategy.makeAlphaAttackStrategy();

    boardTiles = worldLayoutStrategy.createLayout(this);
    unitTiles = worldLayoutStrategy.createUnits(this);
    cityTiles = worldLayoutStrategy.createCities(this);
  }

  public void setAgeStrategy(AgeStrategy ageStrategy)
  {
    this.ageStrategy = ageStrategy;
  }

  private boolean attack( Position attacker, Position defender )
  {
    return attackStrategy.attack(this, attacker, defender);
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

  public boolean moveUnit(Position from, Position to)
  {
    Unit u = getUnitAt(from);

    if (u != null)
    {
      Tile t = getTileAt(to);
      if (t.getValidMove()==false)
      {
        return false;
      }

      if (calcMoveCount(from,to) > u.getMoveCount())
      {
        return false;
      }

      if (u.getOwner() != getPlayerInTurn())
      {
        return false;
      }

      if (getUnitAt(to) != null )
      {
        if (getUnitAt(to).getOwner() == u.getOwner())
        {
          return false;
        }
        else {
          boolean successful = attack(from, to);
          if (successful == true)
          {
            unitTiles.put(to, u);

            Player attacker = getUnitAt(from).getOwner();
            Battle battle = new Battle(attacker, true, round);
            battles.add(battle);

            if(getCityAt(to) != null)
            {
              City c = new CityImpl();
              c.setOwner(u.getOwner());
              cityTiles.put(to, c);
            }
          }

          Player attacker = getUnitAt(from).getOwner();
          Battle battle = new Battle(attacker, false, round);
          battles.add(battle);
          u.setMovecount(0);

          unitTiles.remove(from);

        }
      } else {
        //u.setMovecount(0);
        unitTiles.put(to, u);
        unitTiles.remove(from);

        if(getCityAt(to) != null){
          Player own = u.getOwner();
          City c = new CityImpl();
          c.setOwner(own);
          cityTiles.put(to, c);
        }

      }
      return true;
    }
    else {
      return false;
    }

  }

  private int calcMoveCount(Position to, Position from)
  {
    int column = Math.abs(to.getColumn() - from.getColumn());
    int row = Math.abs(to.getRow()-from.getRow());

    int total_moves = Math.max( row, column);

    return total_moves;
  }

  public List<Battle> getBattles()
  {
    return battles;
  }

  public void endOfTurn()
  {
    //End of round increment age and change production
    if(redTurn==false)
    {
      age = ageStrategy.calcNextWorldAge(age);
      round = round+1;
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

  public int getRound()
  {
    return round;
  }
}
