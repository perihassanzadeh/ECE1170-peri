package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.factories.AlphaFactory;
import hotciv.factories.BetaFactory;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

public class TestDeltaCiv {
    private Game game;
    private DeltaWorldLayoutStrategy deltaWorldLayoutStrategy;
    private WorldLayoutStrategy world;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaFactory(), new DeltaWorldLayoutStrategy());
        world = new DeltaWorldLayoutStrategy();
    }

    @Test
    public void checkTileImplementation()
    {
        world.createLayout(game);

        //Ocean Tile
        Position ocean = new Position(0,0);
        Tile oceantile = game.getTileAt(ocean);
        assertThat(oceantile.getTypeString(), is(GameConstants.OCEANS));

        //Plains Tile
        Position plains = new Position(0,4);
        Tile plainstile = game.getTileAt(plains);

        assertThat(plainstile.getTypeString(), is(GameConstants.PLAINS));

        //Mountains Tile
        Position mountain = new Position(0,5);
        Tile mountaintile = game.getTileAt(mountain);

        assertThat(mountaintile.getTypeString(), is(GameConstants.MOUNTAINS));

        //Forest Tile
        Position forest = new Position(1,9);
        Tile foresttile = game.getTileAt(forest);

        assertThat(foresttile.getTypeString(), is(GameConstants.FOREST));

        //Hills Tile
        Position hills = new Position(1,3);
        Tile hillstile = game.getTileAt(hills);

        assertThat(hillstile.getTypeString(), is(GameConstants.HILLS));

    }
}
