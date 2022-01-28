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

import asd.amazon.dto.ProductDTO;
import asd.amazon.request.ProductUpdateAvailabilityRequest;
import asd.amazon.request.ProductUpdateRequest;
import asd.amazon.responses.ProductResponse;
import asd.amazon.responses.ViewDetailsResponse;
import asd.amazon.service.ProductService;
import asd.amazon.utils.CommonConstant;

@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.PATCH,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
@RestController
@RequestMapping(CommonConstant.PRODUCT_ROOT)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(CommonConstant.GET_ALL_PRODUCT)
    public ResponseEntity<List<ProductResponse>> getAllProductBy(@RequestParam(value = "seller_id")Long sellerID){
        return ResponseEntity.ok().body(productService.getProductBy(sellerID));
    }

    @GetMapping(CommonConstant.GET_STAND_PRODUCTS)
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@RequestParam(value = "category")String category){
        return ResponseEntity.ok().body(productService.getProductsByCategory(category));
    }

    @PostMapping(CommonConstant.PRODUCT_UPLOAD)
    public ResponseEntity<Boolean> uploadNewProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok().body(productService.uploadNewProduct(product));
    }

    @GetMapping(CommonConstant.GET_PRODUCT)
    public ResponseEntity<ProductResponse> getProductBy(@RequestParam(name = "product_id") Long productID){
        return ResponseEntity.ok().body(productService.getProductFromBatch(productID));
    }

    @PostMapping(CommonConstant.PRODUCT_UPDATE)
    public ResponseEntity<Boolean> updateProduct(@RequestBody ProductUpdateRequest request){
        return ResponseEntity.ok().body(productService.updateProduct(request));
    }

    @PostMapping(CommonConstant.PRODUCT_UPDATE_AVAILABILITY)
    public ResponseEntity<Boolean> updateAvailability(@RequestBody ProductUpdateAvailabilityRequest request){
        return ResponseEntity.ok().body(productService.updateAvailability(request));
    }

    @GetMapping(CommonConstant.PRODUCT_DELETE)
    public ResponseEntity<Boolean> deleteProduct(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }
    @GetMapping(CommonConstant.PRODUCT_ViewDetails)
    public ResponseEntity<ViewDetailsResponse>viewDetailsResponseResponseEntity(@RequestParam(name = "product_id") Long productID){
        return ResponseEntity.ok().body(productService.viewDetails(productID));
    }

    @GetMapping(CommonConstant.PRODUCT_QUANTITY_CHECK)
    public ResponseEntity<Boolean> checkProductQuantity(@RequestParam(name = "product_id") Long id){
        return ResponseEntity.ok().body(productService.productQuantityCheck(id));
    }
}
