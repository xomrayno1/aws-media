package com.media.core.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.media.core.filter.MdcFilter;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	@Bean
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.registerListener(new CustomRetryListener());
		return retryTemplate;
	}
	
	@Bean
    public FilterRegistrationBean<MdcFilter> loggingFilter() {
        FilterRegistrationBean<MdcFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MdcFilter());
        registrationBean.addUrlPatterns("/*"); // Áp dụng cho tất cả các URL
        registrationBean.setOrder(1); 

        return registrationBean;
    }
	
//
//	@Bean
//	public CharacterEncodingFilter characterEncodingFilter() {
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding(true);
//		return characterEncodingFilter;
//	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("GET", "PUT", "POST", "DELETE")
//				.allowedHeaders("*").maxAge(3600)
//		.allowCredentials(true).maxAge(3600);
//	}
}
