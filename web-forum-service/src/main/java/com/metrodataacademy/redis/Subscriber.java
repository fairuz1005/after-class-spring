package com.metrodataacademy.redis;

import com.metrodataacademy.domain.dto.request.ReqRedisUpdateProfileDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;
import java.util.concurrent.Executors;

@Configuration
public class Subscriber {
    @Value("${auth.app.redis.port}")
    private int redisPort;

    @Value("${auth.app.redis.host}")
    private String redisHost;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration(redisHost, redisPort);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(conf);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    ChannelTopic channelTopicUpdateProfile(){
        return new ChannelTopic("UPDATE_PROFILE");
    }

    @Bean
    ChannelTopic channelTopicDeleteUser(){
        return new ChannelTopic("DELETE_USER");
    }

    @Bean("updateProfile")
    MessageListenerAdapter updateProfile(Receiver receiver){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver);
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer(ReqRedisUpdateProfileDto.class));

        return messageListenerAdapter;
    }

    @Bean("deleteUser")
    MessageListenerAdapter deleteUser(Receiver receiver){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver);
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer(ReqRedisUpdateProfileDto.class));

        return messageListenerAdapter;
    }

    @Bean
    RedisMessageListenerContainer redisContainer(@Qualifier("updateProfile") MessageListenerAdapter updateProfile,
                                                 @Qualifier("deleteUser") MessageListenerAdapter deleteUser){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(lettuceConnectionFactory());
        redisMessageListenerContainer.addMessageListener(updateProfile, channelTopicUpdateProfile());
        redisMessageListenerContainer.addMessageListener(deleteUser, channelTopicDeleteUser());
        redisMessageListenerContainer.setTaskExecutor(Executors.newFixedThreadPool(4));
        return redisMessageListenerContainer;
    }
}
