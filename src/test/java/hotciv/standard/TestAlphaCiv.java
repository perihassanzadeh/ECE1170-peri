package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
  private Game game;
  private AgeStrategy alpha_agingstrategy;
  private AgeStrategy beta_agingStrategy;
  private UnitActionStrategy alpha_UnitActionStrategy;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new Strategy());
    alpha_agingstrategy =  new AlphaAgeStrategy();
    beta_agingStrategy = new BetaAgeStrategy();
    alpha_UnitActionStrategy = new AlphaUnitActionStrategy();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    // TODO: reenable the assert below to get started...
     assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void startDate()
  {
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void defaultWinner()
  {
      Player play = game.getPlayerInTurn();

      if(play == Player.RED && game.getAge()==3000)
      {
        assertThat(game.getWinner(), is(Player.RED));
      }
      else
      {
        assertThat(game.getWinner(), is(nullValue()));
      }
  }

  @Test
  public void incrementAge()
  {
    assertThat(game.getAge(), is(-4000));
    //end red turn
    game.endOfTurn();
    //end blue turn
    game.endOfTurn();
    assertThat(game.getAge(), is(-3900));
  }

  @Test
  public void blueTurnAfterRed()
  {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void twoPlayers()
  {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void initialTiles()
  {
    Position p = new Position(1,1);
    Tile t = game.getTileAt(p);
    assertThat(t.getTypeString(), is(GameConstants.PLAINS));

    Position ocean = new Position(1,0);
    Tile t_ocean = game.getTileAt(ocean);
    assertThat(t_ocean.getTypeString(), is(GameConstants.OCEANS));

    Position hills = new Position(0,1);
    Tile t_plains = game.getTileAt(hills);
    assertThat(t_plains.getTypeString(), is(GameConstants.HILLS));

    Position mountain = new Position(2,2);
    Tile t_mountain = game.getTileAt(mountain);
    assertThat(t_mountain.getTypeString(), is(GameConstants.MOUNTAINS));

    Position red_city = new Position(1,1);
    Tile t_city = game.getTileAt(red_city);
  }

  @Test
  public void MoveUnitTest()
  {
    Position from = new Position(2,0);
    Position to = new Position(2,1);

    assertThat(game.moveUnit(from, to), is(Boolean.TRUE));

    Unit u = game.getUnitAt(to);
    //assertThat(u.getMoveCount(), is(2));

  }

  @Test
  public void cityTest()
  {
    Position red = new Position(1,1);
    Position blue = new Position(4,1);

    City redCity = game.getCityAt(red);
    assertThat(redCity.getOwner(), is(Player.RED));

    City blueCity = game.getCityAt(blue);
    assertThat(blueCity.getOwner(), is(Player.BLUE));
  }

  @Test
  public void cityPopulationTest()
  {
    Position red = new Position(1,1);
    City redCity = game.getCityAt(red);
    assertThat(redCity.getSize(), is(1));
  }

  @Test
  public void cityTreasuryTest()
  {
    //Initially Treasury is 0
    Position red = new Position(1,1);
    City redCity = game.getCityAt(red);
    assertThat(redCity.getTreasury(), is(0));
  }

  @Test
  public void initUnits()
  {
    Position rArc = new Position(2,0);
    Position bLeg = new Position(3,2);
    Position rSet = new Position(4,3);

    Unit r_a = game.getUnitAt(rArc);
    assertThat(r_a.getTypeString(), is(GameConstants.ARCHER));

    Unit b_l = game.getUnitAt(bLeg);
    assertThat(b_l.getTypeString(), is(GameConstants.LEGION));

    Unit r_s = game.getUnitAt(rSet);
    assertThat(r_s.getTypeString(), is(GameConstants.SETTLER));
  }

  @Test
  public void attackingUnitWins()
  {
    Position rArc = new Position(2,0);
    Position bLeg = new Position(3,2);

    Unit redArcherr = game.getUnitAt(rArc);
    Unit blueLegion = game.getUnitAt(bLeg);
    assertThat(redArcherr.getTypeString(), is(GameConstants.ARCHER));
    assertThat(blueLegion.getTypeString(), is(GameConstants.LEGION));

    Unit redArcherBeforeMove = game.getUnitAt(rArc);
    assertThat(redArcherBeforeMove.getOwner(), is(Player.RED));

    assertThat(game.moveUnit(rArc, new Position(2,1)), is(true));
    assertThat(game.moveUnit(new Position(2,1), new Position(3,1)), is(true));
    //assertThat(game.moveUnit(new Position(3,1), new Position(3,2)), is(true));


    Unit attacker = game.getUnitAt(bLeg);
    //assertThat(attacker.getTypeString(), is(GameConstants.ARCHER));
    //assertThat(attacker.getOwner(), is(Player.RED));
  }

  @Test
  public void unitActionsTest()
  {
    Position rArcher = new Position(2,0);
    Unit redArcher = game.getUnitAt(rArcher);

    assertThat(alpha_UnitActionStrategy.getAction(rArcher, game), is(Boolean.FALSE));
  }
}
