package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title: DiscussPostMapper
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-07-02 15:55
 */
@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param 给参数取别名
    // 如果只有一个参数，并且在<if>里使用，则必须加别名 ?
    int selectDiscussPostRows(@Param("userId") int userId);



}
