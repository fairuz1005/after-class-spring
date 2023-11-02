package com.metrodataacademy.redis;

import com.metrodataacademy.domain.dto.request.ReqRedisUpdateProfileDto;
import com.metrodataacademy.domain.dto.request.ReqRegisterDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class Publisher {
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
    public RedisTemplate<String, ReqRedisUpdateProfileDto> publishUpdateProfile() {
        RedisTemplate<String, ReqRedisUpdateProfileDto> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer(ReqRedisUpdateProfileDto.class));
        template.setEnableTransactionSupport(true);
        return template;
    }

//    @Bean
//    public RedisTemplate<String, ReqRegisterDto> publishRegister() {
//
//    }
}
