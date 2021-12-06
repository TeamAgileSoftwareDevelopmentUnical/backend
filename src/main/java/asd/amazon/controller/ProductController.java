package asd.amazon.controller;

import asd.amazon.dto.ProductDTO;
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

    public static final String GETALL = "/get-all";

    @Autowired
    private ProductService productService;

    @GetMapping(GETALL)
    public ResponseEntity<List<ProductDTO>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping(CREATE)
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.create(productDTO));
    }


}
