package com.product.review.service;

import com.product.review.dto.ProductDTO;
import com.product.review.entity.Product;
import com.product.review.entity.Seller;
import com.product.review.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setup() {

        product = Product.builder()
                .productId(1)
                .productName("lamp")
                .productPrice(10.6)
                .category("light")
                .build();


    }
    @Test
    public void testAddProduct(){
        ProductDTO productDTO1 = ProductDTO.builder()
                .productId(5)
                .productName("bike")
                .productPrice(12.8)
                .category("vehicles")
                .seller(new Seller())
                .build();
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(null, productService.addProduct(productDTO1));
    }

    @Test
    public void testProductList(){

        Product product1 = Product.builder()
                .productId(2)
                .productName("Tony")
                .productPrice(44.4)
                .category("na")
                .build();

        given(productRepository.findAll()).willReturn(List.of(product,product1));

        List<Product> productList = productService.getAllProducts();

        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);
    }
    @Test
    public void testProductListNegative(){

        given(productRepository.findAll()).willReturn(Collections.emptyList());

        List<Product> productList = productService.getAllProducts();

        assertThat(productList).isEmpty();
        assertThat(productList.size()).isEqualTo(0);
    }
    @Test
    public void testGetProductId(){
        int productId = 1;
        given(productRepository.findById(1)).willReturn(Optional.of(product));

        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()) {
            Product savedProduct = productService.getProductById(productId);
            assertThat(savedProduct).isEqualTo(product);
        }

    }
    @Test
    public void testDeleteProduct(){
        int productId = 1;

        willDoNothing().given(productRepository).deleteById(productId);

        Optional<Product> optional = productRepository.findById(productId);
        if(optional.isPresent()) {
            productService.deleteProduct(productId);
            verify(productRepository, times(1)).deleteById(productId);
        }
    }
    @Test
    public void testUpdateProductPositive(){

        when(productRepository.save(product)).thenReturn(product);
        product.setProductName("stark");
        product.setCategory("and");
        assertEquals("stark", product.getProductName());
        assertEquals("and", product.getCategory());
    }





}
