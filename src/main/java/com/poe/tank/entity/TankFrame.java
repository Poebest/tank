package main.java.com.poe.tank.entity;

import cn.hutool.core.collection.ListUtil;
import lombok.*;
import main.java.com.poe.tank.common.Constants;
import main.java.com.poe.tank.enums.Dir;
import main.java.com.poe.tank.enums.Group;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * @version v1.0
 * @Author: poe on 2020/6/13 3:47 下午
 * @Description TODO 初始化tank界面 数据实体
 * @Classname TankFrame
 */

@Setter
@Getter
public class TankFrame extends Frame {
    /**
     * 初始化一个实体
     */
    public static final TankFrame INSTANCE = new TankFrame();
    /**
     * 初始化生成tank内容
     */
    public Tank tank =
            new Tank(Constants.tankHeight, Constants.tankHeight, Dir.DOWN, Group.GOOD, this);
    private Explode explode = new Explode(100, 100, this);

    public List<Tank> tanks = ListUtil.list(false);


    public List<Bullet> bullets = ListUtil.list(false);

    public int x, y = 100;
    /**
     * 子弹列表
     */
    public List<Bullet> list = ListUtil.list(false);
    /**
     * tank 数量
     */
    Map<UUID, Tank> map = new HashMap<>(Constants.tankCount);

    public TankFrame() {
        //设置窗口大小
        setSize(Constants.gameWidth, Constants.gameHeight);
        //设置是否可以手动调整窗口大小
        setResizable(false);
        setTitle("TanK War");
        this.addKeyListener(new MyKeyListener());

        //设置窗口关闭
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量  : " + bullets.size(), 10, 60);
        g.drawString("敌人的数量  : " + bullets.size(), 10, 80);
        //设置tank 出现初始位置
        tank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //进行碰撞校验
        for (int i = 0; i < bullets.size(); i++) {
            for (Tank value : tanks) {
                bullets.get(i).collideWith(value);
            }
        }
        explode.paint(g);

        g.setColor(color);
    }

    Image image = null;

    //解决双重缓冲, 闪烁问题
    @Override
    public void update(Graphics g) {
        if (Objects.isNull(image)) {
            //解决双重缓冲问题
            image = this.createImage(Constants.gameWidth, Constants.gameHeight);
        }
        Graphics graphics = image.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Constants.gameWidth, Constants.gameHeight);
        graphics.setColor(color);
        paint(graphics);
        //创建一个透明的图层
        g.drawImage(image, 0, 0, null);
    }


    // FIXME: 2020/6/16  自定义键盘监听内部类
    class MyKeyListener extends KeyAdapter {

        private boolean bL = false;
        private boolean bR = false;
        private boolean bU = false;
        private boolean bP = false;

        /**
         * 按下键盘
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    x -= 50;

                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    x += 50;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    y -= 50;
                    break;
                case KeyEvent.VK_DOWN:
                    bP = true;
                    y += 50;

                    break;
                default:
                    break;

            }
            setMainTankDir();
        }

        /**
         * 键盘弹起
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bP = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;

            }
            setMainTankDir();
        }

        /**
         * 设置tank方向
         */
        private void setMainTankDir() {

            //按下其他按键的时候, 不进行移动tank

            if (!bL && !bU && !bR && !bP) {
                tank.setMoving(false);
            } else {
                tank.setMoving(true);
                if (bL) {
                    tank.setDir(Dir.LEFT);
                }
                if (bU) {
                    tank.setDir(Dir.UP);
                }
                if (bR) {
                    tank.setDir(Dir.RIGHT);
                }
                if (bP) {
                    tank.setDir(Dir.DOWN);
                }
            }
        }
    }
}
