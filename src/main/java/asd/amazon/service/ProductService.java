package asd.amazon.service;

import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAll();

    ProductDTO create(ProductDTO productDTO);
}
