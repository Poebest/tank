package main.java.com.poe.tank.entity;

import cn.hutool.core.lang.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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
     * 左右移动距离
     */
    private int x;
    /**
     * 上下移动距离
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

}
