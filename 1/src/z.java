
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class z extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 400, 520);  //填充指定的形状
        for(int i = 0; i < area.length; i++) {
            for(int j = 0; j < area[i].length; j++) {  //初始化面板
                if(area[i][j] == 0) {  //没有被下落图形覆盖
                    g.setColor(Color.lightGray);
                    
                } else {
                    g.setColor(Color.pink);
                }
                g.fill3DRect(i*size, (area[i].length-1-j)*size, size, size, true);
            }
        }
        for(int ii = 0; ii < moveObj.length; ii++) {
            int i = moveObj[ii]/20;
            int j = moveObj[ii]%20;
            g.setColor(Color.pink);           
            g.fill3DRect(i*size, (area[i].length-1-j)*size, size, size, true);
        }
        g.setColor(Color.pink);
        g.setFont(new Font("gbk", Font.BOLD|Font.ITALIC, 24));
        g.drawString("得分:"+score, 300, 40);
    }
    long moveIndex = 0;
    int size = 24;
    int[][] area = new int[12][20];
    int[] moveObj = new int[4];
    int score;
    public int Jframe()  
    {
    	try {
    		JFrame jframe = new JFrame("Test");  //界面制作
            jframe.add(this);
            jframe.setSize(400, 520);
            jframe.setLocation(200, 40);
            jframe.setVisible(true);
            jframe.setResizable(false);
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            moveObj = new int[] {20-2, 20-1, 20*2-2, 20*2-1};
            jframe.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_LEFT) {  //点击向左的方向键
                        if(moveObj[0] > 20) {
                            for(int i = 0; i < moveObj.length; i++) {
                                moveObj[i] = moveObj[i] - 20;
                            }
                        }
                    }
                    if(e.getKeyCode() == KeyEvent.VK_RIGHT) {  //点击向右的方向键
                        if(moveObj[3] < (12-1)*20) {
                            for(int i = 0; i < moveObj.length; i++) {
                                moveObj[i] = moveObj[i] + 20;
                            }
                        }
                    }
                }
            });
            return 1;
    	}catch(Exception e)
    	{
    		return 0;
    	}
    	 
    }
    public int Time() { 
    	try {
    		 new Timer().schedule(new TimerTask() {  //TimerTask方法可以反复执行多次
                 
                 @Override
                 public void run() {
                     if(moveIndex % 4 == 0) { 
                         int[] tmpMoveObj = new int[4];
                         if(moveObj[0] % 20 > 0) {
                             for(int i = 0; i < moveObj.length; i++) {
                                 tmpMoveObj[i] = moveObj[i] - 1;
                                 if(area[tmpMoveObj[i]/20][tmpMoveObj[i]%20] == 1) {
                                     tmpMoveObj = null;
                                     break;
                                 }
                             }
                             moveObj = tmpMoveObj == null ? moveObj : tmpMoveObj;
                         } else {
                             tmpMoveObj = null;
                         }
                         if(tmpMoveObj == null) {
                             for(int i = 0; i < moveObj.length; i++) {
                                 area[moveObj[i]/20][moveObj[i]%20] = 1;
                             }
                             for(int j = 20 - 1; j >= 0; j--) {
                                 int k = 0;
                                 for(int i = 0; i < area.length; i++)k+=area[i][j];
                                 if(k == area.length) {
                                     for(int jj = j; jj < 20-1; jj++) {
                                         for(int i = 0; i < area.length; i++)area[i][jj]=area[i][jj+1];
                                     }
                                     score++;
                                 }
                             }
                             Random r =new Random();
                             int i =r.nextInt(100);
                             if(i%5==0)
                                moveObj = new int[] {20-2, 20-1, 20*2-2, 20*2-1};
                             else if(i%5==1) 
                                moveObj = new int[] {20-2, 20-1, 20*2-2, 20*3-2};
                             else if(i%5==2)
                                moveObj = new int[] {20-2, 20-1, 20*2-1, 20*3-1};
                             else if(i%5==3)
                                moveObj = new int[] {20-2, 20*2-1, 20*2-2, 20*3-2};
                             else if(i%5==4)
                             	moveObj = new int[] {20*2-1, 20-1, 20*3-1, 20*4-1};              
                         }
                     }
                     repaint();
                     moveIndex++ ;
                 }
             }, 10, 100);
    		 return 1;
    	}catch(Exception e)
    	{
    		return 0;
    	}
    	
    }

    public static void main(String[] args) {
       z a =  new z();
       a.Jframe();
       a.Time();
    }
}