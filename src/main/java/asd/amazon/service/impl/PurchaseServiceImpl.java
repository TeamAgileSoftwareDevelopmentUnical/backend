package asd.amazon.service.impl;

import asd.amazon.dto.PurchaseDTO;
import asd.amazon.entity.Product;
import asd.amazon.entity.Purchase;
import asd.amazon.entity.SellerAccount;
import asd.amazon.entity.enums.Type;
import asd.amazon.repository.BatchRepository;
import asd.amazon.repository.PurchaseRepository;
import asd.amazon.responses.ProductResponse;
import asd.amazon.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    BatchRepository batchRepository;

    @Override
    public List<PurchaseDTO> getPurchasesByCustomerId(Long customerID) {
        List<PurchaseDTO> responses = new ArrayList<>();
        System.out.println("get purchases in backend of the customer: "+customerID);
        purchaseRepository.findAllByCustomerIdOrderByDateDesc(customerID).forEach(purchase -> {
            responses.add(mapPurchaseToPurchaseDTO(purchase));
        });
        System.out.println(responses);
        return responses;
    }

    private Float getProductPrice(Purchase purchase)
    {
        return batchRepository.findBatchByProduct(purchase.getProduct().getId()).getPrice();
    }

    private PurchaseDTO mapPurchaseToPurchaseDTO(Purchase purchase){
        PurchaseDTO response = new PurchaseDTO();
        response.setId(purchase.getId());
        response.setCustomer(purchase.getCustomer());
        response.setDate(purchase.getDate());
        response.setShippingAddress(purchase.getShippingAddress());
        response.setPaymentMethod(purchase.getPaymentMethod());
        response.setTotal(purchase.getProductQuantity()*getProductPrice(purchase));
        return response;
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
