package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String PREFIX = "USER:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveUser(User user) {
        redisTemplate.opsForValue().set(PREFIX + user.getId(), user);
    }

    public User getUser(String id) {
        return (User) redisTemplate.opsForValue().get(PREFIX + id);
    }

    public void deleteUser(String id) {
        redisTemplate.delete(PREFIX + id);
    }
}
