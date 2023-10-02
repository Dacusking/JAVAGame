package object;

import Entity.entity;
import LaCirese.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Obj_Heart extends entity {
    public Obj_Heart(GamePanel gp){
        super(gp);
        solidAreaDefaultX=0;
        solidAreaDefaultY=0;
        solidArea=new Rectangle(0,0,40,40);
        name="Heart";
        image=setup("res/objects/heart_full");
        image2=setup("res/objects/heart_blank");
        collision=false;
    }
}
