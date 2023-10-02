package LaCirese;

import object.Obj_Bomb;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    int i=20;
    public boolean upPressed,  downPressed,leftPressed, rightPressed,spacePressed;
    boolean checkDrawTime=false;

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();

        //TITLESTATE
        if(gp.gameState==gp.titleSate){

            if(gp.ui.titleScreenState==0){
                if(code==KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum<0){
                        gp.ui.commandNum=3;
                    }
                }
                if(code==KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum>3){
                        gp.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum==0){
                        gp.ui.titleScreenState=1;
                        try {
                            CireseGetter(gp);
                        } catch (SQLException ae) {
                            System.out.println("Exception happened! Abort! Abort!");
                        }
                    }
                    if(gp.ui.commandNum==1){
                        //add later
                    }
                    if(gp.ui.commandNum==2){
                        //add later
                    }
                    if(gp.ui.commandNum==3){
                        System.exit(0);
                    }
                }
            }
            else if(gp.ui.titleScreenState==1){
                if(code==KeyEvent.VK_W)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum<0){
                        gp.ui.commandNum=2;
                    }
                }
                if(code==KeyEvent.VK_S)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum>2){
                        gp.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum==0){
                        System.out.println("You selected original look!");
                        gp.alternativ=0;
                        gp.tileM.getTileImage();
                        gp.gameState=gp.playState;
                    }
                    if(gp.ui.commandNum==1){
                        System.out.println("You selected alternativ look!");
                        gp.alternativ=1;
                        gp.tileM.getTileImage();
                        gp.gameState=gp.playState;
                    }
                    if(gp.ui.commandNum==2){
                        gp.ui.titleScreenState=0;
                    }

                }
            }

        }

        //PLAYSTATE
        if(gp.gameState==gp.playState){
            if(code==KeyEvent.VK_W)
            {
                upPressed=true;
            }
            if(code==KeyEvent.VK_S)
            {
                downPressed=true;
            }
            if(code==KeyEvent.VK_A)
            {
                leftPressed=true;
            }
            if(code==KeyEvent.VK_D)
            {
                rightPressed=true;
            }
            if(code==KeyEvent.VK_SPACE)
            {
                spacePressed=true;
            }
            if(code==KeyEvent.VK_P)
            {
                    System.out.println("Pauza!!");
//                    try {
//                        CireseCounter(gp.player.totalCirese);
//                        System.out.println("Adaugare!!");
//                    } catch (SQLException ae) {
//                        System.out.println("Exception happened! Abort! Abort!");
//                    }
                gp.gameState=gp.pauseState;
            }
            //BOMB SETTING
            if(code==KeyEvent.VK_B){
                if(gp.player.hasBomb!=0){
                    switch (gp.player.direction){
                        case "up":
                            gp.obj[gp.currentMap][i]=new Obj_Bomb(gp);
                            gp.obj[gp.currentMap][i].x=gp.player.x;
                            gp.obj[gp.currentMap][i].y=gp.player.y+40;
                            i++;
                            gp.player.hasBomb--;

                            break;
                        case "down":
                            gp.obj[gp.currentMap][i]=new Obj_Bomb(gp);
                            gp.obj[gp.currentMap][i].x=gp.player.x;
                            gp.obj[gp.currentMap][i].y=gp.player.y-10;
                            i++;
                            gp.player.hasBomb--;

                            break;
                        case "left":
                            gp.obj[gp.currentMap][i]=new Obj_Bomb(gp);
                            gp.obj[gp.currentMap][i].x=gp.player.x+20;
                            gp.obj[gp.currentMap][i].y=gp.player.y;
                            i++;
                            gp.player.hasBomb--;

                            break;
                        case "right":
                            gp.obj[gp.currentMap][i]=new Obj_Bomb(gp);
                            gp.obj[gp.currentMap][i].x=gp.player.x-20;
                            gp.obj[gp.currentMap][i].y=gp.player.y;
                            i++;
                            gp.player.hasBomb--;

                            break;
                    }
                }
            }
            //DEBUG
//            if(code==KeyEvent.VK_T)
//            {
//                if(checkDrawTime==false){
//                    checkDrawTime=true;
//                }
//                else if(checkDrawTime==true){
//                    checkDrawTime=false;
//                }
//            }

        }
        //PAUSESTATE
        else if(gp.gameState==gp.pauseState){
            if(code==KeyEvent.VK_P)
            {
                gp.gameState=gp.playState;
            }
        }
        //GAME OVER STATE
        else if(gp.gameState==gp.gameOverState){
            if(code==KeyEvent.VK_W)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=1;
                }
            }
            if(code==KeyEvent.VK_S)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum>1){
                    gp.ui.commandNum=0;
                }
            }
            if(code==KeyEvent.VK_ENTER)
            {
                if(gp.ui.commandNum==0){
                    gp.gameState= gp.playState;
                    gp.retry();
                }
                else if (gp.ui.commandNum==1){
                    gp.ui.titleScreenState=0;
                    gp.gameState=gp.titleSate;
                    gp.restart();
                }

            }
        }
        //WIN STATE
        else if(gp.gameState==gp.gameWinState){
            if(code==KeyEvent.VK_W)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=1;
                }
            }
            if(code==KeyEvent.VK_S)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum>1){
                    gp.ui.commandNum=0;
                }
            }
            if(code==KeyEvent.VK_ENTER)
            {
                if(gp.ui.commandNum==0){
                    gp.gameState= gp.playState;
                    gp.retry();
                }
                else if (gp.ui.commandNum==1){
                    gp.ui.titleScreenState=0;
                    gp.gameState=gp.titleSate;
                    gp.restart();
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_W)
        {
            upPressed=false;
        }
        if(code==KeyEvent.VK_S)
        {
            downPressed=false;
        }
        if(code==KeyEvent.VK_A)
        {
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D)
        {
            rightPressed=false;
        }
        if(code==KeyEvent.VK_SPACE)
        {
            spacePressed=false;
        }
    }
    public static void CireseCounter(int s) throws SQLException {
        Connection c=null;
        Statement stmt=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:LaCirese.db");
            stmt=c.createStatement();

            String SQL1 = "CREATE TABLE IF NOT EXISTS La_Cirese " +
                    "(Cirese)";
            stmt.executeUpdate(SQL1);
            c.setAutoCommit(false);
            //Take from table
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM La_Cirese;" );
//            gp.player.totalCirese=rs.getInt("Cirese");
//            System.out.println( "Current total = " + gp.player.totalCirese );
//            rs.close();
            //update table
            //SQL1="INSERT INTO La_Cirese (Cirese)"+"VALUES ("+s+");";
            SQL1="UPDATE La_Cirese set Cirese=" + s +" WHERE rowid=" +(1) +";";



            stmt.executeUpdate(SQL1);

            stmt.close();
            c.commit();
            c.close();


        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    public static void CireseGetter(GamePanel gp) throws SQLException {
        Connection c=null;
        Statement stmt=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:LaCirese.db");
            stmt=c.createStatement();
            c.setAutoCommit(false);
            //Take from table
            ResultSet rs = stmt.executeQuery( "SELECT * FROM La_Cirese;" );
            gp.player.totalCirese=rs.getInt("Cirese");
            System.out.println( "Current total = " + gp.player.totalCirese );
            rs.close();

            stmt.close();
            c.commit();
            c.close();


        }catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
