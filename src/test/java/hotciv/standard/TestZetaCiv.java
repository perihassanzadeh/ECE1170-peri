package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import hotciv.factories.*;

public class TestZetaCiv {
    private Game game;
    private ZetaWinnerStrategy zetaWinnerStrategy;

    @Before
    public void setUp(){
        //game = new GameImpl(new ZetaFactory(), new TestEpsilonWorld());
        game = new GameImpl(new Strategy());
        zetaWinnerStrategy = new ZetaWinnerStrategy(new BetaWinnerStrategy(), new EpsilonWinnerStrategy());
    }

    @Test
    public void zetaCiv(){
        assertThat(game.getWinner(), is(nullValue()));

    }
}
