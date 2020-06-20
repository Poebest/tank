package main.java.com.poe.tank.common;

import cn.hutool.core.img.ImgUtil;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @version v1.0
 * @Author: poe on 2020/6/13 5:23 下午
 * @Description TODO tank资源
 * @Classname ResourceMgr
 */

@Slf4j
public class ResourceMgr {
    /**
     * 存活的tank
     */
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    /**
     * 击毁的tank
     */
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    /**
     * 子弹
     */
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    /**
     * 子弹爆炸 效果
     */
    public static BufferedImage[] explodes = new BufferedImage[16];


    // FIXME: 2020/6/13 数据初始化
    static {
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImgUtil.toBufferedImage(ImgUtil.rotate(goodTankU, -90));
            goodTankR = ImgUtil.toBufferedImage(ImgUtil.rotate(goodTankU, 90));
            goodTankD = ImgUtil.toBufferedImage(ImgUtil.rotate(goodTankU, 180));

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImgUtil.toBufferedImage(ImgUtil.rotate(badTankU, -90));
            badTankR = ImgUtil.toBufferedImage(ImgUtil.rotate(badTankU, 90));
            badTankD = ImgUtil.toBufferedImage(ImgUtil.rotate(badTankU, 180));

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImgUtil.toBufferedImage(ImgUtil.rotate(bulletU, -90));
            bulletR = ImgUtil.toBufferedImage(ImgUtil.rotate(bulletU, 90));
            bulletD = ImgUtil.toBufferedImage(ImgUtil.rotate(bulletU, 180));

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO
                        .read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }

        } catch (Exception e) {
            log.error("获取资源失败 {} , 请检查", e);
        }
    }
}
