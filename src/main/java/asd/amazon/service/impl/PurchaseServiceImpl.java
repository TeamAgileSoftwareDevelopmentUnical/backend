package asd.amazon.service.impl;

import asd.amazon.dto.PurchaseDTO;
import asd.amazon.entity.Product;
import asd.amazon.entity.Purchase;
import asd.amazon.entity.SellerAccount;
import asd.amazon.entity.enums.Type;
import asd.amazon.repository.BatchRepository;
import asd.amazon.repository.PurchaseRepository;
import asd.amazon.responses.ProductResponse;
import asd.amazon.responses.PurchaseResponse;
import asd.amazon.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    ProductServiceImpl productService;

    @Transactional
    @Override
    public List<PurchaseResponse> getPurchasesByCustomerId(Long customerID) {
        List<PurchaseResponse> responses = new ArrayList<>();
        purchaseRepository.findAllByCustomerIdOrderByDateDesc(customerID).forEach(purchase -> {
            responses.add(mapPurchaseToPurchaseResponse(purchase));
        });
        return responses;
    }

    private PurchaseResponse mapPurchaseToPurchaseResponse(Purchase purchase) {
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setProductName(purchase.getProduct().getName());
        purchaseResponse.setProductDescription(purchase.getProduct().getDescription());
        purchaseResponse.setProductPrice(purchase.getProduct().getBatch().getPrice());
        purchaseResponse.setProductQuantity(purchase.getProductQuantity());
        purchaseResponse.setTotalPrice(purchaseResponse.getProductPrice()*purchaseResponse.getProductQuantity());
        purchaseResponse.setSellerUsername(purchase.getProduct().getSellerAccounts().getUsername());
        purchaseResponse.setPurchaseDate(purchase.getDate());
        purchaseResponse.setPaymentMethod(purchase.getPaymentMethod());
        purchaseResponse.setShippingAddress(purchase.getShippingAddress());
        purchaseResponse.setPhoto(new String(purchase.getProduct().getPhoto()));
        return purchaseResponse;
    }

    @Override
    public Purchase createPurchase(PurchaseDTO purchaseDTO) {
        return mapPurchaseDTOToPurchase(purchaseDTO);
    }

    private Purchase mapPurchaseDTOToPurchase(PurchaseDTO purchaseDTO){
        Purchase response = new Purchase();
        response.setId(purchaseDTO.getId());
        response.setCustomer(purchaseDTO.getCustomer());
        response.setDate(purchaseDTO.getDate());
        response.setShippingAddress(purchaseDTO.getShippingAddress());
        response.setPaymentMethod(purchaseDTO.getPaymentMethod());
        return response;
    }
}
