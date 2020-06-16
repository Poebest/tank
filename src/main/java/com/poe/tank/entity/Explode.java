package main.java.com.poe.tank.entity;

import main.java.com.poe.tank.common.ResourceMgr;

/**
 * @version v1.0
 * @Author: poe on 2020/6/15 5:35 下午
 * @Description TODO 爆炸
 * @Classname Explode
 */
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

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        // FIXME: 2020/6/15 爆炸的时候,同时播放爆炸音效
//        new  Thread(()-> new Audio())
    }
}
