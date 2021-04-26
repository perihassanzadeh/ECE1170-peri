package hotciv.standard;

import hotciv.factories.AlphaFactory;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.SpyGameObserver;
import hotciv.variants.AlphaWorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSpyObserver {

    Game game;
    @Before
    public void setup()
    {
        game = new GameImpl(new AlphaFactory(), new AlphaWorldLayoutStrategy());

    }

    @Test
    public void observerOfEmptyTile()
    {
        Position p1 = new Position(2,0);
        Position p2 = new Position(2,1);

        SpyGameObserver firstSpy = new SpyGameObserver(p1);
        SpyGameObserver secSpy = new SpyGameObserver(p2);

        game.addObserver(firstSpy);
        game.addObserver(secSpy);

        assertFalse(firstSpy.worldChanged());
        assertFalse(secSpy.worldChanged());

        game.moveUnit(p1, p2);
        assertTrue(firstSpy.worldChanged());
        assertTrue(secSpy.worldChanged());
    }

    @Test
    public void observerOfTileFocChange()
    {
        Position p1 = new Position(5,5);

        SpyGameObserver firstSpy = new SpyGameObserver(p1);
        game.addObserver(firstSpy);

        assertFalse(firstSpy.tileFocChanged());

        game.setTileFocus(p1);

        assertTrue(firstSpy.tileFocChanged());
    }

    @Test
    public void observerOfUnitAction()
    {
        Position redSet = new Position(4,3);
        SpyGameObserver firstSpy = new SpyGameObserver(redSet);

        game.addObserver(firstSpy);

        assertFalse(firstSpy.worldChanged());

        game.performUnitActionAt(redSet);
        assertTrue(firstSpy.worldChanged());
    }
}
