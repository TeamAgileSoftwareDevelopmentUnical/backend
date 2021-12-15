package asd.amazon.service;

import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.Batch;
import asd.amazon.entity.Product;
import asd.amazon.request.ProductUpdateRequest;
import asd.amazon.responses.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getProductBy(Long sellerID);

    public ProductDTO create(ProductDTO productDTO);
  
    public Boolean uploadNewProduct(ProductDTO product);

    public ProductResponse getProductFromBatch(Long productId);

    public Boolean updateProduct(Long id, ProductUpdateRequest request);

    public Boolean deleteProduct(Long productId);
}
