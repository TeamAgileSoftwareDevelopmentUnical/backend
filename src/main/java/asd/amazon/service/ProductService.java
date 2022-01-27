package asd.amazon.service;

import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.Batch;
import asd.amazon.entity.Product;
import asd.amazon.entity.enums.Type;
import asd.amazon.request.ProductQuantityCheckRequest;
import asd.amazon.request.ProductUpdateAvailabilityRequest;
import asd.amazon.request.ProductUpdateRequest;
import asd.amazon.request.ViewDetails;
import asd.amazon.responses.ProductQuantityCheckResponse;
import asd.amazon.responses.ProductResponse;
import asd.amazon.responses.ViewDetailsResponse;

import java.util.List;

public interface ProductService {

    public List<ProductResponse> getProductBy(Long sellerID);

    public List<ProductResponse> getProductsByCategory(String category);

    public ProductDTO create(ProductDTO productDTO);
  
    public Boolean uploadNewProduct(ProductDTO product);

    public ProductResponse getProductFromBatch(Long productId);

    public Boolean updateProduct(ProductUpdateRequest request);

    public Boolean updateAvailability(ProductUpdateAvailabilityRequest request);

    public Boolean deleteProduct(Long productId);

    public ViewDetailsResponse viewDetails (Long viewDetails);



    Boolean productQuantityCheck(Long productId);
}
