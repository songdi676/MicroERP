
package cn.zhangxd.platform.system.provider.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 缓存配置
 *
 * @author zhangxd
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport
{

    private static Logger logger = LoggerFactory.getLogger( CacheConfig.class );

    /**
     * Method key generator key generator.
     *
     * @return the key generator
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator()
    {
        return ( target, method, params ) -> {
            StringBuilder sb = new StringBuilder();
            sb.append( target.getClass().getName() );
            sb.append( method.getName() );
            for ( Object obj : params )
            {
                logger.debug( "params:" + obj.toString() );
                sb.append( obj.toString() );
            }
            return sb.toString();
        };
    }

    /**
     * Cache manager cache manager.
     *
     * @param redisTemplate
     *            the redis template
     * @return the cache manager
     */
    @Bean
    public CacheManager cacheManager( RedisTemplate redisTemplate )
    {
        return new RedisCacheManager( redisTemplate );
    }

    /**
     * Redis template redis template.
     *
     * @param factory
     *            the factory
     * @return the redis template
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory factory )
    {
        StringRedisTemplate template = new StringRedisTemplate( factory );
        setSerializer( template ); // 设置序列化工具
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 设置Serializer
     *
     * @param template
     *            RedisTemplate
     */
    private void setSerializer( StringRedisTemplate template )
    {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class );
        ObjectMapper om = new ObjectMapper();
        om.setVisibility( PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY );
        om.enableDefaultTyping( ObjectMapper.DefaultTyping.NON_FINAL );
        jackson2JsonRedisSerializer.setObjectMapper( om );
        template.setValueSerializer( jackson2JsonRedisSerializer );
    }

}
