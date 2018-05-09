package com.example.demo.security;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("myredistest")
public class RedisController {
	private StringRedisTemplate template;
	
	ValueOperations<String, String> redisOperations;
	
	public RedisController(StringRedisTemplate template) {
		this.template = template;
		redisOperations = template.opsForValue();
	}
	
	@RequestMapping("/get")
	public String get() {
		return redisOperations.get("mykey");
	}
	
	@RequestMapping("/set")
	public String set() {
		redisOperations.set("mykey", "abc");
		return "success";
	}
}
