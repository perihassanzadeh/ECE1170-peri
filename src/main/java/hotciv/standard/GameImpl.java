package hotciv.standard;
import java.util.*;

import hotciv.factories.GameFactory;
import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.variants.*;
import hotciv.framework.GameObserver;

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
  private List<GameObserver> observers;
  private AgeStrategy ageStrategy;
  private WinnerStrategy winnerStrategy;
  private UnitActionStrategy unitActionStrategy;
  private WorldLayoutStrategy worldLayoutStrategy;
  private AttackStrategy attackStrategy;

  public GameImpl(GameFactory factory, WorldLayoutStrategy world)
  {
    redTurn = true;
    age = -4000;
    round = 0;
    battles = new ArrayList<Battle>();
    observers = new ArrayList<GameObserver>();
    this.ageStrategy = factory.makeAgeStrategy();
    this.winnerStrategy = factory.makeWinnerStrategy();
    this.unitActionStrategy = factory.makeUnitActionStrategy();
    this.attackStrategy = factory.makeAttackStrategy();
    this.worldLayoutStrategy = world;

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
    //Unit u = getUnitAt(from);
    Unit u = ((UnitImpl)this.getUnitAt(from));

    if (u != null)
    {
      Tile t = getTileAt(to);
      Boolean val = ((TileImpl)this.getTileAt(to)).getValidMove();
      if (val==false)
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
              ((CityImpl)c).setOwner(u.getOwner());

              cityTiles.put(to, c);
            }
          }

          Player attacker = getUnitAt(from).getOwner();
          Battle battle = new Battle(attacker, false, round);
          battles.add(battle);
          ((UnitImpl)this.getUnitAt(from)).setMovecount(0);

          unitTiles.remove(from);

        }
      } else {
        //u.setMovecount(0);
        unitTiles.put(to, u);
        unitTiles.remove(from);

        if(getCityAt(to) != null){
          Player own = u.getOwner();
          City city = new CityImpl();
          ((CityImpl)city).setOwner(own);

          cityTiles.put(to, city);
        }

      }
      worldChangedAt(from);
      worldChangedAt(to);
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

    int total_moves = Math.max(row, column);

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
      //reset();
      round = round+1;
      int size = cityTiles.size();

      for(int row=0; row<=size; row++)
      {
        for(int column=0; column<=size; column++)
        {
          Position position = new Position(row, column);
          City city = new CityImpl();
          city = cityTiles.get(position);

          if(cityTiles.get(position) != null)
          {
            int current = city.getTreasury();
            ((CityImpl)city).setTreasury(current+6);
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
    turnEnds(getPlayerInTurn(), age);
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance )
  {
    ((CityImpl)this.getCityAt(p)).setWorkforceFocus(balance);
    worldChangedAt(p);
  }

  public void changeProductionInCityAt( Position position, String unitType )
  {
    City city = cityTiles.get(position);
    ((CityImpl)city).setProduction(unitType);
    worldChangedAt(position);
  }

  public void performUnitActionAt( Position position )
  {
    boolean action;

    action = unitActionStrategy.getAction(position, this);

    worldChangedAt(position);

  }

  public void reset()
  {
    for(int i=0; i<GameConstants.WORLDSIZE; i++)
    {
      for(int j=0; j<GameConstants.WORLDSIZE; j++)
      {
        Position p = new Position(i,j);
        Unit u = unitTiles.get(p);

        if(u != null)
        {
          ((UnitImpl)u).setMovecount(1);
          unitTiles.put(p, u);
          worldChangedAt(p);
        }
      }
    }
  }

  public void createCity(Position position, Player owner)
  {
    City city = new CityImpl();
    ((CityImpl)city).setOwner(owner);

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

  private int costOfUnit(String unitType)
  {
    int cost;

    if(unitType == GameConstants.ARCHER) {
      cost = 10;
      return cost;
    }
    else if(unitType == GameConstants.LEGION) {
      cost = 15;
      return cost;
    }
    else if(unitType == GameConstants.SETTLER)
    {
      cost = 30;
      return cost;
    }
    else if(unitType == GameConstants.UFO)
    {
      cost = 60;
      return cost;
    }
    else
    {
      return -1;
    }
  }

  public void addObserver(GameObserver observer)
  {
    observers.add(observer);
  }

  public void setTileFocus(Position position)
  {
    for(GameObserver obs : observers)
    {
      obs.tileFocusChangedAt(position);
    }
  }

  public void worldChangedAt(Position pos)
  {
    for (GameObserver obs: observers)
    {
      obs.worldChangedAt(pos);
    }
  }

  private void turnEnds(Player nextPlayer, int age)
  {
    for( GameObserver obs: observers )
    {
      obs.turnEnds(nextPlayer, age);
    }
  }

}

