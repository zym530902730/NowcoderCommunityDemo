package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Title: AlphoDaoMybatisImpl
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-06-30 21:01
 */
@Repository
@Primary
public class AlphoDaoMyBatisImpl implements AlphaDao {
    @Override
    public String select() {
        return "MyBatis";
    }
}
