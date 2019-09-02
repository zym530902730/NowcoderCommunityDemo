package com.nowcoder.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Title: ForgetMapper
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-07-08 21:47
 */
@Mapper
public interface ForgetMapper {

    @Update({
            "update user set password=#{password} where email=#{email} ",
    })
    int updateStatus(String password, String email);

}
