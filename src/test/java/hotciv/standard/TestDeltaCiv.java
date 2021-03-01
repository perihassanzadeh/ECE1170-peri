package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

public class TestDeltaCiv {
    private Game game;
    private WorldLayoutStrategy alpha_worldLayoutStrategy;
    private WorldLayoutStrategy delta_worldLayoutStrategy;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new Strategy());
        alpha_worldLayoutStrategy =  new AlphaWorldLayoutStrategy();
        delta_worldLayoutStrategy = new DeltaWorldLayoutStrategy();
    }

    @Test
    public void checkTileImplementation()
    {
        /*
        HashMap<Position, Tile> boardTiles = new HashMap<Position, Tile>();
        boardTiles = delta_worldLayoutStrategy.createLayout(game);

        //Ocean Tile
        Position ocean = new Position(0,0);
        Tile oceantile = game.getTileAt(ocean);

        assertThat(boardTiles.get(oceantile), is(GameConstants.OCEANS));

        //Plains Tile
        Position plains = new Position(0,4);
        Tile plainstile = game.getTileAt(plains);

        assertThat(boardTiles.get(plainstile), is(GameConstants.PLAINS));

        //Mountains Tile
        Position mountain = new Position(3,7);
        Tile mountaintile = game.getTileAt(mountain);

        assertThat(boardTiles.get(mountaintile), is(GameConstants.MOUNTAINS));

        //Forest Tile
        Position forest = new Position(2,10);
        Tile foresttile = game.getTileAt(forest);

        assertThat(boardTiles.get(foresttile), is(GameConstants.FOREST));

        //Hills Tile
        Position hills = new Position(15,5);
        Tile hillstile = game.getTileAt(hills);

        assertThat(boardTiles.get(hillstile), is(GameConstants.HILLS));
         */
    }
}
