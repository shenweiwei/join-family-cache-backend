package org.sww.joinfamily.cache.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sww.joinfamily.cache.constants.QueueConstant;

@Configuration
public class RabbitConfig {
	// 声明队列
	@Bean
	public Queue pictureQueue() {
		return new Queue(QueueConstant.MESSAGE_PICTURE_QUEUE_NAME, true); // true表示持久化该队列
	}

	// 声明交互器
	@Bean
	TopicExchange messageTopicExchange() {
		return new TopicExchange(QueueConstant.MESSAGE_PICTURE_EXCHANGE);
	}

	// 绑定
	@Bean
	public Binding pictureBinding() {
		return BindingBuilder.bind(pictureQueue()).to(messageTopicExchange()).with(QueueConstant.MESSAGE_PICTURE_ROUTE_KEY);
	}

}
