package com.nttdata.bootcamp.ms.bankaccount.config;

import com.nttdata.bootcamp.ms.bankaccount.entity.BankAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheRedisConfiguration {

    @Bean
    public ReactiveRedisTemplate<Integer, BankAccount> reactiveJsonPostRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {

        RedisSerializationContext<Integer, BankAccount> serializationContext = RedisSerializationContext
                .<Integer, BankAccount>newSerializationContext(new StringRedisSerializer())
                .hashKey(new Jackson2JsonRedisSerializer<>(Integer.class))
                .hashValue(new Jackson2JsonRedisSerializer<>(BankAccount.class))
                .build();

        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }
}
