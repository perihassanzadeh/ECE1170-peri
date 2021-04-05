package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import hotciv.factories.*;

public class TestThetaCiv {
    private Game game;

    @Before
    public void setUp(){
        game = new GameImpl(new ThetaFactory(), new AlphaWorldLayoutStrategy());
    }

    @Test
    public void thetaCiv(){
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void initProduction()
    {
        City redCity = game.getCityAt(new Position(1,1));

        assertThat(redCity.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void createUfo()
    {
        Position redCity = new Position(1,1);
        City red = game.getCityAt(redCity);

        red.setProduction(GameConstants.UFO);

        assertThat(red.getProduction(), is(GameConstants.UFO));

    }
}
