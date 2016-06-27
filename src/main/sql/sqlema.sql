--数据库初始化脚本

--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;
--创建秒杀库存表
CREATE TABLE `seckill`(
`seckill_id`  bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存ID' ,
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`star_time` timestamp NOT NULL COMMENT '秒杀开启时间',
`end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDb AUTO_INCREMENT=1000 DEFAULT CHARACTER SET=utf8 COMMENT '秒杀库存表';

--初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
  ('1000元秒杀苹果1',100,'2016-06-15 00:00:00','2016-06-16 00:00:00'),
  ('500元秒杀苹果2',200,'2016-06-15 00:00:00','2016-06-16 00:00:00'),
  ('10元秒杀苹果3',300,'2016-06-15 00:00:00','2016-06-16 00:00:00'),
  ('5元秒杀苹果4',400,'2016-06-15 00:00:00','2016-06-16 00:00:00');


  --秒杀成功明细表
  --用户登录认证相关信息
create table success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品ID',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识：-1无效 0：成功 1：已付款',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
key idx_create_time(create_time)
)ENGINE=InnoDb DEFAULT CHARACTER SET=utf8 COMMENT '秒杀成功明细表';

--连接数据库控制台
mysql -uroot -p