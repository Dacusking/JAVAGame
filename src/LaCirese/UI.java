package LaCirese;

import Entity.entity;
import object.Obj_Heart;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage heart_full,heart_blank;
    Font arial_40,arial_180;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;
    public boolean gameFinished=false;

    double playTime;

    public int commandNum=0;
    public int titleScreenState=0;

    DecimalFormat dFormat=new DecimalFormat("#0.00");

    public UI(GamePanel gp){

        this.gp=gp;
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_180=new Font("Arial",Font.BOLD,80);

        //Create HUD Obj
        entity heart=new Obj_Heart(gp);
        heart_full=heart.image;
        heart_blank=heart.image2;
    }
    public void showMessage(String text){

        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2){

        this.g2=g2;
        g2.setFont(arial_40);
        g2.setColor(Color.red);

        //TITLESTATE
        if(gp.gameState==gp.titleSate){
            drawTitleScreen();
        }
        //PLAYSTATE
        if(gp.gameState==gp.playState){
            drawPlayerLife();
        }
        //PAUSE STATE
        if(gp.gameState==gp.pauseState){
            drawPauseScreen();
        }
        //GAME OVER STATE
        if(gp.gameState==gp.gameOverState) {
            drawgameOverScreen();
        }
        //WIN STATE
        if(gp.gameState==gp.gameWinState)
        {
            drawgameWinScreen();
        }


    }
    public void drawgameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text="Game Over!";
        //Shadow
        g2.setColor(Color.black);
        x=getXforCenterText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(40f));
        text="Retry";
        x=getXforCenterText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        //Back to title
        text="Quit";
        x=getXforCenterText(text);
        y+=55;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-gp.tileSize,y);
        }

    }
    public void drawgameWinScreen(){
        g2.setColor(Color.blue);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100f));

        text="Ai furat ciresele!";
        //Shadow
        g2.setColor(Color.black);
        x=getXforCenterText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);
        //Main
        g2.setColor(Color.yellow);
        g2.drawString(text,x-4,y-4);

        g2.setColor(Color.white);
        //Retry
        g2.setFont(g2.getFont().deriveFont(40f));
        text="Play Again";
        x=getXforCenterText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        //Back to title
        text="Quit";
        x=getXforCenterText(text);
        y+=55;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-gp.tileSize,y);
        }

    }
    public void drawPlayerLife(){
        int x=gp.tileSize/2;
        int y=gp.tileSize/8;
        int i=0;

        //Draw Blank Heart
        while(i<gp.player.maxLife){
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+=40;
        }

        //Reset
        x=gp.tileSize/2;
        y=gp.tileSize/8;
        i=0;

        //Draw Current Life
        while(i<gp.player.life){
            g2.drawImage(heart_full,x,y,null);
            i++;
            x+=40;
        }
    }
    public void drawTitleScreen(){

        if(titleScreenState==0){
            g2.setColor(new Color(50,120,50));
            g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text="LA CIRESE";
            int x=getXforCenterText(text);
            int y=gp.tileSize*3;

            //SHADOW
            g2.setColor(Color.black);
            g2.drawString(text,x+5,y+5);

            //MAIN TEXT
            g2.setColor(Color.red);
            g2.drawString(text,x,y);

            UtilityTool uTool=new UtilityTool();
            BufferedImage image=null;
            try{
                image= ImageIO.read(new FileInputStream("res/objects/Cireasa.png"));
                image=uTool.scaleImage(image,gp.tileSize,gp.tileSize);

            }catch(IOException e){
                e.printStackTrace();
            }

            //CIRESE IMAGE
            x=gp.screenWidth/2-(gp.tileSize*10);
            y=gp.tileSize*6;
            //g2.drawImage(gp.obj[0][2].down1,x,y,gp.tileSize*4,gp.tileSize*4,null);
            g2.drawImage(image,x,y,gp.tileSize*4,gp.tileSize*4,null);
            x=gp.screenWidth/2+(gp.tileSize*6);
            y=gp.tileSize*6;
            //g2.drawImage(gp.obj[0][3].down1,x,y,gp.tileSize*4,gp.tileSize*4,null);
            g2.drawImage(image,x,y,gp.tileSize*4,gp.tileSize*4,null);
            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

            g2.setColor(Color.black);
            text="NEW GAME";
            x=getXforCenterText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);

            if(commandNum==0){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text="LOAD GAME";
            x=getXforCenterText(text);
            y+=gp.tileSize+8;
            g2.drawString(text,x,y);
            if(commandNum==1){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text="SETTINGS";
            x=getXforCenterText(text);
            y+=gp.tileSize+8;
            g2.drawString(text,x,y);
            if(commandNum==2){
                g2.drawString(">",x-gp.tileSize,y);
            }

            text="EXIT";
            x=getXforCenterText(text);
            y+=gp.tileSize+8;
            g2.drawString(text,x,y);
            if(commandNum==3){
                g2.drawString(">",x-gp.tileSize,y);
            }
        }
        else if(titleScreenState==1){
            //GRAPHICS SELECT
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42f));

            String text="Select game appearance!";
            int x=getXforCenterText(text);
            int y=gp.tileSize*3;
            g2.drawString(text,x,y);

            text="Original";
            x=getXforCenterText(text);
            y+=gp.tileSize*3;
            g2.drawString(text,x,y);
            if(commandNum==0){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text="Alternativ";
            x=getXforCenterText(text);
            y+=gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum==1){
                g2.drawString(">",x-gp.tileSize,y);
            }
            text="Back";
            x=getXforCenterText(text);
            y+=gp.tileSize*3;
            g2.drawString(text,x,y);
            if(commandNum==2){
                g2.drawString(">",x-gp.tileSize,y);
            }
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text="PAUSE";
        int x=getXforCenterText(text);
        int y=gp.screenHeight/2;
        g2.drawString(text,x,y);

    }

    public int getXforCenterText(String text){
        int x;
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        x=gp.screenWidth/2-length/2;
        return x;
    }
}
