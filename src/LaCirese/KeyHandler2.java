package LaCirese;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler2 implements KeyListener {

    public boolean upPressed,  downPressed, leftPressed,rightPressed, zeroPressed;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP)
        {
            upPressed=true;
        }
        if(code==KeyEvent.VK_DOWN)
        {
            downPressed=true;
        }
        if(code==KeyEvent.VK_LEFT)
        {
            leftPressed=true;
        }
        if(code==KeyEvent.VK_RIGHT)
        {
            rightPressed=true;
        }
        if(code==KeyEvent.VK_NUMPAD0 || code==KeyEvent.VK_INSERT)
        {
            zeroPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP)
        {
            upPressed=false;
        }
        if(code==KeyEvent.VK_DOWN)
        {
            downPressed=false;
        }
        if(code==KeyEvent.VK_LEFT)
        {
            leftPressed=false;
        }
        if(code==KeyEvent.VK_RIGHT)
        {
            rightPressed=false;
        }
        if(code==KeyEvent.VK_NUMPAD0 || code==KeyEvent.VK_INSERT)
        {
            zeroPressed=false;
        }
    }
}