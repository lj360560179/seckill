package org.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * Created by LJ on 2016/6/21.
 */
public class RepeatkillException extends  SeckillException{
    public RepeatkillException(String message) {
        super(message);
    }

    public RepeatkillException(String message, Throwable cause) {
        super(message, cause);
    }
}
