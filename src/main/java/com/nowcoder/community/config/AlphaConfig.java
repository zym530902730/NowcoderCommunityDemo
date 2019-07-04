package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

/**
 * Title: AlphaConfig
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-06-30 22:26
 */
@Configuration
public class AlphaConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
