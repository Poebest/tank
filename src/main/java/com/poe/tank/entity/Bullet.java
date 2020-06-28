package main.java.com.poe.tank.entity;

import lombok.*;
import main.java.com.poe.tank.common.Constants;
import main.java.com.poe.tank.common.ResourceMgr;
import main.java.com.poe.tank.enums.Dir;
import main.java.com.poe.tank.enums.Group;

import java.awt.*;
import java.util.Objects;

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

    private static int WIDTH = ResourceMgr.bulletD.getWidth();
    private static int HEIGHT = ResourceMgr.bulletD.getHeight();

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

    private Group group;

    Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

    }


    public void paint(Graphics g) {
//        Color color = g.getColor();
//        g.setColor(Color.red);
//        //位置移动
//        g.fillRect(x, y, WIDTH, HEIGHT);
//        //解决, 位置移动后, 闪烁问题
//        g.setColor(color);
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if (!isLive) {
            tankFrame.bullets.remove(this);
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

        rectangle.x = this.x;
        rectangle.y = this.y;

        if (x < 0 || y < 0 || x > Constants.gameWidth || y > Constants.gameHeight) {
            isLive = false;
        }
    }

    /**
     * 碰撞计算 定义在一个位置 , 如果在同个区域中有交集, 即为bullet 和 tank 即为碰撞
     *
     * @param tank
     */
    public void collideWith(Tank tank) {
        if (Objects.equals(this.group, tank.getGroup())) {
            return;
        }
//        //子弹的区域
//        Rectangle rec1 = new Rectangle(x, y, WIDTH, HEIGHT);
//        //tank区域
//        Rectangle rec2 = new Rectangle(tank.getX(), tank.getHeight(), Constants.tankWidth, Constants.tankHeight);
        if (rectangle.intersects(tank.rectangle)) {
            tank.die();
            this.die();
            //调整爆炸的位置
            int ex = tank.getX() + Constants.tankWidth / 2 - Explode.WIDTH / 2;
            int ey = tank.getY() + Constants.tankHeight / 2 - Explode.HEIGHT / 2;
            //添加爆炸效果
            tankFrame.explodes.add(new Explode(ex, ey, tankFrame));
        }
    }

    private void die() {
        this.isLive = false;

    }
}
