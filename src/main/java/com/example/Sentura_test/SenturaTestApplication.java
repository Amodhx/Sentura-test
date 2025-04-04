package com.example.Sentura_test;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SenturaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenturaTestApplication.class, args);
	}

	@Bean
	OkHttpClient okHttpClient(){
		return new OkHttpClient();
	}

}
