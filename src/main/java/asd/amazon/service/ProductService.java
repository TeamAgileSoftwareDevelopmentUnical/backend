package asd.amazon.service;

import asd.amazon.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public List<ProductDTO> getAll();

    public ProductDTO create(ProductDTO productDTO);
}
