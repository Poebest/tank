package test.com.poe.tank;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

/**
 * @version v1.0
 * @Author: poe on 2020/6/13 5:30 下午
 * @Description TODO
 * @Classname testResourceMgr
 */
@Slf4j
public class testResourceMgr {

    @Test
    public void testResourceMgr() {
        try {
            // FIXME: 2020/6/13  获取resources 资源包下面的文件不需要 多加斜杆
            String path = this.getClass().getClassLoader().getResource("images/GoodTank1.png").getPath();

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("images/GoodTank1.png");
            if (Objects.isNull(inputStream)) {
                log.info("获取本地资源失败请检查");
            } else {
                BufferedImage read = ImageIO.read(inputStream);
                int height = read.getHeight();
                log.info("图片高度 {}", String.valueOf(height));
            }
        } catch (Exception e) {
            log.error("获取资源失败,请检查", e);
        }


    }
}
