package Entity;

import LaCirese.GamePanel;
import LaCirese.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class NPC_Cat extends entity{

    public NPC_Cat(GamePanel gp){
        super(gp);

        direction="right";
        speed=6;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 15;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width = 24;
        solidArea.height = 25;
        getNPCImage();
    }
    public void getNPCImage(){
        left1=setup("CatLeft1");
        left2=setup("CatLeft2");
        right1=setup("CatRight1");
        right2=setup("CatRight2");
    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool=new UtilityTool();
        BufferedImage Image=null;

        try{
            Image= ImageIO.read(new FileInputStream("res/NPC/"+imageName+".png"));
            Image=uTool.scaleImage(Image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return Image;
    }

    public void setAction(){

        actionLockCounter++;
            if((actionLockCounter2>20 && actionLockCounter2<60) || (actionLockCounter2>90 && actionLockCounter2<130)
            || (actionLockCounter2>160 && actionLockCounter2<200)) {
                gp.npc[2][4].speed = 0;
                gp.npc[2][5].speed = 0;
            }
            else{
                gp.npc[2][4].speed = 6;
                gp.npc[2][5].speed = 6;
            }
        actionLockCounter2++;
        if(actionLockCounter==240){
            if(direction=="left"){
                direction="right";
            }
            else if(direction=="right"){
                direction="left";
            }
            actionLockCounter=0;
            actionLockCounter2=0;
        }

    }
}
