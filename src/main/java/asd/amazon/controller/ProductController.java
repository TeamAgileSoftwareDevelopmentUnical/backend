package asd.amazon.controller;

import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.Batch;
import asd.amazon.entity.Product;
import asd.amazon.request.ProductUpdateRequest;
import asd.amazon.responses.ProductResponse;
import asd.amazon.service.BatchService;
import asd.amazon.service.ProductService;
import asd.amazon.utils.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping(CommonConstant.PRODUCT_ROOT)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(CommonConstant.GET_ALL_PRODUCT)
    public ResponseEntity<List<ProductResponse>> getAllProductBy(@RequestParam(value = "seller_id")Long seller_id){
        return ResponseEntity.ok().body(productService.getProductBy(seller_id));
    }

    @PostMapping(CommonConstant.PRODUCT_UPLOAD)
    public ResponseEntity<Boolean> uploadNewProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok().body(productService.uploadNewProduct(product));
    }

    @GetMapping(CommonConstant.GET_PRODUCT)
    public ResponseEntity<ProductResponse> getProductBy(@RequestParam(name = "product_id") Long product_id){
        return ResponseEntity.ok().body(productService.getProductFromBatch(product_id));
    }

    @PatchMapping(CommonConstant.PRODUCT_UPDATE)
    public ResponseEntity<Boolean> updateProduct(@RequestParam(name = "id")Long Id, @RequestBody ProductUpdateRequest request){
        return ResponseEntity.ok().body(productService.updateProduct(Id,request));
    }

    @DeleteMapping(CommonConstant.PRODUCT_DELETE)
    public ResponseEntity<Boolean> deleteProduct(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }
}
