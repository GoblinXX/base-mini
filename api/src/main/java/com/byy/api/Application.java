package com.byy.api;

import com.byy.api.config.SecurityConfig;
import com.byy.api.config.WebMvcConfig;
import com.byy.api.security.service.UserDetailsServiceScanned;
import com.byy.api.service.Scanned;
import com.byy.biz.config.BizConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author: yyc
 * @date: 19-6-27 下午3:43
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {Scanned.class, UserDetailsServiceScanned.class})
@Import({BizConfig.class, SecurityConfig.class, WebMvcConfig.class})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
