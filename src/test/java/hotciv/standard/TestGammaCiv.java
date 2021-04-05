package hotciv.standard;

import com.sun.tools.attach.AgentInitializationException;
import hotciv.factories.AlphaFactory;
import hotciv.factories.GammaFactory;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

public class TestGammaCiv {
    private Game game;
    private AlphaUnitActionStrategy alpha_unitStrategy;
    private GammaUnitActionStrategy gamma_unitStrategy;

    @Before
    public void setUp() {
        game = new GameImpl(new GammaFactory(), new AlphaWorldLayoutStrategy());
        alpha_unitStrategy = new AlphaUnitActionStrategy();
        gamma_unitStrategy = new GammaUnitActionStrategy();
    }

    @Test
    public void unitStratTest()
    {
        Position rArch = new Position(2,0);
        Position rSettler = new Position(4,3);
        Position noUnit = new Position(0,0);

        assertThat(gamma_unitStrategy.getAction(rArch, game), is(Boolean.TRUE));
        assertThat(gamma_unitStrategy.getAction(rSettler, game), is(Boolean.TRUE));
        assertThat(gamma_unitStrategy.getAction(noUnit, game), is(Boolean.FALSE));
    }

}
