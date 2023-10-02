package Entity;

import LaCirese.GamePanel;
import LaCirese.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class NPC_Dog extends entity{

    public NPC_Dog(GamePanel gp){
        super(gp);

        direction="down";
        speed=5;
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
        up1=setup("DogUp1");
        up2=setup("DogUp2");
        down1=setup("DogDown1");
        down2=setup("DogDown2");
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
        if(actionLockCounter==120){
            if(direction=="down"){
                direction="up";
            }
            else if(direction=="up"){
                direction="down";
            }
            actionLockCounter=0;
        }

    }
}
