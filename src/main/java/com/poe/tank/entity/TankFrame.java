package main.java.com.poe.tank.entity;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.java.com.poe.tank.common.Constants;
import main.java.com.poe.tank.enums.Dir;
import main.java.com.poe.tank.enums.Group;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @version v1.0
 * @Author: poe on 2020/6/13 3:47 下午
 * @Description TODO 初始化tank界面 数据实体
 * @Classname TankFrame
 */
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class TankFrame extends Frame {
    /**
     * 初始化一个实体
     */
    public static final TankFrame INSTANCE = new TankFrame();

    /**
     * 初始化生成tank内容
     */
    public Tank tank =
            new Tank(RandomUtil.randomInt(Constants.gameWidth), RandomUtil.randomInt(Constants.gameHeight), Dir.DOWN, Group.GOOD, this);

    public int x, y = 100;
    /**
     * 子弹列表
     */
    public List<Bullet> list = ListUtil.list(true);
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
        //设置tank 出现初始位置
        g.fillRect(x, y, 50, 50);

    }

    // FIXME: 2020/6/16  自定义键盘监听内部类
    class MyKeyListener extends KeyAdapter {

        protected boolean bL = false;
        protected boolean bR = false;
        protected boolean bU = false;
        protected boolean bP = false;

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
                default:
                    break;

            }
        }
    }
}
