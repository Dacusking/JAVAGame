package object;

import Entity.entity;
import LaCirese.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Obj_Papuc extends entity {
    public Obj_Papuc(GamePanel gp){
        super(gp);
        solidAreaDefaultX=0;
        solidAreaDefaultY=0;
        solidArea=new Rectangle(0,0,40,40);
        name="Papuc";
        down1=setup("res/objects/Papuc");
        collision=false;
    }
}
