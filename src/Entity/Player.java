
package Entity;

import java.io.*;
import LaCirese.GamePanel;
import LaCirese.KeyHandler;
import LaCirese.UtilityTool;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Player extends entity{

    public KeyHandler keyH;
    public int hasPapuc=0;

    public int hasCireasa=0;

    boolean hasBardita=false;

    public int hasBomb=0;

    public int totalCirese;
    public int maxLife;
    public int life;

    public Player(GamePanel gp,KeyHandler keyH){

        super(gp);
        this.keyH=keyH;

        solidArea=new Rectangle();
        solidArea.x=18;
        solidArea.y=31;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=4;
        solidArea.height=8;
        setDefaultValues();
        getPlayerImage();
    }
    Color c = new Color(0.0f, 0.0f, 1.0f, 0.5f);
    public void setDefaultPositions(){
        x=40;
        y=40;
        speed=4;
        direction="down";
    }
    public void restoreLife(){
        life=maxLife;
        invincible=false;
    }
    public void setDefaultValues(){
        x=40;
        y=40*4;
        speed=4;
        direction="down";
        //Player Satus
        maxLife=4;
        life=maxLife;
        invincible=false;
        hasPapuc=0;
        hasCireasa=0;
        hasBardita=false;
        hasBomb=0;
    }
    public void setDefaultValues2(){
        x=40*16;
        y=40*11;
        speed=4;
        direction="down";
        //Player Satus
        maxLife=4;
        life=maxLife;
        invincible=false;
        hasPapuc=0;
        hasCireasa=0;
        hasBardita=false;
        hasBomb=0;
    }
    public void setDefaultValues3(){
        x=40*15;
        y=40*15;
        speed=4;
        direction="down";
        //Player Satus
        maxLife=4;
        life=maxLife;
        invincible=false;
        hasPapuc=0;
        hasCireasa=0;
        hasBardita=false;
        hasBomb=0;
    }
    public void getPlayerImage(){
        up1=setup("BoyUp1");
        up2=setup("BoyUp3");
        up3=setup("BoyUp2");
        down1=setup("BoyDown1");
        down2=setup("BoyDown3");
        down3=setup("BoyDown2");
        left1=setup("BoyLeft1");
        left2=setup("BoyLeft3");
        left3=setup("BoyLeft2");
        right1=setup("BoyRight1");
        right2=setup("BoyRight3");
        right3=setup("BoyRight2");
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool=new UtilityTool();
        BufferedImage Image=null;

        try{
            Image=ImageIO.read(new FileInputStream("res/player/"+imageName+".png"));
            Image=uTool.scaleImage(Image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return Image;
    }
    public void  update(){
        if(keyH.upPressed==false || keyH.downPressed==false || keyH.leftPressed==false || keyH.rightPressed==false)
        {
            moving=false;
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


            //CHECK EVENT
            gp.eChecker.checkEvent();
            gp.eChecker.speedUp();
            gp.eChecker.teleport();
            gp.eChecker.checkGate();

            //IF COLLISION FALSE PLAYER CAN MOVE
            if(collisionOn==false){
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
        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>180){
                invincible=false;
                invincibleCounter=0;
            }
        }
        if(cooldown==true){
            cooldownCounter++;
            if(cooldownCounter>120){
                cooldown=false;
                cooldownCounter=0;
            }
        }
        else if(cooldown==false){
            speed=4;
        }
        if(life<=0){
            gp.gameState=gp.gameOverState;
        }
    }

    public void pickUpObject(int i){
        if(i!=999){
            String objectName=gp.obj[gp.currentMap][i].name;
            switch (objectName){
                case "Papuc":
                    hasPapuc++;
                    gp.obj[gp.currentMap][i]=null;
                    System.out.println("Papuc:"+hasPapuc);
                    break;
                case "Cireasa":
                    hasCireasa++;
                    totalCirese++;
                    gp.obj[gp.currentMap][i]=null;
                    System.out.println("Cirese:"+hasCireasa);
                    try {
                        KeyHandler.CireseCounter(gp.player.totalCirese);
                        System.out.println("Adaugare!!");
                    } catch (SQLException ae) {
                        System.out.println("Exception happened! Abort! Abort!");
                    }
                    break;
                case "Bardita":
                    hasBardita=true;
                    gp.obj[gp.currentMap][i]=null;
                    System.out.println("Picked Bardita");
                    break;
                case "Barrel":
                    if(hasBardita==true){
                        gp.obj[gp.currentMap][i]=null;
                    }
                    break;
                case "Bomb":
                    hasBomb++;
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
        if(invincible==true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        }
        g2.drawImage(image,x,y,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
//        g2.setFont(new Font("Arial",Font.PLAIN,26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible"+invincibleCounter,10,400);
    }
}
