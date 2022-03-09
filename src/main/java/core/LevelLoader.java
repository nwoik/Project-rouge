package core;

import core.spawns.CharacterSpawn;
import core.spawns.ObjectSpawn;
import object.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class LevelLoader {
    private Handler handler;

    private CharacterSpawn characterSpawn;
    private ObjectSpawn skeletonSpawn, torchSpawn, eyeSpawn, slimeSpawn, buttonSpawn, warpSpawn;

    private BufferedImageLoader loader;
    private BufferedImage character, skeletonImg, torchImg, eyeImg, slimeImg, buttonImg, warpImg;
    private BufferedImage dungeon1;

    private SpriteSheet characterSheet, skeletonSheet, torchSheet, eyeSheet, slimeSheet, buttonSheet, warpSheet;
    private SpriteSheet dungeon1Sheet;

    private ReadCSVFile level1Floor, level1Walls, level1Spawns;
    private ReadCSVFile level2Floor, level2Walls, level2Spawns;
    private ReadCSVFile level3Floor, level3Walls, level3Spawns;
    private ReadCSVFile level4Floor, level4Walls, level4Spawns;

    private TileMap tileMap1, tileMap2, tileMap3, tileMap4;
    public Level level, level1, level2, level3, level4;

    public LinkedList<Level> levelList;

    public int currentLevel;


    public LevelLoader(Handler handler) throws IOException {
        this.handler = handler;
        this.loader = new BufferedImageLoader();

        this.dungeon1 = loader.loadImage("/Dungeon 1 atlas.png");
        this.dungeon1Sheet = new SpriteSheet(dungeon1);

        this.levelList = new LinkedList<Level>();

        // Level 1 init
        this.level1Spawns = new ReadCSVFile("src/main/java/core/levels/level2_objects.csv");
        this.level1Walls = new ReadCSVFile("src/main/java/core/levels/level2_walls.csv");
        this.level1Floor = new ReadCSVFile("src/main/java/core/levels/level2_floor.csv");
        this.tileMap1 = new TileMap(dungeon1Sheet);
        this.level1 = new Level(tileMap1, level1Floor, level1Walls, level1Spawns);
        this.levelList.add(this.level1);

        // Level 2 init
        this.level2Spawns = new ReadCSVFile("src/main/java/core/levels/DC1_Spawns.csv");
        this.level2Walls = new ReadCSVFile("src/main/java/core/levels/DC1_Walls.csv");
        this.level2Floor = new ReadCSVFile("src/main/java/core/levels/DC1_Floor.csv");
        this.tileMap2 = new TileMap(dungeon1Sheet);
        this.level2 = new Level(tileMap2, level2Floor, level2Walls, level2Spawns);
        this.levelList.add(this.level2);

        // Level 2 init
        this.level3Spawns = new ReadCSVFile("src/main/java/core/levels/level2_Spawns.csv");
        this.level3Walls = new ReadCSVFile("src/main/java/core/levels/level2_Walls.csv");
        this.level3Floor = new ReadCSVFile("src/main/java/core/levels/level2_Floor.csv");
        this.tileMap3 = new TileMap(dungeon1Sheet);
        this.level3 = new Level(tileMap3, level3Floor, level3Walls, level3Spawns);
        this.levelList.add(this.level3);

        this.level4Spawns = new ReadCSVFile("src/main/java/core/levels/level2_Spawns.csv");
        this.level4Walls = new ReadCSVFile("src/main/java/core/levels/level2_Walls.csv");
        this.level4Floor = new ReadCSVFile("src/main/java/core/levels/level2_Floor.csv");
        this.tileMap4 = new TileMap(dungeon1Sheet);
        this.level4 = new Level(tileMap4, level4Floor, level4Walls, level4Spawns);
        this.levelList.add(this.level4);

        this.character = loader.loadImage("/Player/Character_Atlas.png");
        this.characterSheet = new SpriteSheet(this.character);
        this.characterSpawn = new CharacterSpawn(this.handler, this.characterSheet);

        this.skeletonImg = loader.loadImage("/Enemies/Skeleton_Atlas.png");
        this.skeletonSheet = new SpriteSheet(this.skeletonImg);

        this.torchImg = loader.loadImage("/Objects/Torch_Atlas.png");
        this.torchSheet = new SpriteSheet(this.torchImg);

        this.eyeImg = loader.loadImage("/Enemies/Eye_Atlas.png");
        this.eyeSheet = new SpriteSheet(this.eyeImg);

        this.slimeImg = loader.loadImage("/Enemies/Slime Atlas.png");
        this.slimeSheet = new SpriteSheet(this.slimeImg);

        this.buttonImg = loader.loadImage("/Objects/Button.png");
        this.buttonSheet = new SpriteSheet(this.buttonImg);

        this.warpImg = loader.loadImage("/Objects/Warp_Zone.png");
        this.warpSheet = new SpriteSheet(this.warpImg);

        this.currentLevel = 0;
        this.level = this.levelList.get(this.currentLevel);
    }

    //might be useful for loading new level
    private void clearLevel() {
        handler.emptyList();
    }

    public void setLevel(Level newLevel) {
        this.level = newLevel;
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
                        this.skeletonSpawn.loadEnemy();
                        break;
                    case "2":
                        this.eyeSpawn = new ObjectSpawn(this.handler, new Eye(xx, yy, ID.Enemy, this.handler, this.eyeSheet));
                        this.eyeSpawn.loadEnemy();
                        break;
                    case "3":
                        this.slimeSpawn = new ObjectSpawn(this.handler, new Slime(xx, yy, ID.Enemy, this.handler, this.slimeSheet));
                        this.slimeSpawn.loadEnemy();
                        break;
                    case "5":
                        this.torchSpawn = new ObjectSpawn(this.handler, new Torch(xx, yy, this.handler, ID.Object, this.torchSheet));
                        this.torchSpawn.loadObject();
                        break;
                    case "6":
                        this.buttonSpawn = new ObjectSpawn(this.handler, new ButtonObject(xx, yy, this.handler, ID.Object, this.buttonSheet));
                        this.buttonSpawn.loadObject();
                        break;
                    case "7":
                        this.warpSpawn = new ObjectSpawn(this.handler, new WarpZone(xx, yy, this.handler, ID.Object, this.warpSheet, this));
                        this.warpSpawn.loadObject();
                        break;
                }
                x++;
            }
        }
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }
}
