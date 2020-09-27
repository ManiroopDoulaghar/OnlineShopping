package com.onlineshopping.onlineshopping;

import com.onlineshopping.onlineshopping.model.Category;
import com.onlineshopping.onlineshopping.model.Product;
import com.onlineshopping.onlineshopping.repository.CategoryRepository;
import com.onlineshopping.onlineshopping.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OnlineshoppingApplication {

	private static final Logger log = LoggerFactory.getLogger(OnlineshoppingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OnlineshoppingApplication.class, args);

	}
}