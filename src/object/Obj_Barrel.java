package object;

import Entity.entity;
import LaCirese.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Obj_Barrel extends entity {
    public Obj_Barrel(GamePanel gp){
        super(gp);
        solidAreaDefaultX=0;
        solidAreaDefaultY=0;
        solidArea=new Rectangle(0,0,40,40);
        name="Barrel";
        down1=setup("res/objects/BarrelTile3");
        collision=true;
    }
}