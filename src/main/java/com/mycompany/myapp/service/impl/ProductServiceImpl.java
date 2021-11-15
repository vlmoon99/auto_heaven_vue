package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.repository.ProductRepository;
import com.mycompany.myapp.service.ProductService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> partialUpdate(Product product) {
        log.debug("Request to partially update Product : {}", product);

        return productRepository
            .findById(product.getId())
            .map(existingProduct -> {
                if (product.getCountryOfOrigin() != null) {
                    existingProduct.setCountryOfOrigin(product.getCountryOfOrigin());
                }
                if (product.getAvailable() != null) {
                    existingProduct.setAvailable(product.getAvailable());
                }
                if (product.getPictureUrls() != null) {
                    existingProduct.setPictureUrls(product.getPictureUrls());
                }
                if (product.getName() != null) {
                    existingProduct.setName(product.getName());
                }
                if (product.getVendor() != null) {
                    existingProduct.setVendor(product.getVendor());
                }
                if (product.getVendorCode() != null) {
                    existingProduct.setVendorCode(product.getVendorCode());
                }
                if (product.getProperties() != null) {
                    existingProduct.setProperties(product.getProperties());
                }
                if (product.getPropertiesContentType() != null) {
                    existingProduct.setPropertiesContentType(product.getPropertiesContentType());
                }
                if (product.getPrice() != null) {
                    existingProduct.setPrice(product.getPrice());
                }
                if (product.getTotalRating() != null) {
                    existingProduct.setTotalRating(product.getTotalRating());
                }
                if (product.getDescription() != null) {
                    existingProduct.setDescription(product.getDescription());
                }

                return existingProduct;
            })
            .map(productRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}
