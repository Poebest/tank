package main.java.com.poe.tank.entity;

import lombok.*;
import main.java.com.poe.tank.common.Constants;
import main.java.com.poe.tank.enums.Dir;

import java.awt.*;

/**
 * @version v1.0
 * @Author: poe on 2020/6/15 5:31 下午
 * @Description 子弹实体
 * @Classname Bullet
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Bullet {

    private static int WIDTH = 30, HEIGHT = 30;
    /**
     * bullet 位置
     */
    private int x, y;

    /**
     * 方向
     */
    private Dir dir;

    private TankFrame tankFrame;
    /**
     * 是否存活
     */
    private Boolean isLive = true;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }


    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.red);
        //位置移动
        g.fillRect(x, y, WIDTH, HEIGHT);
        //解决, 位置移动后, 闪烁问题
        g.setColor(color);

        move();
    }

    private void move() {
        if (!isLive) {
            return;
        }

        switch (dir) {
            case LEFT:
                x -= Constants.bulletSpeed;
                break;
            case RIGHT:
                x += Constants.bulletSpeed;
                break;
            case DOWN:
                y += Constants.bulletSpeed;
                break;
            case UP:
                y -= Constants.bulletSpeed;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > Constants.gameWidth || y > Constants.gameHeight) {
            isLive = false;
        }
    }
}
