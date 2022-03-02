package core;

import core.spawns.CharacterSpawn;
import object.*;

public class LevelLoader {
    private Handler handler;
    private CharacterSpawn characterSpawn;
    int playerSpawnX, playerSpawnY;

    public LevelLoader(Handler handler, CharacterSpawn characterSpawn) {
        this.handler = handler;
        this.characterSpawn = characterSpawn;


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
                        System.out.println("Spawn character");
                        this.characterSpawn.loadCharacter(xx, yy);
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 1, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "1":
                        System.out.println(item + " " + xx + " " + yy);
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 1, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "2":
                        System.out.println(item + " " + xx + " " + yy);
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 1, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "5":
                        System.out.println(item + " " + xx + " " + yy);
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 2, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "6":
                        System.out.println(item + " " + xx + " " + yy);
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 2, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "7":
                        System.out.println(item + " " + xx + " " + yy);
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 2, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "11":
                        System.out.println(item + " " + xx + " " + yy);
                        floor = new Floor(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.floor);
                        floor.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 3, 64, 64));
                        handler.addObject(floor, handler.floors);
                        break;
                    case "12":
                        System.out.println(item + " " + xx + " " + yy);
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
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 44, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 4, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "17":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 4, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "20":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 5, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "21":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 5, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "22":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 5, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "25":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 44, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 6, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "26":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 44, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 6, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "27":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 6, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "28":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(4, 6, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "31":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 44, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 7, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "32":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 7, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "33":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, -44);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(4, 7, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "35":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 8, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "36":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(2, 8, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "37":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 8, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "38":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(4, 8, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "40":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(1, 9, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                    case "42":
                        System.out.println(item + " " + xx + " " + yy);
                        wall = new Wall(xx, yy, ID.Block, level.tileMap.tiles.get(item), BlockID.wall, 0, 0);
                        wall.setBlock_image(level.tileMap.tiles.get(item).grabImage(3, 9, 64, 64));
                        handler.addObject(wall, handler.walls);
                        break;
                }
                x++;
            }
        }
    }
}
