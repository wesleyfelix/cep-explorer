package br.com.cep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class CepApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);

		// *** URL below needs to match the Vue client URL and port **
		config.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://192.168.1.1:8080", "http://192.168.1.1:8080/"));
		config.setAllowedMethods(Collections.singletonList("*"));

		config.setAllowedHeaders(Collections.singletonList("*"));

		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;

	}
}
