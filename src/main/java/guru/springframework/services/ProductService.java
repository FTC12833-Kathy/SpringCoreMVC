package guru.springframework.services;

import guru.springframework.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductService extends MapService<Product>{
    public ProductService() {
        domainMap = new HashMap<>();
            loadDomainObjects();
    }

    @Override
    protected void loadDomainObjects() {
        Product product = new Product();
        product.setId(1);
        product.setDescription("Product 1");
        product.setPrice(new BigDecimal("12.99"));
        product.setImageUrl("http://example.com/product1");

        domainMap.put(1, product);

        product = new Product();
        product.setId(2);
        product.setDescription("Product 2");
        product.setPrice(new BigDecimal("14.99"));
        product.setImageUrl("http://example.com/product2");

        domainMap.put(2, product);

        product = new Product();
        product.setId(3);
        product.setDescription("Product 3");
        product.setPrice(new BigDecimal("34.99"));
        product.setImageUrl("http://example.com/product3");

        domainMap.put(3, product);

        product = new Product();
        product.setId(4);
        product.setDescription("Product 4");
        product.setPrice(new BigDecimal("44.99"));
        product.setImageUrl("http://example.com/product4");

        domainMap.put(4, product);

        product = new Product();
        product.setId(5);
        product.setDescription("Product 5");
        product.setPrice(new BigDecimal("25.99"));
        product.setImageUrl("http://example.com/product5");

        domainMap.put(5, product);
    }
}
