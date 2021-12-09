package eskimo220.cn.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CacheTestService {

    @Cacheable(sync = true, value = "getCacheString-key")
    public String getCacheString(String key) {
        System.out.println("key = " + key);
        return UUID.randomUUID().toString();
    }
}
