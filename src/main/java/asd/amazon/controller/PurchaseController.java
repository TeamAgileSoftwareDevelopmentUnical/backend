package asd.amazon.controller;

import asd.amazon.dto.PurchaseDTO;
import asd.amazon.entity.Purchase;
import asd.amazon.responses.ProductResponse;
import asd.amazon.responses.PurchaseResponse;
import asd.amazon.service.PurchaseService;
import asd.amazon.utils.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.PATCH,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
@RestController
@RequestMapping(CommonConstant.PURCHASES_ROOT)
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping(CommonConstant.PURCHASES_GET)
    public ResponseEntity<List<PurchaseResponse>> getPurchases(@RequestParam(value = "customer_id")Long customer){
       return ResponseEntity.ok().body(purchaseService.getPurchasesByCustomerId(customer));
    }
    @PostMapping(CommonConstant.PURCHASES_CREATE)
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseDTO purchaseDTO)  {
        return ResponseEntity.ok().body(purchaseService.createPurchase(purchaseDTO));
    }
}
