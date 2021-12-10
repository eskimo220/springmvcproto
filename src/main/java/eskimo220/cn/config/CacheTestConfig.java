package eskimo220.cn.config;

import org.apache.catalina.Manager;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationContextFacade;
import org.apache.catalina.core.StandardContext;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.redisson.tomcat.RedissonSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheTestConfig {

//    @Autowired
//    private ApplicationContextFacade servletContext;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient jndiRedissonClient() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:comp/env/bean/redisson");
        bean.afterPropertiesSet();
        return (RedissonClient) bean.getObject();
    }


/*    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {

        System.out.println("----------------");

        Field applicationContextField = servletContext.getClass().getDeclaredField("context");
        applicationContextField.setAccessible(true);
        ApplicationContext appContextObj = (ApplicationContext) applicationContextField.get(servletContext);
        Field standardContextField = appContextObj.getClass().getDeclaredField("context");
        standardContextField.setAccessible(true);
        StandardContext standardContextObj = (StandardContext) standardContextField.get(appContextObj);
        Manager persistenceManager = standardContextObj.getManager();

        System.out.println(persistenceManager);

        if (persistenceManager instanceof RedissonSessionManager) {
            RedissonClient redissonClient = ((RedissonSessionManager) persistenceManager).getRedisson();

            redissonClient.getAtomicLong("nums-xxxx-in").incrementAndGet();

            System.out.println(redissonClient);


//            System.out.println("Thread.currentThread().getContextClassLoader() = " + Thread.currentThread().getContextClassLoader());
//            System.out.println("this.getClass().getClassLoader() = " + this.getClass().getClassLoader());
//            this.getClass().getClassLoader();
//            Thread.currentThread().getContextClassLoader()
//            Class.forName("org.springframework.cache.CacheManager");
//            Class.forName("org.redisson.spring.cache.RedissonSpringCacheManager");

            return redissonClient;
        }

        return null;
    }*/

    @Bean
    public RedissonSpringCacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<>();

        // create "testMap" cache with ttl = 24 minutes and maxIdleTime = 12 minutes
        config.put("getCacheString-key", new CacheConfig(24 * 60 * 1000, 12 * 60 * 1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
