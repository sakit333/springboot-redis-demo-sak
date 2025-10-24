package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    private final RedisTemplate<String, Object> redisTemplate;

    public UserService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveUser(User user) {
        redisTemplate.opsForValue().set("USER:" + user.getId(), user, 10, TimeUnit.MINUTES);
    }

    public User getUser(String id) {
        Object obj = redisTemplate.opsForValue().get("USER:" + id);
        return obj instanceof User ? (User) obj : null;
    }
}
