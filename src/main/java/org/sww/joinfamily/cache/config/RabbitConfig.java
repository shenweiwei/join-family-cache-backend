package org.sww.joinfamily.cache.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	// 声明队列
	@Bean
	public Queue pictureQueue() {
		return new Queue("picture.queue", true); // true表示持久化该队列
	}

	// 声明交互器
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("topicExchange");
	}

	// 绑定
	@Bean
	public Binding pictureBinding() {
		return BindingBuilder.bind(pictureQueue()).to(topicExchange()).with("key.1");
	}

}
