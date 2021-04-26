package hotciv.standard;

import hotciv.factories.BetaFactory;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.BetaWinnerStrategy;
import hotciv.variants.WinnerStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestBetaWinnerStrategy {
    private Game game;
    private WinnerStrategy winnerStrategy;

    @Before
    public void setUp()
    {
        game = new GameImpl(new BetaFactory(), new AlphaWorldLayoutStrategy());
        winnerStrategy = new BetaWinnerStrategy();
    }

    @Test
    public void noInitialWinner()
    {
        Player p = winnerStrategy.getWinner(game);
        assertThat(p, is(nullValue()));
    }
}
