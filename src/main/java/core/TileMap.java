package core;

import java.util.*;

public class TileMap {
    private SpriteSheet spriteSheet;
    public SpriteSheet floorTile0, floorTile1, floorTile2, floorTile3, floorTile4, floorTile5, floorTile6, floorTile7;
    public Map<String, SpriteSheet> tiles = new HashMap<>();

    public SpriteSheet horizontalWallTile, verticalWallTileR, verticalWallTileL,
            separatorTop, separatorMiddle, separatorBottom,
            topLeft, bottomLeft, topRight, bottomRight, baseLeft, baseMiddle, baseRight;

    public TileMap(SpriteSheet spriteSheet){
        this.spriteSheet = spriteSheet;
        this.floorTile0 = this.spriteSheet;
        this.floorTile1 = this.spriteSheet;
        this.floorTile2 = this.spriteSheet;
        this.floorTile3 = this.spriteSheet;
        this.floorTile4 = this.spriteSheet;
        this.floorTile5 = this.spriteSheet;
        this.floorTile6 = this.spriteSheet;
        this.floorTile7 = this.spriteSheet;

        this.horizontalWallTile = this.spriteSheet;
        this.verticalWallTileR = this.spriteSheet;
        this.verticalWallTileL = this.spriteSheet;
        this.separatorTop = this.spriteSheet;
        this.separatorMiddle = this.spriteSheet;
        this.separatorBottom = this.spriteSheet;
        this.topLeft = this.spriteSheet;
        this.bottomLeft = this.spriteSheet;
        this.topRight = this.spriteSheet;
        this.bottomRight = this.spriteSheet;
        this.baseLeft = this.spriteSheet;
        this.baseMiddle = this.spriteSheet;
        this.baseRight = this.spriteSheet;

        this.tiles.put("0", this.floorTile0);
        this.tiles.put("1", this.floorTile1);
        this.tiles.put("2", this.floorTile2);
        this.tiles.put("5", this.floorTile3);
        this.tiles.put("6", this.floorTile4);
        this.tiles.put("7", this.floorTile5);
        this.tiles.put("11", this.floorTile6);
        this.tiles.put("12", this.floorTile7);
        this.tiles.put("15", this.horizontalWallTile);
        this.tiles.put("16", this.verticalWallTileR);
        this.tiles.put("17", this.verticalWallTileL);
        this.tiles.put("20", this.separatorTop);
        this.tiles.put("21", this.separatorMiddle);
        this.tiles.put("22", this.separatorBottom);
        this.tiles.put("25", this.topLeft);
        this.tiles.put("26", this.bottomLeft);
        this.tiles.put("27", this.topRight);
        this.tiles.put("28", this.bottomRight);
        this.tiles.put("31", this.baseLeft);
        this.tiles.put("32", this.baseMiddle);
        this.tiles.put("33", this.baseRight);


    }
}
