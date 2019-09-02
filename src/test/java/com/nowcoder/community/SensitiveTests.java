package com.nowcoder.community;

import com.nowcoder.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Title: SensitiveTests
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-07-24 00:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitivwFilter() {
        String text = "可以赌博，可以吸毒，可以开票对党的";
        text = sensitiveFilter.fillter(text);
        System.out.println(text);

        text = "可以赌@#博，可以吸^^^毒，可以开票对党的";
        text = sensitiveFilter.fillter(text);
        System.out.println(text);
    }


}
