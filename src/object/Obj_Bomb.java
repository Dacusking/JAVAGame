package object;

import Entity.entity;
import LaCirese.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Obj_Bomb extends entity {
    public Obj_Bomb(GamePanel gp){
        super(gp);
        solidAreaDefaultX=0;
        solidAreaDefaultY=0;
        solidArea=new Rectangle(0,0,40,40);
        name="Bomb";
        down1=setup("res/objects/Bomba");
        collision=false;
    }
}