package com.examples.scart.product;

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceMainTests {

	private static Map<Integer, String> products = new HashMap<>();

	@BeforeClass
	public static void setup() {
		System.out.println("Initialize product list");
		products.put(100, "Mobile");
		products.put(101, "Television");
		products.put(102, "Laptop");
	}

	@AfterClass
	public static void cleanup() {
		System.out.println("Clean-up product list");
		products.clear();
	}

	@Before
	public void init() {
		System.out.println("Called before test case...");
	}

	@After
	public void clean() {
		System.out.println("Called after test case...");
	}



	public String getProductName(int id)
	{
		return products.get(id);
//		if(id == 100) {
//			return "Mobile";
//		} else {
//			return "Invalid Id";
//		}
	}

	@Test
	public void shouldReturnValidProductNameWhenValidIdPassed() {
		Assertions.assertThat(getProductName(100)).isEqualTo("Mobile");
	}


	@Test
	public void shouldReturnNullWhenInValidIdPassed() {
		Assertions.assertThat(getProductName(103)).isNull();
	}
}
