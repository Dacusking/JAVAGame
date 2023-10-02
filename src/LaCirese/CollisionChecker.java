package LaCirese;

import Entity.entity;
public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }

    public void checkTile(entity entity){
        int entityLeftWorldX=entity.x+entity.solidArea.x;
        int entityRightWorldX=entity.x+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY=entity.y+entity.solidArea.y;
        int entityBottomWorldY=entity.y+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.tileSize;
        int entityRightCol=entityRightWorldX/gp.tileSize;
        int entityTopRow=entityTopWorldY/gp.tileSize;
        int entityBottomRow=entityBottomWorldY/gp.tileSize;

        int tileNum1,tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collison==true||gp.tileM.tile[tileNum2].collison==true){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collison==true||gp.tileM.tile[tileNum2].collison==true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collison==true||gp.tileM.tile[tileNum2].collison==true){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collison==true||gp.tileM.tile[tileNum2].collison==true){
                    entity.collisionOn=true;
                }
                break;

        }
    }
    public int checkObject(entity entity,boolean player){

        int index=999;

        for(int i=0;i<gp.obj[1].length;i++)
        {
            if(gp.obj[gp.currentMap][i]!=null)
            {

                //get entity solid area position
                entity.solidArea.x=entity.x+entity.solidArea.x;
                entity.solidArea.y=entity.y+entity.solidArea.y;

                //get the obj solid area position
                gp.obj[gp.currentMap][i].solidArea.x=gp.obj[gp.currentMap][i].x+gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y=gp.obj[gp.currentMap][i].y+gp.obj[gp.currentMap][i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x=gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y=gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }

        }

        return index;
    }
}
