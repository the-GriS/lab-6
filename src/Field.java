import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel{
    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
    private boolean paused;

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    public Field(){
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void addBall(){
        balls.add(new BouncingBall(this));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for(BouncingBall ball : balls){
            ball.paint(canvas);
        }
    }

    public synchronized void pause(){
        paused = true;
    }



    public synchronized void resume(){
        paused = false;
        notifyAll();
    }

    public synchronized void canMove(BouncingBall ball) throws InterruptedException {
        if(paused){
            wait();
        }
    }
}
