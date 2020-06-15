package test.com.poe.tank;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @version v1.0
 * @Author: poe on 2020/6/13 11:04 上午
 */
@Slf4j
public class testSlf4j {

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            log.info(String.valueOf(i));
        }
    }

}
