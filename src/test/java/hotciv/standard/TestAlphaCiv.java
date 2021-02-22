package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.framework.*;

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

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
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

  }

  @Test
  public void MoveUnitTest()
  {
    Position from = new Position(1,1);
    Position to = new Position(1,3);

    assertThat(game.moveUnit(from, to), is(Boolean.TRUE));

    Unit u = game.getUnitAt(to);
    assertThat(u.getMoveCount(), is(2));
  }

  @Test
  public void cityInitTest()
  {
    Position red_city = new Position(1,1);
    City red = game.getCityAt(red_city);
    assertThat(red.getOwner(), is(Player.RED));

    Position blue_city = new Position(4,1);
    City blue = game.getCityAt(blue_city);
    assertThat(blue.getOwner(), is(Player.BLUE));

  }

  @Test
  public void unitInitTest()
  {
    //Red has archer at (2,0), Blue has legion at (3,2), red has settler at (4,3) - WORKING
    Position r_arch = new Position(2,0);
    Position b_leg = new Position(3,2);
    Position r_set = new Position(4,3);

    assertThat(game.getUnitAt(r_arch), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(b_leg), is(GameConstants.LEGION));
    assertThat(game.getUnitAt(r_set), is(GameConstants.SETTLER));
    Unit r_a = game.getUnitAt(r_arch);
    assertThat(r_a.getOwner(), is(Player.RED));
    Unit b_l = game.getUnitAt(b_leg);
    assertThat(b_l.getOwner(), is(Player.BLUE));
    Unit r_s = game.getUnitAt(r_set);
    assertThat(r_s.getOwner(), is(Player.RED));
  }
}
