package main.java.com.poe.tank.entity;

import cn.hutool.core.lang.UUID;
import lombok.*;
import main.java.com.poe.tank.common.Constants;
import main.java.com.poe.tank.enums.Dir;
import main.java.com.poe.tank.enums.Group;

import java.awt.*;

/**
 * @version v1.0
 * @Author: poe on 2020/6/13 4:21 下午
 * @Description TODO tank 数据实体
 * @Classname Tank
 */
@Setter
@Getter
public class Tank {
    /**
     * 坦克速度
     */
    private int tankSpeed;
    /**
     * 宽度
     */
    private int width = Constants.tankWidth;
    /**
     * 高度
     */
    private int height = Constants.tankHeight;
    /**
     * uuid
     */
    private UUID id = UUID.randomUUID();
    /**
     * 默认坦克方向
     */
    private Dir dir = Dir.DOWN;
    /**
     * 默认不进行移动
     */
    private boolean moving = false;
    /**
     * 定义tank 初始化位置,x
     */
    private int x;
    /**
     * 定义tank 初始化位置,y
     */
    private int y;
    /**
     * tank界面 数据实体
     */
    private TankFrame tankFrame;
    /**
     * tank 是否存活
     */
    private boolean living = true;
    /**
     * 坦克是否存活分组
     */
    private Group group = Group.BAD;
    /**
     * 用于设置tank 位置
     */
    private Rectangle rectangle = new Rectangle();

    // FIXME: 2020/6/13 tank新的构造方法
    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = x;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = width;
        rectangle.height = height;
    }

    public void paint(Graphics g) {
        //set tank color
        Color color = g.getColor();
        g.setColor(Color.yellow);
        //位置移动
        g.fillRect(x, y, 50, 50);
        //解决, 位置移动后, 闪烁问题
        g.setColor(color);

        move();
    }

    /**
     * 是否进行移动
     */
    private void move() {

        if (!moving) {
            return;
        }

        switch (dir) {
            case LEFT:
                x -= Constants.tankSpeed;
                break;
            case RIGHT:
                x += Constants.tankSpeed;
                break;
            case DOWN:
                y += Constants.tankSpeed;
                break;
            case UP:
                y -= Constants.tankSpeed;
                break;
            default:
                break;
        }
    }

    public void fire() {

        tankFrame.bullets.add(new Bullet(this.x, this.y, this.dir, tankFrame));
    }
}
