package hotciv.standard;

import hotciv.factories.AlphaFactory;
import hotciv.variants.*;
import org.junit.Before;
import hotciv.factories.*;
import hotciv.framework.*;
import org.junit.Test;

public class TestGameDecorator {

    Game theGame;

    @Before
    public void setUp() {
        theGame = new GameDecorator(new GameImpl(new AlphaFactory(), new AlphaWorldLayoutStrategy()));
    }

    @Test
    public void decoratorTest()
    {
        theGame = new GameDecorator(new GameImpl(new AlphaFactory(), new AlphaWorldLayoutStrategy()));

        //changing city at 1,1 work focus to food
        Position p = new Position(1,1);
        theGame.changeWorkForceFocusInCityAt(p, GameConstants.foodFocus);
        CityImpl city = (CityImpl) theGame.getCityAt(p);

        //move red archer
        theGame.moveUnit(new Position(2, 0), new Position(2, 1));
        theGame.endOfTurn();

        //move blue legion
        theGame.moveUnit(new Position(3, 2), new Position(3, 1));
        theGame.endOfTurn();

        //print out the transcript
        GameDecorator game = (GameDecorator) theGame;
        System.out.println(game.getTranscript());
    }
}
