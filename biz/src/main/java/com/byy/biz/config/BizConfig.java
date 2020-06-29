package com.byy.biz.config;

import com.byy.biz.service.Scanned;
import com.byy.dal.DalConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yyc
 * @date: 19-6-10 上午10:07
 */
@SpringBootConfiguration
@Import({DalConfig.class, RedisCacheConfig.class})
@ComponentScan(basePackageClasses = {Scanned.class})
@EnableAspectJAutoProxy
public class BizConfig {

  @Bean
  public PasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
