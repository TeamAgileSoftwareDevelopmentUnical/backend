package asd.amazon.service.impl;


import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.Product;
import asd.amazon.repository.ProductRepository;
import asd.amazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAll() {
        return mapProducts(productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = false)
    public ProductDTO create(ProductDTO productDTO) {
        Product product = mapProduct(productDTO);
        productRepository.save(product);
        productDTO.setId(product.getId());
        return productDTO;
    }

    private Product mapProduct(ProductDTO productDTO){//TODO: do map
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        return product;
    }
    private ProductDTO mapProduct(Product product){//TODO: do map
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        return productDTO;
    }
    private List<ProductDTO> mapProducts(List<Product> products){
        List<ProductDTO> toReturn = new ArrayList<>();
        for(Product p : products){
            toReturn.add(mapProduct(p));
        }
        return toReturn;
    }
}
