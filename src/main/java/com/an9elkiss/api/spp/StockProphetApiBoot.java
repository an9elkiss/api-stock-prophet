package com.an9elkiss.api.spp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockProphetApiBoot {

    public static void main(String[] args) {
        SpringApplication.run(StockProphetApiBoot.class, args);
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
