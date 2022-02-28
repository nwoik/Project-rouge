package core;

import java.util.Dictionary;

public class Level {
    public TileMap tileMap;
    public ReadCSVFile floorFile;
    public ReadCSVFile wallFile;

    public Level(TileMap tileMap, ReadCSVFile floorFile) {
        this.tileMap = tileMap;
        this.floorFile = floorFile;
//        this.wallFile = wallFile;

    }
}
