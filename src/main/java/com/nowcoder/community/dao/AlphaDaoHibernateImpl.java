package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * Title: AlphaDaoHibernatempl
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-06-30 20:57
 */

@Repository("alphaHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {

    @Override
    public String select() {
        return "Hibernate";
    }
}
