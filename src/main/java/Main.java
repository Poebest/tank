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
        TankFrame tf = TankFrame.INSTANCE;

        tf.setVisible(true);

        Integer tankCount = Constants.tankCount;
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    log.error("tank error", e);
                }
                tf.repaint();
            }
        }).start();
    }
}
