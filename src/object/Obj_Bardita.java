package object;

import Entity.entity;
import LaCirese.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Obj_Bardita extends entity {
    public Obj_Bardita(GamePanel gp){
        super(gp);
        solidAreaDefaultX=0;
        solidAreaDefaultY=0;
        solidArea=new Rectangle(0,0,40,40);
        name="Bardita";
        down1=setup("res/objects/Bardita");
        collision=false;
    }
}