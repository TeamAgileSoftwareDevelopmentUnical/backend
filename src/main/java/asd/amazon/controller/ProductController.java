package asd.amazon.controller;

import asd.amazon.dto.ProductDTO;
import asd.amazon.entity.Batch;
import asd.amazon.entity.Product;
import asd.amazon.request.ProductUpdateRequest;
import asd.amazon.responses.ProductResponse;
import asd.amazon.service.BatchService;
import asd.amazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductController.ROOT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    public static final String ROOT = "/product";

    public static final String CREATE = "/create";

    public static final String UPDATE = "/update";

    public static final String GETALL = "/get-all";


    public static final String UPLOAD = "/upload-product";

    @Autowired
    private ProductService productService;
    @Autowired
    private BatchService batchService;

    @GetMapping(GETALL)
    public ResponseEntity<List<ProductDTO>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping(UPLOAD)
    public Boolean uploadNewProduct(@RequestBody ProductDTO product){
        return productService.uploadNewProduct(product);
    }

    @GetMapping(value = "/get-product-by")
    public ResponseEntity<ProductResponse> getProductBy(@RequestParam(name = "id") Long id){
       return ResponseEntity.ok(productService.getProductFromBatch(id));
    }

    @PatchMapping(value = "/update-product")
    public Boolean updateProduct(@RequestParam(name = "id")Long Id, @RequestBody ProductUpdateRequest request){
        return productService.updateProduct(Id,request);
    }

    @DeleteMapping(value = "/delete-product")
    public Boolean deleteProduct(@RequestParam(name = "id") Long id){
        return productService.deleteProduct(id);
    }
}
