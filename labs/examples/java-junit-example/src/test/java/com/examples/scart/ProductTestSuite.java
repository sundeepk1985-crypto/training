package com.examples.scart;

import com.examples.scart.product.ProductTest;
import com.examples.scart.service.ProductServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ProductTest.class, ProductServiceTest.class})
public class ProductTestSuite {

}
