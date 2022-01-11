package asd.amazon.service;

import asd.amazon.dto.PurchaseDTO;
import asd.amazon.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    public List<PurchaseDTO> getPurchasesByCustomerId(Long customerID);
}
