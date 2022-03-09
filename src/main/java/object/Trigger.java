package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Trigger {
    public int x,y,height, width;
    public GameObject actionObject, targetObject;

    public Trigger(int x, int y, GameObject actionObject, GameObject targetObject) {
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.actionObject = actionObject; // Object doing the action
        this.targetObject = targetObject; // The target of that action
    }
}
