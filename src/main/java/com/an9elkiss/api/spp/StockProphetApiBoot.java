package com.an9elkiss.api.spp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Slf4j
@SpringBootApplication
//@EnableScheduling
//@MapperScan("com.an9elkiss.api.spp.dao")
public class StockProphetApiBoot extends WebMvcConfigurerAdapter implements CommandLineRunner, BeanFactoryPostProcessor {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(StockProphetApiBoot.class).run(args);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info(StringUtils.arrayToDelimitedString(beanFactory.getBeanDefinitionNames(),","));
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
    

	/**
	 * 实现封装PUT请求的From体至Command
	 * @return
	 */
//    @Bean
//    public FilterRegistrationBean httpPutFormContentFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        HttpPutFormContentFilter httpPutFormContentFilter = new HttpPutFormContentFilter();
//        registration.setFilter(httpPutFormContentFilter);
//        registration.addUrlPatterns("/*");
//        return registration;
//    }
    
    /**
	 * 允许跨域请求。swagger可能发起跨域请求
	 * 
	 * @return
	 */
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedHeaders("*")
//                        .allowedMethods("*")
//                        .allowedOrigins("*");
//            }
//        };
//    }


}
