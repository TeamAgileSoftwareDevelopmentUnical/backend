package asd.amazon.service.impl;


import asd.amazon.dto.BatchDTO;
import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.*;
import asd.amazon.entity.enums.Type;
import asd.amazon.repository.BatchRepository;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.repository.ProductRepository;
import asd.amazon.repository.SellerAccountRepository;
import asd.amazon.request.ProductQuantityCheckRequest;
import asd.amazon.request.ProductUpdateAvailabilityRequest;
import asd.amazon.request.ProductUpdateRequest;
import asd.amazon.request.ViewDetails;
import asd.amazon.responses.*;
import asd.amazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Transactional
    @Override
    public List<ProductResponse> getProductBy(Long sellerID) {
        List<ProductResponse> responses = new ArrayList<>();
        SellerAccount seller = sellerAccountRepository.getById(sellerID);
        seller.getProduct().forEach(product -> {
            responses.add(mapProduct(product));
        });
        return responses;
    }

    @Transactional
    @Override
    public List<ProductResponse> getProductsByCategory(String category) {
        List<ProductResponse> responses = new ArrayList<>();
        productRepository.findAllByType(Type.valueOf(category)).forEach(product -> {
            responses.add(mapProduct(product));
        });
        return responses;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean uploadNewProduct(ProductDTO productDTO) {
        Batch batch = new Batch();
        batch.setPrice(productDTO.getPrice());
        batch.setAvailableQuantity(productDTO.getAvailableQuantity());
        Batch newBatch = batchRepository.save(batch);

        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setType(productDTO.getType());
        product.setName(productDTO.getName());
        product.setBatch(newBatch);
        if(productDTO.getPhoto()!=null){
            product.setPhoto(productDTO.getPhoto().getBytes(StandardCharsets.UTF_8));
        }
        product.setSellerAccounts(sellerAccountRepository.getById(productDTO.getSellerID()));
        productRepository.save(product);

        return true;
    }



    @Transactional
    @Override
    public ProductResponse getProductFromBatch(Long productID) {
        Product product = productRepository.getById(productID);
        return product.getId()>0?mapProduct(product):null;
    }

    @Transactional
    @Override
    public Boolean updateProduct(ProductUpdateRequest request) {
        Product product = productRepository.getById(request.getProductID());
        if (product.getId() > 0){
            product.setName(request.getProductName());
            product.setDescription(request.getProductDescription());
            if(request.getPhoto()!=null)
                product.setPhoto(request.getPhoto().getBytes(StandardCharsets.UTF_8));
            //product.setPhoto(request.getPhoto().getBytes());
            Product updatedProduct = productRepository.save(product);

            Batch batch = product.getBatch();
            batch.setAvailableQuantity(request.getProductQuantity());
            batch.setPrice(request.getProductPrice());
            batch.setProduct(updatedProduct);
            batchRepository.save(batch);

            return true;
        }

        return false;
    }

    @Transactional
    @Override
    public Boolean updateAvailability(ProductUpdateAvailabilityRequest request) {
        System.out.println("_________________entro qui_________________");

        Product product = productRepository.getById(request.getProductId());
        Float newAvailability = request.getAvailability();

        System.out.println(product.getId());
        System.out.println(product.getBatch().getId());

        if (product != null){
            Batch batch = product.getBatch();
            batch.setAvailableQuantity(newAvailability);
            batchRepository.save(batch);

            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean deleteProduct(Long productId) {
        Product product = productRepository.getById(productId);
        if (product.getId()>0){
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public ViewDetailsResponse viewDetails(Long viewDetails) {
        Product product = productRepository.getById(viewDetails);
        if (product.getId()>0){
            ViewDetailsResponse response= new ViewDetailsResponse();
            response.setProductName(product.getName());
            response.setProductId(product.getId());
            response.setProductDesc(product.getDescription());
            response.setProductQuantity(product.getBatch().getAvailableQuantity());
            return response;
        }
       return null;
    }


    @Transactional
    @Override
    public PaymentPreProcess productQuantityCheck(Long id, int quantity, Long customerID) {
        PaymentPreProcess response = new PaymentPreProcess();
        // check customer shipping address
        CustomerAccount account = customerAccountRepository.findCustomerAccountsById(customerID);
        if (account.getShippingAddress() == null || account.getShippingAddress().isEmpty()){
            response.setStatus(false);
            response.setMessage("Shipping address not found!\nUpdate your shipping address in your profile!");
            return response;
        }
        //check product quantity
        Product product = productRepository.getById(id);
        if((product.getBatch().getAvailableQuantity()-quantity)<0){
            response.setStatus(false);
            response.setMessage("Product out of stock");
            return response;
        }

        response.setMessage("Product ["+product.getName()+"] is Ready for payment");
        response.setStatus(true);
        return response;
    }

    private SellerResponse setSellerInfo(SellerAccount seller) {
        SellerResponse sellerResponse = new SellerResponse();
        sellerResponse.setSellerID(seller.getId());
        sellerResponse.setUsername(seller.getUsername());
        sellerResponse.setName(seller.getName());
        sellerResponse.setSurname(seller.getSurname());
        return sellerResponse;
    }

    private BatchResponse setBatch(Batch batch) {
        BatchResponse response = new BatchResponse();
        response.setBatchID(batch.getId());
        response.setProductPrice(batch.getPrice());
        response.setAvailableQuantity(batch.getAvailableQuantity());
        return response;
    }

    public ProductResponse mapProduct(Product product){//TODO: do map
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setProductDesc(product.getDescription());
        response.setType(product.getType());
        if(product.getPhoto() != null){
            response.setPhoto(new String(product.getPhoto()));
        }
        response.setBatch(setBatch(product.getBatch()));
        response.setSeller(setSellerInfo(product.getSellerAccounts()));
        return response;
    }
}