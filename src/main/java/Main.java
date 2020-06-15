package main.java;

import lombok.extern.slf4j.Slf4j;
import main.java.com.poe.tank.common.Constants;
import main.java.com.poe.tank.entity.TankFrame;

/**
 * @version v1.0
 * @Author: poe on 2020/6/12 5:53 下午
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();

        tf.setVisible(true);

        Integer tankCount = Constants.tankCount;
        for (int i = 0; i < tankCount; i++) {
            log.info("坦克数量 {}", i);
        }
    }
}
