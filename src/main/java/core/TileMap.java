package core;

import object.Block;
import object.Wall;
import org.w3c.dom.Node;

import java.awt.image.BufferedImage;
import java.util.*;

public class TileMap {
    private SpriteSheet spriteSheet;
    private SpriteSheet floorTile0, floorTile1, floorTile2, floorTile3, floorTile4, floorTile5, floorTile6, floorTile7;
    public Map<String, SpriteSheet> floorTiles = new HashMap<>();

    public Wall horizontalWallTile, verticalWallTileR, verticalWallTileL,
            separatorTop, separatorMiddle, separatorBottom,
            topLeft, bottomLeft, topRight, bottomRight;

    public TileMap(SpriteSheet spriteSheet){
        this.spriteSheet = spriteSheet;
        this.floorTile0 = new SpriteSheet(this.spriteSheet.grabImage(1, 1, 16, 16));
        this.floorTile1 = new SpriteSheet(this.spriteSheet.grabImage(2, 1, 16, 16));
        this.floorTile2 = new SpriteSheet(this.spriteSheet.grabImage(3, 1, 16, 16));
        this.floorTile3 = new SpriteSheet(this.spriteSheet.grabImage(1, 2, 16, 16));
        this.floorTile4 = new SpriteSheet(this.spriteSheet.grabImage(2, 2, 16, 16));
        this.floorTile5 = new SpriteSheet(this.spriteSheet.grabImage(3, 2, 16, 16));
        this.floorTile6 = new SpriteSheet(this.spriteSheet.grabImage(2, 3, 16, 16));
        this.floorTile7 = new SpriteSheet(this.spriteSheet.grabImage(3, 3, 16, 16));

        this.floorTiles.put("0", this.floorTile0);
        this.floorTiles.put("1", this.floorTile1);
        this.floorTiles.put("2", this.floorTile2);
        this.floorTiles.put("5", this.floorTile3);
        this.floorTiles.put("6", this.floorTile4);
        this.floorTiles.put("7", this.floorTile5);
        this.floorTiles.put("11", this.floorTile6);
        this.floorTiles.put("12", this.floorTile7);


    }
}
