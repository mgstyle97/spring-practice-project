package spring.practice.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"spring.practice.project.domain.global"})
public class GlobalConfig {
}
