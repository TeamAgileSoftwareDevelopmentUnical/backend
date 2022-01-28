package asd.amazon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import asd.amazon.dto.PurchaseDTO;
import asd.amazon.entity.Purchase;
import asd.amazon.repository.BatchRepository;
import asd.amazon.repository.PurchaseRepository;
import asd.amazon.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    BatchRepository batchRepository;

    @Transactional
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

    @Override
    public Purchase createPurchase(PurchaseDTO purchaseDTO) {
        return mapPurchaseDTOToPurchase(purchaseDTO);
    }

    private Float getProductPrice(Purchase purchase)
    {
        return batchRepository.findBatchByProduct(purchase.getProduct().getId()).getPrice();
    }

    private PurchaseDTO mapPurchaseToPurchaseDTO(Purchase purchase){
        PurchaseDTO response = new PurchaseDTO();
        response.setId(purchase.getId());
        // FIXME: purchase.getCustomer()
        response.setCustomer(purchase.getCustomer());
        response.setDate(purchase.getDate());
        response.setShippingAddress(purchase.getShippingAddress());
        response.setPaymentMethod(purchase.getPaymentMethod());
        // FIXME: total
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
