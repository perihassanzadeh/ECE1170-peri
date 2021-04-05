package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import hotciv.factories.*;

public class TestSemiCiv {
    private Game game;


    @Before
    public void setUp(){
        game = new GameImpl(new SemiFactory(), new DeltaWorldLayoutStrategy());
    }

    @Test
    public void semiCiv(){
        assertThat(game.getWinner(), is(nullValue()));

    }
}
