package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.factories.BetaFactory;
import hotciv.framework.*;

import hotciv.variants.*;
import org.hamcrest.Factory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

public class TestBetaCiv {
    private Game game;
    private AgeStrategy beta_agingStrategy;
    private WinnerStrategy beta_winningStrategy;
    private BetaFactory betaFactory;
    private AlphaWorldLayoutStrategy alphaWorldLayoutStrategy;

    /** Fixture for betaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaFactory(), new AlphaWorldLayoutStrategy());
        beta_agingStrategy = new BetaAgeStrategy();
    }

    @Test
    public void shouldBeRedAsStartingPlayer() {
        assertThat(game, is(notNullValue()));
        // TODO: reenable the assert below to get started...
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void betaAging()
    {
        //Checking logic for beta aging
        assertThat(beta_agingStrategy.calcNextWorldAge(-4000), is(-3900));
        assertThat(beta_agingStrategy.calcNextWorldAge(-100), is(-1));
        assertThat(beta_agingStrategy.calcNextWorldAge(-1), is(1));
        assertThat(beta_agingStrategy.calcNextWorldAge(1), is(50));
        assertThat(beta_agingStrategy.calcNextWorldAge(50), is(100));
        assertThat(beta_agingStrategy.calcNextWorldAge(1800), is(1825));
        assertThat(beta_agingStrategy.calcNextWorldAge(1900), is(1905));
        assertThat(beta_agingStrategy.calcNextWorldAge(1975), is(1976));
    }

    @Test
    public void testBetaWin()
    {

    }

}
