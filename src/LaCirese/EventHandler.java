package LaCirese;


import java.awt.*;
import java.sql.SQLException;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];
    public EventHandler(GamePanel gp){
        this.gp=gp;

        eventRect=new EventRect[gp.maxMap][gp.maxScreenCol][gp.maxScreenRow];

        int map=0;
        int col=0;
        int row=0;
        while(map<gp.maxMap && col<gp.maxScreenCol && row<gp.maxScreenRow){
            eventRect[map][col][row]=new EventRect();
            eventRect[map][col][row].x=0;
            eventRect[map][col][row].y=10;
            eventRect[map][col][row].width=40;
            eventRect[map][col][row].height=30;
            eventRect[map][col][row].eventRectDefaultX=eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY=eventRect[map][col][row].y;

            col++;
            if(col==gp.maxScreenCol){
                col=0;
                row++;
                if(row==gp.maxScreenRow){
                    row=0;
                    map++;
                }
            }
        }


    }

    public void checkEvent(){
        if(hit()==true){
            if(gp.player.invincible==false) {
                damageBunica();
                gp.player.invincible=true;
            }
        }
    }
    public void checkGate(){
            if (gate1(1,1,11,"left")){
                gp.player.x=19*gp.tileSize;
                gp.player.y=11*gp.tileSize;

            }
            if (gate1(1,19,11,"right")){
                gp.player.x=1*gp.tileSize;
                gp.player.y=11*gp.tileSize;

            }
        if (gate1(2,8,0,"up")){
            gp.player.x=12*gp.tileSize;
            gp.player.y=20*gp.tileSize;

        }
        if (gate1(2,12,0,"up")){
            gp.player.x=8*gp.tileSize;
            gp.player.y=20*gp.tileSize;

        }
        if (gate1(2,8,20,"down")){
            gp.player.x=12*gp.tileSize;
            gp.player.y=0*gp.tileSize;

        }
        if (gate1(2,12,20,"down")){
            gp.player.x=8*gp.tileSize;
            gp.player.y=0*gp.tileSize;

        }
            if (gate2(1,1,11,"left")){
                gp.bunica.x=19*gp.tileSize;
                gp.bunica.y=11*gp.tileSize;

            }
            if (gate2(1,19,11,"right")){
                gp.bunica.x=1*gp.tileSize;
                gp.bunica.y=11*gp.tileSize;

            }
        if (gate2(2,8,0,"up")){
            gp.bunica.x=12*gp.tileSize;
            gp.bunica.y=20*gp.tileSize;

        }
        if (gate2(2,12,0,"up")){
            gp.bunica.x=8*gp.tileSize;
            gp.bunica.y=20*gp.tileSize;

        }
        if (gate2(2,8,20,"down")){
            gp.bunica.x=12*gp.tileSize;
            gp.bunica.y=0*gp.tileSize;

        }
        if (gate2(2,12,20,"down")){
            gp.bunica.x=8*gp.tileSize;
            gp.bunica.y=0*gp.tileSize;

        }

    }
    public void teleport(){
        if(gp.player.hasCireasa==3){
         //   gp.npc[gp.currentMap][1]=null;
            gp.currentMap++;
            if(gp.currentMap==1)
            {
                gp.player.setDefaultValues2();
                gp.bunica.setDefaultValues2();
            }
            if(gp.currentMap==2)
            {
                gp.player.setDefaultValues3();
                gp.bunica.setDefaultValues3();
            }
            if(gp.currentMap==3){
//                gp.ui.titleScreenState=0;
//                gp.gameState=gp.titleSate;
//                gp.restart(); //Titlu
                try {
                    KeyHandler.CireseCounter(gp.player.totalCirese);
                    System.out.println("Adaugare!!");
                } catch (SQLException ae) {
                    System.out.println("Exception happened! Abort! Abort!");
                }
                gp.gameState=gp.gameWinState;
            }

//                map= gp.currentMap;
        }
    }
    public boolean hit(){
        boolean hit=false;

        gp.player.solidArea.x=gp.player.x+gp.player.solidArea.x;
        gp.player.solidArea.y=gp.player.y+gp.player.solidArea.y;

        gp.bunica.solidArea.x=gp.bunica.x+gp.bunica.solidArea.x;
        gp.bunica.solidArea.y=gp.bunica.y+gp.bunica.solidArea.y;

        //HIT CATEI
        for(int i=0;i<gp.npc[1].length;i++) {
            if (gp.npc[gp.currentMap][i] != null) {
                gp.npc[gp.currentMap][i].solidArea.x = gp.npc[gp.currentMap][i].x + gp.npc[gp.currentMap][i].solidArea.x;
                gp.npc[gp.currentMap][i].solidArea.y = gp.npc[gp.currentMap][i].y + gp.npc[gp.currentMap][i].solidArea.y;
                if (gp.player.solidArea.intersects(gp.npc[gp.currentMap][i].solidArea)) {
                    hit = true;
                }

            }
        }
        //HIT BUNICA
        if(gp.player.solidArea.intersects(gp.bunica.solidArea) )
        {
            hit=true;
        }

        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
        gp.player.solidArea.y=gp.player.solidAreaDefaultY;
        gp.bunica.solidArea.x=gp.bunica.solidAreaDefaultX;
        gp.bunica.solidArea.y=gp.bunica.solidAreaDefaultY;

        for(int i=0;i<gp.npc[1].length;i++) {
            if (gp.npc[gp.currentMap][i] != null) {
                gp.npc[gp.currentMap][i].solidArea.x = gp.npc[gp.currentMap][i].solidAreaDefaultX;
                gp.npc[gp.currentMap][i].solidArea.y = gp.npc[gp.currentMap][i].solidAreaDefaultY;

            }
        }

        return hit;
    }
    public void damageBunica(){

        gp.player.life-=1;
        System.out.println("Lives:"+gp.player.life);
    }
    public boolean gate1(int map,int col,int row, String reqDirection){
        boolean gate1=false;
        if(map==gp.currentMap){
            gp.player.solidArea.x=gp.player.x+gp.player.solidArea.x;
            gp.player.solidArea.y=gp.player.y+gp.player.solidArea.y;
            eventRect[map][col][row].x=col*gp.tileSize+eventRect[map][col][row].x;
            eventRect[map][col][row].y=row*gp.tileSize+eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row])){
                if(gp.player.direction.contentEquals(reqDirection)|| reqDirection.contentEquals("any")) {
                    gate1 = true;
                }
            }

            gp.player.solidArea.x=gp.player.solidAreaDefaultX;
            gp.player.solidArea.y=gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x=eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y=eventRect[map][col][row].eventRectDefaultY;
        }
        return gate1;
    }
    public boolean gate2(int map,int col,int row, String reqDirection){
        boolean gate1=false;
        if(map==gp.currentMap){
            gp.bunica.solidArea.x=gp.bunica.x+gp.bunica.solidArea.x;
            gp.bunica.solidArea.y=gp.bunica.y+gp.bunica.solidArea.y;
            eventRect[map][col][row].x=col*gp.tileSize+eventRect[map][col][row].x;
            eventRect[map][col][row].y=row*gp.tileSize+eventRect[map][col][row].y;

            if(gp.bunica.solidArea.intersects(eventRect[map][col][row])){
                if(gp.bunica.direction.contentEquals(reqDirection)|| reqDirection.contentEquals("any")) {
                    gate1 = true;
                }
            }

            gp.bunica.solidArea.x=gp.bunica.solidAreaDefaultX;
            gp.bunica.solidArea.y=gp.bunica.solidAreaDefaultY;
            eventRect[map][col][row].x=eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y=eventRect[map][col][row].eventRectDefaultY;
        }
        return gate1;
    }
    public void speedUp(){
        if (gp.player.hasPapuc != 0 && gp.player.cooldown==false) {
            if (gp.player.keyH.spacePressed == true){
                    gp.player.speed = 7;
                    gp.player.cooldown=true;
                    gp.player.hasPapuc--;
            }
        }
    }

}
