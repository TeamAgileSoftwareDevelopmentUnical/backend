package asd.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import asd.amazon.dto.PurchaseDTO;
import asd.amazon.entity.Purchase;
import asd.amazon.service.PurchaseService;
import asd.amazon.utils.CommonConstant;

@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.PATCH,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
@RestController
@RequestMapping(CommonConstant.PURCHASES_ROOT)
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping(CommonConstant.PURCHASES)
    public ResponseEntity<List<PurchaseDTO>> getPurchases(@RequestParam(value = "customer_id")Long customer){
        System.out.println("get mapping purchases");
        return ResponseEntity.ok().body(purchaseService.getPurchasesByCustomerId(customer));
    }
    @PostMapping(CommonConstant.PURCHASES_CREATE)
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseDTO purchaseDTO)  {
        return ResponseEntity.ok().body(purchaseService.createPurchase(purchaseDTO));
    }
}
