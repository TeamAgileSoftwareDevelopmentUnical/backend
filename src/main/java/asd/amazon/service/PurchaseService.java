package asd.amazon.service;

import asd.amazon.dto.PurchaseDTO;
import asd.amazon.entity.Purchase;
import asd.amazon.responses.PurchaseResponse;

import java.util.List;

public interface PurchaseService {
    public List<PurchaseResponse> getPurchasesByCustomerId(Long customerID);
    public Purchase createPurchase(PurchaseDTO purchaseDTO);
}
