/**
 * Created by LJ on 2016/6/24.
 * 存放主要交互逻辑JS代码
 * javascript模块化
 */
var seckill = {
    //封装秒杀相关的URL
    URL: {
        new: function () {
            return '/seckill/time/now';
        },
        exposer : function(seckillId){
            return '/seckill/'+seckillId+'/exposer';
        },
        execution : function (seckillId,md5) {
            return '/seckill/'+seckillId+'/'+md5+'/execution';
        }
    },
    handleSeckillkill : function (seckillId,node) {
        //处理秒杀逻辑
        node.hide().html('<button class="btn btn-primary btn-lg" id="killbtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),{},function (result) {
            //回调函数中，执行交互流程
            if(result && result['success']){
                var exposer = result['date'];
                if(exposer['exposed']){
                    //开启秒杀
                    //获取秒杀的地址
                    var md5 =  exposer['md5']
                    var killUrl = seckill.URL.execution(seckillId,md5);
                    console.log("killUrl:"+killUrl);
                    //绑定一次点击事件
                   $("#killbtn").one('click',function(){
                        //执行秒杀请求
                        //1.禁用按钮
                        $(this).addClass('disabled');
                        //2.发送秒杀请求
                        $.post(killUrl,{},function (result) {
                            if(result && result['success']){
                                var killResult = result['date'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //3.显示秒杀结构
                                node.html('<spqn class="label label-success">'+stateInfo+'</spqn>');
                            }
                        });
                    });
                    node.show();
                }else{
                    //未开启秒杀
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end']
                    //重新计算计时逻辑
                    seckill.countdown(seckillId,now,start,end);
                }
            }else{
                console.log("result:"+result);
            }
        });
    },
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    countdown: function (seckillId, nowTime, starTime, endTime) {
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束!');
        } else if (nowTime < starTime) {
            //秒杀未开始，计时时间绑定
            var killTime = new Date(starTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                //时间格式
                var format = event.strftime('秒杀倒计时： %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                /**
                 * 时间完成后回调事件
                 */
            }).on('finish.countdown',function () {
                //获取秒杀地址，控制实现逻辑，执行秒杀
                seckill.handleSeckillkill(seckillId,seckillBox);
            });
            seckillBox.html('秒杀未开始!');
        } else {
            //秒杀开始
            seckill.handleSeckillkill(seckillId,seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //用户手机验证和登录，计时交互
            //规划我们的交互流程
            //没有登录后端，在cookies查找Phone
            var killPhone = $.cookie('killPhone');

            //验证手机号
            if (!seckill.validatePhone(killPhone)) {
                //如果没登录就是FALSE,绑定手机
                //控制输出

                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        //电话写入COOKIE
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });

            }
            //已经登录
            //计时交互
            var starTime = params['starTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];

            $.get(seckill.URL.new(), {}, function (result) {
                //
                if (result && result['success']) {
                    var nowTime = result['date'];
                    //时间判断,计时交互
                    seckill.countdown(seckillId,nowTime,starTime,endTime)
                } else {
                    console.log('result:' + result);
                }
            })
        }
    }
}