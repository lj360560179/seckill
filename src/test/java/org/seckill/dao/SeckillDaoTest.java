package org.seckill.dao;

import javafx.scene.chart.PieChart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import static org.junit.Assert.*;

/**
 * Created by L450 on 2016/6/15.
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入DAO实现类
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception {
        long id=1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQuneryAll() throws Exception {
        List<Seckill> seckills= seckillDao.queryAll(0,100);
        for (Seckill seckill:seckills){
         System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        Date killtime= new Date();
        int updateCount=seckillDao.reduceNumber(1000L,killtime);
        System.out.println(updateCount);
    }


}