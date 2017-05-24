
package cn.zhangxd.platform.admin.web.common.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.zhangxd.platform.common.web.config.AbstractWebSecurityConfig;

/**
 * spring-security配置
 *
 * @author zhangxd
 */
@Configuration
public class WebSecurityConfig extends AbstractWebSecurityConfig
{

    @Override
    protected void configure( HttpSecurity security ) throws Exception
    {
        security.authorizeRequests()
                .antMatchers( HttpMethod.POST, "/auth/token" ).permitAll()
                .requestMatchers( CorsUtils::isPreFlightRequest ).permitAll();// 就是这一行啦
                                                                              // ;
        super.configure( security );
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurerAdapter()
        {
            @Override
            public void addCorsMappings( CorsRegistry registry )
            {
                registry.addMapping( "/**" ).allowedOrigins(
                        "http://10.61.16.103:8080" );
            }
        };
    }
}
