package redisdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import redisdemo.service.OrderService;

@Configuration
public class RedisSubConfig {
    @Autowired
    private OrderService orderService;
    @Bean
    public MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(orderService, "insert");
    }

    /**
     * 定义消息监听者容器
     * @param connectionFactory 连接工厂
     * @param listenerAdapter 消息处理器
     * @return RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.addMessageListener(listenerAdapter, new PatternTopic("java02"));
        return listenerContainer;
    }

}
