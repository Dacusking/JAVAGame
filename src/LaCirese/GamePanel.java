package LaCirese;
import Entity.Bunica;
import Entity.Player;
import Entity.entity;
import Tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 40; //40x40
    final int scale =1;

    public final int tileSize = originalTileSize *scale; //48x48
    public final int maxScreenCol = 21;
    public final int maxScreenRow=21;

    public final int maxMap=4;
    public int currentMap=0;
    public final int screenWidth= tileSize*maxScreenCol; //1008 pix
    public final int screenHeight=tileSize*maxScreenRow;//1008 pix
    //FULLSCREEN
    int screenWidth2=screenWidth;
    int screenHeight2=screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    //FPS
    int FPS=60;

    TileManager tileM=new TileManager(this);

    KeyHandler keyH=new KeyHandler(this);
    KeyHandler2 keyH2=new KeyHandler2();
    Thread gameThread;

    public CollisionChecker cChecker=new CollisionChecker(this);

    public int alternativ;

    public EventHandler eChecker=new EventHandler(this);
    public AssetSetter aSetter=new AssetSetter(this);
    public UI ui=new UI(this);
    public Player player=new Player(this,keyH);
    public Bunica bunica=new Bunica(this,keyH2);
    public entity obj[][]=new entity[maxMap][100];

    public entity npc[][]=new entity[maxMap][10];
    ArrayList<entity> entityList=new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleSate=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int gameOverState=6;
    public final int gameWinState=7;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addKeyListener(keyH2);
        this.setFocusable(true);

    }
    public void retry(){
        player.invincible=false;
        currentMap=0;
        player.setDefaultValues();
        bunica.setDefaultValues();
        aSetter.setObject();
        aSetter.setNPC();
    }
    public void restart(){
        player.invincible=false;
        currentMap=0;
        player.setDefaultValues();
        bunica.setDefaultValues();
        aSetter.setObject();
        aSetter.setNPC();
    }
    public void setupGame(){//dam call la metoda asta inainte sa inceapa jocu pentru a plasa objecturile

        aSetter.setObject();
        aSetter.setNPC();
        gameState=titleSate;


    }
    public void startGameThread(){

        gameThread=new Thread(this /*this este clasa GamePanel*/);
        gameThread.start(); //da call la run automatic
    }


    @Override
    public void run(){

        double drawInterval=1000000000/FPS;
        double nextDrawTime=System.nanoTime()+drawInterval;


        while(gameThread != null)
        {
            //-----------long currentTime =System.nanoTime();
            //-----------System.out.println(currentTime);

            //update locatia caracterului
            update();

            // si deseneaza screen ul
            repaint();// asa se apeleaza paintComponent

            try {
                double remainingTime =nextDrawTime-System.nanoTime();
                remainingTime=remainingTime/1000000;//facem asta deoarece sleep se foloseste de mili secunde iar noi folosim nano

                if(remainingTime<0)  //in cazul in care update si repaind sunt mai lungi decat drawInterval
                {
                    remainingTime=0;
                }

                Thread.sleep((long)remainingTime);//nu face nimic in intervalul asta de timp

                nextDrawTime+=drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){

        if(gameState==playState){

            //PLAYERS
            player.update();
            bunica.update();
            //NPC
            for(int i=0;i<npc[1].length;i++){
                if(npc[currentMap][i]!=null){
                    npc[currentMap][i].update();
                }
            }
        }
        if(gameState==pauseState){
            //chestii
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g; //ofera mai multe propietati lui g

        //DEBUG
        long drawStart=0;
        if(keyH.checkDrawTime==true){
            drawStart=System.nanoTime();
        }

        //TITLE SCREEN
        if(gameState==titleSate){
            ui.draw(g2);
        }
        //others.
        else {
            //TILE
            tileM.draw(g2);//mereu trb desenate tile uri inainte de player

            //ADD entities

            entityList.add(player);
            entityList.add(bunica);

            for(int i=0;i< npc[1].length;i++){
                if(npc[currentMap][i]!=null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i=0;i< obj[1].length;i++){
                if(obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            //Draw entities
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY LIST
            entityList.clear();

            //UI
            ui.draw(g2);

        }
        //DEBUG
//        if(keyH.checkDrawTime==true) {
//            long drawEnd = System.nanoTime();
//            long passed = drawEnd - drawStart;
//            g2.setColor(Color.white);
//            g2.drawString("Draw time: " + passed, 10, 400);
//            System.out.println("Draw Time: " + passed);
//
//        }

        g2.dispose();
    }

}
