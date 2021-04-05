package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.factories.AlphaFactory;
import hotciv.factories.EpsilonFactory;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

public class TestEpsilonAttackStrategy {
    private Game game;
    private AttackStrategy epsilon_AttackStrategy;


    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonFactory(), new AlphaWorldLayoutStrategy());
        epsilon_AttackStrategy = new EpsilonAttackStrategy();
    }

    @Test
    public void AttackStratTest()
    {
        epsilon_AttackStrategy.attack(game, new Position(2,0), new Position(3,2));
    }
}
