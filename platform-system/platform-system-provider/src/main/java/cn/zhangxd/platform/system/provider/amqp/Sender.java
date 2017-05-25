
package cn.zhangxd.platform.system.provider.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Sender
{
    private static Logger logger = LoggerFactory.getLogger( Sender.class );
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled( fixedDelay = 10000L )
    public void send()
    {
        logger.info( "发送消息---" );
        this.rabbitTemplate.convertAndSend( "foo", "hello" );
    }

}