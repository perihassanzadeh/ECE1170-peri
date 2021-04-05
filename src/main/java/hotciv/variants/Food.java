package hotciv.variants;

import hotciv.framework.Tile;
import hotciv.framework.GameConstants;

import java.util.Comparator;

public class Food implements Comparator<Tile>{

    public int compare(Tile tile1, Tile tile2)
    {
        String firstTileType = tile1.getTypeString();
        String secondTileType = tile2.getTypeString();

        int prod1 = getProdVal(firstTileType);
        int prod2 = getProdVal(secondTileType);

        int food1 = getFoodVal(firstTileType);
        int food2 = getFoodVal(secondTileType);

        if(food1-food2 == 0)
        {
            return prod2 - prod1;
        }

        return food2 - food1;
    }

    public int getProdVal(String tileType)
    {
        int prodVal = 0;

        if(tileType == GameConstants.FOREST)
        {
            prodVal = GameConstants.forestProd;
        }

        if(tileType == GameConstants.HILLS)
        {
            prodVal = GameConstants.hillsProd;
        }

        if(tileType == GameConstants.MOUNTAINS)
        {
            prodVal = GameConstants.mountainProd;
        }

        return prodVal;
    }

    public int getFoodVal(String tileType)
    {
        int foodVal = 0;

        if (tileType == (GameConstants.PLAINS))
        {
            foodVal = GameConstants.plainsFood;
        }

        if (tileType == (GameConstants.OCEANS))
        {
            foodVal = GameConstants.oceanFood;
        }

        return foodVal;
    }

}
