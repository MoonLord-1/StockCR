package com.yeirel.StockCR;

import org.springframework.boot.SpringApplication;

public class TestStockCrApplication {

	public static void main(String[] args) {
		SpringApplication.from(StockCrApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
