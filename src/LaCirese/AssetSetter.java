package LaCirese;

import Entity.NPC_Cat;
import Entity.NPC_Dog;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){

        int mapNum=0;
        gp.obj[mapNum][0]=new Obj_Papuc(gp);
        gp.obj[mapNum][0].x=2* gp.tileSize;
        gp.obj[mapNum][0].y=7* gp.tileSize;

        gp.obj[mapNum][1]=new Obj_Papuc(gp);
        gp.obj[mapNum][1].x=13* gp.tileSize;
        gp.obj[mapNum][1].y=14*gp.tileSize;

        gp.obj[mapNum][6]=new Obj_Papuc(gp);
        gp.obj[mapNum][6].x=15* gp.tileSize;
        gp.obj[mapNum][6].y=4*gp.tileSize;

        gp.obj[mapNum][2]=new Obj_Cireasa(gp);
        gp.obj[mapNum][2].x=5* gp.tileSize;
        gp.obj[mapNum][2].y=16* gp.tileSize;

        gp.obj[mapNum][3]=new Obj_Cireasa(gp);
        gp.obj[mapNum][3].x=18* gp.tileSize;
        gp.obj[mapNum][3].y=4* gp.tileSize;

//        gp.obj[mapNum][4]=new Obj_Cireasa(gp);
//        gp.obj[mapNum][4].x=3* gp.tileSize;
//        gp.obj[mapNum][4].y=4* gp.tileSize;

        gp.obj[mapNum][5]=new Obj_Cireasa(gp);
        gp.obj[mapNum][5].x=19* gp.tileSize;
        gp.obj[mapNum][5].y=15* gp.tileSize;


        //PENTRU A SETA OBJ PE ALTA HARTA
        mapNum=1;
        gp.obj[mapNum][0]=new Obj_Cireasa(gp);
        gp.obj[mapNum][0].x=2* gp.tileSize;
        gp.obj[mapNum][0].y=2* gp.tileSize;

        gp.obj[mapNum][1]=new Obj_Cireasa(gp);
        gp.obj[mapNum][1].x=18* gp.tileSize;
        gp.obj[mapNum][1].y=2*gp.tileSize;

        gp.obj[mapNum][2]=new Obj_Cireasa(gp);
        gp.obj[mapNum][2].x=5* gp.tileSize;
        gp.obj[mapNum][2].y=18* gp.tileSize;

        gp.obj[mapNum][3]=new Obj_Papuc(gp);
        gp.obj[mapNum][3].x=16* gp.tileSize;
        gp.obj[mapNum][3].y=13* gp.tileSize;

        gp.obj[mapNum][4]=new Obj_Papuc(gp);
        gp.obj[mapNum][4].x=4* gp.tileSize;
        gp.obj[mapNum][4].y=13* gp.tileSize;

        gp.obj[mapNum][5]=new Obj_Papuc(gp);
        gp.obj[mapNum][5].x=10* gp.tileSize;
        gp.obj[mapNum][5].y=5* gp.tileSize;

        gp.obj[mapNum][6]=new Obj_Bardita(gp);
        gp.obj[mapNum][6].x=10* gp.tileSize;
        gp.obj[mapNum][6].y=11* gp.tileSize;

        gp.obj[mapNum][7]=new Obj_Barrel(gp);
        gp.obj[mapNum][7].x=10* gp.tileSize;
        gp.obj[mapNum][7].y=2* gp.tileSize;

        gp.obj[mapNum][8]=new Obj_Barrel(gp);
        gp.obj[mapNum][8].x=10* gp.tileSize;
        gp.obj[mapNum][8].y=12* gp.tileSize;

        gp.obj[mapNum][9]=new Obj_Barrel(gp);
        gp.obj[mapNum][9].x=12* gp.tileSize;
        gp.obj[mapNum][9].y=11* gp.tileSize;

        gp.obj[mapNum][10]=new Obj_Barrel(gp);
        gp.obj[mapNum][10].x=8* gp.tileSize;
        gp.obj[mapNum][10].y=11* gp.tileSize;

        gp.obj[mapNum][11]=new Obj_Barrel(gp);
        gp.obj[mapNum][11].x=6* gp.tileSize;
        gp.obj[mapNum][11].y=18* gp.tileSize;

        gp.obj[mapNum][12]=new Obj_Barrel(gp);
        gp.obj[mapNum][12].x=14* gp.tileSize;
        gp.obj[mapNum][12].y=18* gp.tileSize;

        mapNum=2;

        gp.obj[mapNum][0]=new Obj_Cireasa(gp);
        gp.obj[mapNum][0].x=2* gp.tileSize;
        gp.obj[mapNum][0].y=4* gp.tileSize;

        gp.obj[mapNum][1]=new Obj_Cireasa(gp);
        gp.obj[mapNum][1].x=18* gp.tileSize;
        gp.obj[mapNum][1].y=4*gp.tileSize;

        gp.obj[mapNum][2]=new Obj_Cireasa(gp);
        gp.obj[mapNum][2].x=2* gp.tileSize;
        gp.obj[mapNum][2].y=16* gp.tileSize;

        gp.obj[mapNum][3]=new Obj_Papuc(gp);
        gp.obj[mapNum][3].x=3* gp.tileSize;
        gp.obj[mapNum][3].y=3* gp.tileSize;

        gp.obj[mapNum][4]=new Obj_Papuc(gp);
        gp.obj[mapNum][4].x=17* gp.tileSize;
        gp.obj[mapNum][4].y=3* gp.tileSize;

        gp.obj[mapNum][5]=new Obj_Papuc(gp);
        gp.obj[mapNum][5].x=3* gp.tileSize;
        gp.obj[mapNum][5].y=17* gp.tileSize;

        gp.obj[mapNum][6]=new Obj_Papuc(gp);
        gp.obj[mapNum][6].x=17* gp.tileSize;
        gp.obj[mapNum][6].y=17* gp.tileSize;

        gp.obj[mapNum][7]=new Obj_Bomb(gp);
        gp.obj[mapNum][7].x=3* gp.tileSize;
        gp.obj[mapNum][7].y=11* gp.tileSize;

        gp.obj[mapNum][8]=new Obj_Bomb(gp);
        gp.obj[mapNum][8].x=17* gp.tileSize;
        gp.obj[mapNum][8].y=9* gp.tileSize;






    }
    public void setNPC(){
        int mapNum;
//        mapNum=0;
//
//        gp.npc[mapNum][1]=new NPC_Dog(gp);
//        gp.npc[mapNum][1].x=4*gp.tileSize;
//        gp.npc[mapNum][1].y=4*gp.tileSize;
//        gp.npc[mapNum][1].direction="up";

        mapNum=1;
        gp.npc[mapNum][0]=new NPC_Dog(gp);
        gp.npc[mapNum][0].x=15*gp.tileSize;
        gp.npc[mapNum][0].y=3*gp.tileSize;

        gp.npc[mapNum][1]=new NPC_Dog(gp);
        gp.npc[mapNum][1].x=5*gp.tileSize;
        gp.npc[mapNum][1].y=11*gp.tileSize;
        gp.npc[mapNum][1].direction="up";

        mapNum=2;

        gp.npc[mapNum][0]=new NPC_Dog(gp);
        gp.npc[mapNum][0].x=1*gp.tileSize;
        gp.npc[mapNum][0].y=11*gp.tileSize;

        gp.npc[mapNum][1]=new NPC_Dog(gp);
        gp.npc[mapNum][1].x=1*gp.tileSize;
        gp.npc[mapNum][1].y=9*gp.tileSize;
        gp.npc[mapNum][1].direction="up";

        gp.npc[mapNum][2]=new NPC_Dog(gp);
        gp.npc[mapNum][2].x=19*gp.tileSize;
        gp.npc[mapNum][2].y=11*gp.tileSize;

        gp.npc[mapNum][3]=new NPC_Dog(gp);
        gp.npc[mapNum][3].x=19*gp.tileSize;
        gp.npc[mapNum][3].y=9*gp.tileSize;
        gp.npc[mapNum][3].direction="up";

        gp.npc[mapNum][4]=new NPC_Cat(gp);
        gp.npc[mapNum][4].x=3*gp.tileSize;
        gp.npc[mapNum][4].y=7*gp.tileSize-5;

        gp.npc[mapNum][5]=new NPC_Cat(gp);
        gp.npc[mapNum][5].x=17*gp.tileSize;
        gp.npc[mapNum][5].y=13*gp.tileSize-5;
        gp.npc[mapNum][5].direction="left";

    }
}
