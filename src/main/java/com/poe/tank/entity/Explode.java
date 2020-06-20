package main.java.com.poe.tank.entity;

import lombok.Getter;
import lombok.Setter;
import main.java.com.poe.tank.common.ResourceMgr;

import java.awt.*;

/**
 * @version v1.0
 * @Author: poe on 2020/6/15 5:35 下午
 * @Description TODO 爆炸
 * @Classname Explode
 */
@Setter
@Getter
public class Explode {
    /**
     * 爆炸效果宽度
     */
    private static int WIDTH = ResourceMgr.explodes[0].getWidth();
    /**
     * 爆炸效果高度
     */
    private static int HEIGHT = ResourceMgr.explodes[0].getWidth();

    /**
     * 爆炸位置x
     */
    private int x;
    /**
     * 爆炸位置y
     */
    private int y;

    private int step = 0;

    private TankFrame tankFrame;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        // FIXME: 2020/6/15 爆炸的时候,同时播放爆炸音效
//        new  Thread(()-> new Audio())
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            step = 0;
        }
    }
}
