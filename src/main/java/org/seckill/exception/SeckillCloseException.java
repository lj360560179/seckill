package org.seckill.exception;

/**
 * 秒杀结束异常
 * Created by LJ on 2016/6/21.
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
