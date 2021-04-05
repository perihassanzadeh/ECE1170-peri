package hotciv.standard;

import hotciv.factories.AlphaFactory;
import hotciv.factories.EpsilonFactory;
import hotciv.framework.*;
import hotciv.variants.Strategy;
import org.junit.*;
import static org.junit.Assert.*;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.variants.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class TestEpsilonWinnerStrategy {
    private Game game;
    private EpsilonWinnerStrategy epsilonWinnerStrategy;
    private WinnerStrategy winnerStrategy = new EpsilonWinnerStrategy();

    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonFactory(), new AlphaWorldLayoutStrategy());
        epsilonWinnerStrategy = new EpsilonWinnerStrategy();
    }

    @Test
    public void WinnerStratTest()
    {
        assertNotNull(game.getUnitAt(new Position(2, 0)));
        game.moveUnit(new Position(2, 0), new Position(3, 1));
        game.moveUnit(new Position(3, 1), new Position(3, 2));
        assertThat(winnerStrategy.getWinner(game), is(nullValue()));

    }
}
