package asd.amazon.service.impl;


import asd.amazon.dto.BatchDTO;
import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.Batch;
import asd.amazon.entity.Product;
import asd.amazon.entity.Purchase;
import asd.amazon.entity.SellerAccount;
import asd.amazon.repository.BatchRepository;
import asd.amazon.repository.ProductRepository;
import asd.amazon.repository.SellerAccountRepository;
import asd.amazon.request.ProductUpdateRequest;
import asd.amazon.responses.BatchResponse;
import asd.amazon.responses.ProductResponse;
import asd.amazon.responses.SellerResponse;
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
    @Autowired
    private SellerAccountRepository sellerAccountRepository;
    @Autowired
    private BatchRepository batchRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAll() {
        return mapProducts(productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean uploadNewProduct(ProductDTO productDTO) {
        Batch batch = new Batch();
        batch.setPrice(productDTO.getPrice());
        batch.setAvailableQuantity(productDTO.getAvailableQuantity());
        batch.setSeller(sellerAccountRepository.getById(productDTO.getSellerID()));
        Batch savedBatch = batchRepository.save(batch);

        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setType(productDTO.getType());
        product.setName(productDTO.getName());
        product.setBatch(savedBatch);
        productRepository.save(product);
        return true;
    }

    @Override
    public ProductResponse getProductFromBatch(Long productId) {
        Product product = productRepository.getById(productId);

        ProductResponse response = new ProductResponse();
        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setProductDesc(product.getDescription());
        response.setType(product.getType());
        response.setBatch(setBatch(product));
        response.setSeller(setSellerInfo(product));

        return response;
    }

    @Override
    public Boolean updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.getById(id);
        if (product!=null){
            Batch batch = product.getBatch();
            batch.setAvailableQuantity(request.getProductQuantity());
            batch.setPrice(request.getProductPrice());
            batchRepository.save(batch);

            product.setName(request.getProductName());
            product.setDescription(request.getProductDescription());

            productRepository.save(product);

            return true;
        }

        return false;
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        Product product = productRepository.getById(productId);
        if(product != null){
            batchRepository.delete(product.getBatch());
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    private SellerResponse setSellerInfo(Product product) {
        SellerResponse sellerResponse = new SellerResponse();
        sellerResponse.setSellerID(product.getBatch().getSeller().getId());
        sellerResponse.setUsername(product.getBatch().getSeller().getUsername());
        sellerResponse.setName(product.getBatch().getSeller().getName());
        sellerResponse.setSurname(product.getBatch().getSeller().getSurname());
        return sellerResponse;
    }

    private BatchResponse setBatch(Product product) {
        BatchResponse batch = new BatchResponse();
        batch.setBatchID(product.getBatch().getId());
        batch.setProductPrice(product.getBatch().getPrice());
        batch.setAvailableQuantity(product.getBatch().getAvailableQuantity());
        return batch;
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
