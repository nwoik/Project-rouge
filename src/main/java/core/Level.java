package core;

import java.util.Dictionary;

public class Level {
    public TileMap tileMap;
    public ReadCSVFile floorFile;
    public ReadCSVFile wallFile;
    public ReadCSVFile spawnFile;

    public Level(TileMap tileMap, ReadCSVFile floorFile, ReadCSVFile wallFile, ReadCSVFile spawnFile) {
        this.tileMap = tileMap;
        this.floorFile = floorFile;
        this.wallFile = wallFile;
        this.spawnFile = spawnFile;
    }
}
