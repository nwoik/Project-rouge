package core;

import core.spawns.CharacterSpawn;
import core.spawns.ObjectSpawn;
import object.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelLoader {

    private Handler handler;

    private CharacterSpawn characterSpawn;
    private ObjectSpawn skeletonSpawn;

    private BufferedImageLoader loader;
    private BufferedImage character, skeletonImg;
    private BufferedImage dungeon1;

    private SpriteSheet characterSheet, skeletonSheet;
    private SpriteSheet dungeon1Sheet;
    private Skeleton skeleton;

    private ReadCSVFile level1Floor, level1Walls, level1Spawns;
    private ReadCSVFile level2Floor, level2Walls, level2Spawns;
    private TileMap tileMap1, tileMap2;
    public Level level1, level2;


    public LevelLoader(Handler handler) throws IOException {
        this.handler = handler;
        this.loader = new BufferedImageLoader();

        this.dungeon1 = loader.loadImage("/Dungeon 1 atlas.png");
        this.dungeon1Sheet = new SpriteSheet(dungeon1);

        // Level 1 init
        this.level1Spawns = new ReadCSVFile("src/main/java/core/levels/BoxMap_Spawns.csv");
        this.level1Walls = new ReadCSVFile("src/main/java/core/levels/BoxMap_Walls.csv");
        this.level1Floor = new ReadCSVFile("src/main/java/core/levels/BoxMap_Floor.csv");
        this.tileMap1 = new TileMap(dungeon1Sheet);
        this.level1 = new Level(tileMap1, level1Floor, level1Walls, level1Spawns);

        // Level 2 init
        this.level2Spawns = new ReadCSVFile("src/main/java/core/levels/DC1_Spawns.csv");
        this.level2Walls = new ReadCSVFile("src/main/java/core/levels/DC1_Walls.csv");
        this.level2Floor = new ReadCSVFile("src/main/java/core/levels/DC1_Floor.csv");
        this.tileMap2 = new TileMap(dungeon1Sheet);
        this.level2 = new Level(tileMap2, level2Floor, level2Walls, level2Spawns);

        this.character = loader.loadImage("/Player/Character_Atlas.png");
        this.characterSheet = new SpriteSheet(this.character);
        this.characterSpawn = new CharacterSpawn(this.handler, this.characterSheet);

        this.skeletonImg = loader.loadImage("/Enemies/Skeleton_Atlas.png");
        this.skeletonSheet = new SpriteSheet(this.skeletonImg);
    }

    //might be useful for loading new level
    private void clearLevel() {
        handler.emptyList();
    }



    //loads level given buffered image
    public void loadLevel(Level level){
        clearLevel();

        for (String[] row : level.floorFile.rows) {
            int yy = level.floorFile.rows.indexOf(row)*64;
            int x = 0;
            for (String item: row) {
                int xx = x*64;
                Floor floor;
                switch (item) {
                    case "0":
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 1, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "1":
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 1, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "2":
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 1, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "5":
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 2, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "6":
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 2, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "7":
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 2, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "11":
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 3, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "12":
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 3, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;

                }
                x++;
            }
        }

        for (String[] row : level.wallFile.rows) {
            int yy = level.wallFile.rows.indexOf(row) * 64;
            int x = 0;
            for (String item : row) {
                int xx = x * 64;
                Wall wall;
                switch (item) {
                    case "15":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 4, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "16":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 44, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 4, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "17":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 4, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "20":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 5, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "21":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 5, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "22":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 5, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "25":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 44, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 6, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "26":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 44, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 6, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "27":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 6, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "28":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(4, 6, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "31":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 44, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 7, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "32":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 7, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "33":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(4, 7, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "35":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 8, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "36":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 8, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "37":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 8, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "38":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(4, 8, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "40":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 9, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "42":
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 9, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                }
                x++;

            }
        }
        for (String[] row : level.spawnFile.rows) {
            int yy = level.spawnFile.rows.indexOf(row)*64;
            int x = 0;
            for (String item: row) {
                int xx = x*64;
                switch (item) {
                    case "0":
                        System.out.println("Spawn");
                        this.characterSpawn.loadCharacter(xx, yy);
                        break;
                    case "1":
                        this.skeletonSpawn = new ObjectSpawn(this.handler, new Skeleton(xx, yy, ID.Enemy, this.handler, this.skeletonSheet));
                        this.skeletonSpawn.loadObject();
                        break;
                }
                x++;
            }
        }
    }
}
