package core;

import core.spawns.CharacterSpawn;
import object.*;

import java.util.Arrays;

public class LevelLoader {
    private Handler handler;
    private SpriteSheet spriteSheet;
    private CharacterSpawn characterSpawn;

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
                switch (item) {
                    case "0":
                        characterSpawn.loadCharacter(xx, yy);
                        handler.floors.add(new Floor(xx, yy, ID.Block, level.tileMap.floorTiles.get(item), BlockID.floor));
                        break;
                }
                x++;
            }
        }
    }
}
