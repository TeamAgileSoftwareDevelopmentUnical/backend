package asd.amazon.service;

import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.Batch;
import asd.amazon.entity.Product;
import asd.amazon.request.ProductUpdateRequest;
import asd.amazon.responses.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getProductBy(Long sellerID);

    Boolean uploadNewProduct(ProductDTO product);

    ProductResponse getProductFromBatch(Long productId);

    Boolean updateProduct(Long id, ProductUpdateRequest request);

    Boolean deleteProduct(Long productId);
}
