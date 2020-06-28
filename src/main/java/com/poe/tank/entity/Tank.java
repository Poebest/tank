package main.java.com.poe.tank.entity;

import cn.hutool.core.lang.UUID;
import lombok.*;
import main.java.com.poe.tank.common.Constants;
import main.java.com.poe.tank.common.ResourceMgr;
import main.java.com.poe.tank.enums.Dir;
import main.java.com.poe.tank.enums.Group;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

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

    private Random random = new Random();

    /**
     * 默认坦克方向
     */
    private Dir dir = Dir.DOWN;
    /**
     * 默认不进行移动
     */
    private boolean moving = true;
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
    Rectangle rectangle = new Rectangle();

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
//        Color color = g.getColor();
//        g.setColor(Color.yellow);
//        //位置移动
//        g.fillRect(x, y, 50, 50);
//        g.setColor(color);
        if (!living) {
            tankFrame.tanks.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(Objects.equals(this.group, Group.GOOD) ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(Objects.equals(this.group, Group.GOOD) ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(Objects.equals(this.group, Group.GOOD) ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case UP:
                g.drawImage(Objects.equals(this.group, Group.GOOD) ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            default:
                break;
        }

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

        if (Objects.equals(this.group, Group.BAD) && random.nextInt(100) > 95) {
            this.fire();
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            //随机方向
            randomDir();

            boundsCheck();

            rectangle.x = x;
            rectangle.y = y;
        }

    }

    /**
     * 边界检查
     */
    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28) {
            y = 28;
        }
        if (this.x > Constants.gameWidth - Constants.tankWidth - 2) {
            x = Constants.gameWidth - Constants.tankWidth - 2;
        }
        if (this.y > Constants.gameHeight - Constants.tankHeight - 2) {
            y = Constants.gameHeight - Constants.tankHeight - 2;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {

//        tankFrame.bullets.add(new Bullet(this.x, this.y, this.dir, tankFrame));
        //矫正 bullets 位置
        int bx = this.x + Constants.tankWidth / 2 - Constants.bulletWidth / 2;
        int by = this.y + Constants.tankHeight / 2 - Constants.bulletHeight / 2;
        tankFrame.bullets.add(new Bullet(bx, by, this.dir, this.group, tankFrame));
        if (Objects.equals(this.group, Group.GOOD)) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    public void die() {
        this.living = false;
    }
}
