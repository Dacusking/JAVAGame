package Entity;

import java.io.*;
import LaCirese.GamePanel;
import LaCirese.KeyHandler;
import LaCirese.KeyHandler2;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class Bunica extends entity {


    KeyHandler2 keyH;

    public int stunDuration=0;
    int defaultSpeed=4;
    public int gotBombed=0;
    public Bunica(GamePanel gp, KeyHandler2 keyH) {

        super(gp);
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 18;
        solidArea.y = 31;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width = 4;
        solidArea.height = 8;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x=600;
        y=560;
        speed=4;
        direction="down";
    }
    public void setDefaultValues2(){
        x=160;
        y=200;
        speed=4;
        direction="down";
    }
    public void setDefaultValues3(){
        x=40*5;
        y=40*5;
        speed=4;
        direction="down";
    }
    public void getPlayerImage(){
        try{
            up1=  ImageIO.read(new FileInputStream("res/bunica/BunicaUp1.png"));
            up2=  ImageIO.read(new FileInputStream("res/bunica/BunicaUp3.png"));
            up3=  ImageIO.read(new FileInputStream("res/bunica/BunicaUp2.png"));
            down1= ImageIO.read(new FileInputStream("res/bunica/BunicaDown1.png"));
            down2= ImageIO.read(new FileInputStream("res/bunica/BunicaDown3.png"));
            down3= ImageIO.read(new FileInputStream("res/bunica/BunicaDown2.png"));
            left1= ImageIO.read(new FileInputStream("res/bunica/BunicaLeft1.png"));
            left2= ImageIO.read(new FileInputStream("res/bunica/BunicaLeft3.png"));
            left3= ImageIO.read(new FileInputStream("res/bunica/BunicaLeft2.png"));
            right1= ImageIO.read(new FileInputStream("res/bunica/BunicaRight1.png"));
            right2= ImageIO.read(new FileInputStream("res/bunica/BunicaRight3.png"));
            right3= ImageIO.read(new FileInputStream("res/bunica/BunicaRight2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void  update(){
        if(stunDuration<0){
            stunDuration=0;
        }
        stunDuration--;

        if(keyH.upPressed==false || keyH.downPressed==false || keyH.leftPressed==false || keyH.rightPressed==false)
        {
            moving=false;
        }
        //Boost incercare
        if(actionLockCounter>120)
        {
            cooldown=true;
            cooldownCounter++;
        }
        if(cooldownCounter==180)
        {
            cooldown=false;
            actionLockCounter=0;
            cooldownCounter=0;
        }
        if(keyH.zeroPressed==true && cooldown==false)
        {
            this.speed=6;
            actionLockCounter++;
        }
        if(keyH.zeroPressed==false || cooldown==true)
        {
            this.speed=defaultSpeed;
        }
        if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true){
            moving=true;
            if(keyH.upPressed==true)
            {
                direction="up";// facem minus deoarece in java X=0 si Y=0 este coordonata coltului din stanga sus a gamePanel-u
            }
            else if(keyH.downPressed==true)
            {
                direction="down";
            }
            else if(keyH.leftPressed==true)
            {
                direction="left";
            }
            else if (keyH.rightPressed==true)
            {
                direction="right";
            }
            //CHECK TILE COLLISON

            collisionOn=false;
            gp.cChecker.checkTile(this);

            //CHECK OBJ COLLISION

            int objindex=gp.cChecker.checkObject(this,true);
            pickUpObject(objindex);

            //Collision with player
            //CHECK EVENT
            gp.eChecker.checkEvent();
            gp.eChecker.checkGate();
            gp.eChecker.teleport();

            //IF COLLISION FALSE PLAYER CAN MOVE
            if(collisionOn==false && stunDuration<=0){
                switch (direction){
                    case "up":
                        y-=speed;
                        break;
                    case "down":
                        y+=speed;
                        break;
                    case "left":
                        x-=speed;
                        break;
                    case "right":
                        x+=speed;
                        break;
                }
            }
            //IF
            spriteCounter++;
//            if(keyH.upPressed==false || keyH.downPressed==false || keyH.leftPressed==false || keyH.rightPressed==false){
//                spriteNum=3;
//            }
            if(spriteCounter>10){
                if(spriteNum==1){
                    spriteNum=2;
                }
                else if (spriteNum==2) {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }

    }
    public void pickUpObject(int i){
        if(i!=999){
            String objectName=gp.obj[gp.currentMap][i].name;
            switch (objectName){
                case "Bomb":
                    stunDuration=180;
                    gp.obj[gp.currentMap][i]=null;
                    break;
            }
        }
    }
    public void draw(Graphics2D g2){

        /* g2.setColor(Color.white);
        g2.fillRect(x,y,gp.tileSize,gp.tileSize);*/
        BufferedImage image=null;
        switch (direction)
        {
            case "up":
                if(spriteNum==1) {
                    image = up1;
                }
                if(spriteNum==2) {
                    image = up2;
                }
                if(moving==false) {
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum==1) {
                    image = down1;
                }
                if(spriteNum==2) {
                    image = down2;
                }
                if(moving==false) {
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum==1) {
                    image = left1;
                }
                if(spriteNum==2) {
                    image = left2;
                }
                if(moving==false) {
                    image = left3;
                }
                break;
            case "right":
                if(spriteNum==1) {
                    image = right1;
                }
                if(spriteNum==2) {
                    image = right2;
                }
                if(moving==false) {
                    image = right3;
                }
                break;
        }
        //g2.drawImage(image,x,y,gp.tileSize, gp.tileSize,null);
        if(stunDuration>0){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        }
        g2.drawImage(image,x,y,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }
}