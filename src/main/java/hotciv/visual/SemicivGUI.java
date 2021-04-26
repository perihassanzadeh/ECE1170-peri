package hotciv.visual;

import hotciv.factories.SemiFactory;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.variants.DeltaWorldLayoutStrategy;
import hotciv.view.CompositeTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class SemicivGUI {
    public static void main(String[] args) {
        Game game = new GameImpl(new SemiFactory(), new DeltaWorldLayoutStrategy());

        DrawingEditor editor =
                new MiniDrawApplication("Click anywhere to see Drawing updates",
                        new HotCivFactory4(game));
        editor.open();
        editor.setTool(new CompositeTool(game, editor));
    }
}
