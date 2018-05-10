package camt.se234.project;

import camt.se234.project.dao.ProductDao;
import camt.se234.project.entity.Product;
import camt.se234.project.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    ProductDao productDao;
    ProductServiceImpl productService;

    @Before
    public void setup() {
        productDao = mock(ProductDao.class);
        productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
    }

    @Test
    public void getAllProductsTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("mango"));
        mockProducts.add(new Product("banana"));
        mockProducts.add(new Product("orange"));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getAllProducts(), hasItem(new Product("mango")));
        assertThat(productService.getAllProducts(), hasItems(new Product("banana"),
                new Product("mango")));
    }

    @Test
    public void getAvailableProductsTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("mango", 20));
        mockProducts.add(new Product("banana", 18));
        mockProducts.add(new Product("blackStar", 8));
        mockProducts.add(new Product("leg", -10));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getAvailableProducts(), hasItem(new Product("mango", 20)));
        assertThat(productService.getAvailableProducts(), is(not(new Product("blackStar", -5))));
    }

    @Test
    public void getUnavailableProductSizeTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("mango", 20));
        mockProducts.add(new Product("banana", 18));
        mockProducts.add(new Product("lemon", 8));
        mockProducts.add(new Product("bottle", 18));
        mockProducts.add(new Product("leg", -10));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getUnavailableProductSize(), is(1));
    }
}