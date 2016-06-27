package org.seckill.dao;

import com.sun.net.httpserver.Authenticator;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by L450 on 2016/6/15.
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明显，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return如果影响行数>1,表示更新的记录行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据ID查询并携带秒杀产品对象的实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
