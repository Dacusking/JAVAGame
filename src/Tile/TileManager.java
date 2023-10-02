package Tile;

import LaCirese.GamePanel;
import LaCirese.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    public TileManager(GamePanel gp){


        this.gp=gp;
        tile=new Tile[20];

        mapTileNum=new int[gp.maxMap][gp.maxScreenCol][gp.maxScreenRow];



        getTileImage();
        loadMap("/maps/map1.txt",0);
        loadMap("/maps/map2.txt",1);
        loadMap("/maps/map3.txt",2);

    }
    public void getTileImage(){
        if(gp.alternativ==0){
            setup(0,"GrassTile",false);
            setup(1,"StoneTile3",true);
            setup(2,"BarrelTile2",true);
            setup(3,"StonePath",false);
            setup(4,"PanzaTile",true);
            setup(5,"Fan",true);
            setup(6,"PoteacaTile",false);
            setup(7,"PondTile",true);
        }
        if(gp.alternativ==1){
            setup(0,"GrassTileAlt",false);
            setup(1,"StoneTile3Alt",true);
            setup(2,"BarrelTile2Alt",true);
            setup(3,"floor",false);
            setup(4,"PanzaTileAlt",true);
            setup(5,"FanAlt",true);
            setup(6,"PoteacaTileAlt",false);
            setup(7,"PondTileAlt",true);
        }

    }
    public void setup(int index,String imageName,boolean collision){
        UtilityTool uTool=new UtilityTool();
        try{
            tile[index]=new Tile();
            tile[index].image=ImageIO.read(new FileInputStream("res/Tiles/"+imageName+".png"));
            tile[index].image=uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].collison=collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath,int map){
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;
            while(col<gp.maxScreenCol && row<gp.maxScreenRow){
                String line=br.readLine();//citeste cate o linie de numere si o pune in line
                while(col<gp.maxScreenCol){

                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row]=num;
                    col++;
                }
                if(col==gp.maxScreenCol){
                    col=0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){

        //g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);
        //g2.drawImage(tile[1].image,40,0,gp.tileSize,gp.tileSize,null);
        int col=0;
        int row=0;
        int x=0;
        int y=0;

        while(col<gp.maxScreenCol && row< gp.maxScreenRow){

            int tileNum=mapTileNum[gp.currentMap][col][row];

            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x+=gp.tileSize;

            if(col==gp.maxScreenCol){
                col=0;
                x=0;
                row++;
                y+=gp.tileSize;
            }
        }
    }
}
