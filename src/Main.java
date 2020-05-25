import java.awt .*;
import java.awt.event.ActionEvent;
import javax.swing .*;


public class Main extends JFrame {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;

    public JMenuItem pauseMenuItem;
    private JMenuItem pauseTwoMenuItem;
    public JMenuItem resumeMenuItem;

    private Field field = new Field();

    public Main() {
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        setExtendedState(MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            @Override
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() && !resumeMenuItem.isEnabled()) {
                    pauseMenuItem.setEnabled(true);
                    pauseTwoMenuItem.setEnabled(true);
                }
            }
        };

        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);

        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);

        Action pauseAction = new AbstractAction("Приостановить движение") {
            @Override
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                pauseTwoMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);

        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                pauseTwoMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);

        Action littlePauseAction = new AbstractAction("Остановить мячи малого радиуса") {
            public void actionPerformed(ActionEvent event) {
                {
                    field.pauseLittle();
                    pauseMenuItem.setEnabled(false);
                    pauseTwoMenuItem.setEnabled(false);
                    resumeMenuItem.setEnabled(true);
                }
            }
        };
        pauseTwoMenuItem = controlMenu.add(littlePauseAction);
        pauseTwoMenuItem.setEnabled(false);
        getContentPane().add(field, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
