package com.prosigmaka.backendjavafinal.controller.products;

import com.prosigmaka.backendjavafinal.helper.GlobalHttpResponse;
import com.prosigmaka.backendjavafinal.model.product.ProductsModel;
import com.prosigmaka.backendjavafinal.model.product.ProductsResponseModel;
import com.prosigmaka.backendjavafinal.service.products.ProductsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos/api")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService service;

    @GetMapping("/listproduct")
    public ResponseEntity<GlobalHttpResponse<List<ProductsResponseModel>>> getProduct(
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(required = false) String title,
            @RequestParam(name = "sortBy", defaultValue = "id") @Pattern(regexp = "id|title|price", message = "invalid sortBy value") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "DESC") @Pattern(regexp = "asc|desc", message = "invalid sortOrder choice") String sortOrder){
        if(title == null){
            var entity = service.getAllProductCategory(categoryId, sortOrder, sortBy);
            return new ResponseEntity<>(entity, HttpStatusCode.valueOf(entity.getStatus()));
        }
        var product = service.getAllProductTitle(title, sortOrder, sortBy);
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(product.getStatus()));
    }
    @PostMapping("/addproduct")
    public ResponseEntity<GlobalHttpResponse<ProductsResponseModel>> addProduct(@Valid @RequestBody ProductsModel productsModel){
        var add = service.addProduct(productsModel);
        return new ResponseEntity<>(add, HttpStatusCode.valueOf(add.getStatus()));
    }
    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<GlobalHttpResponse<ProductsResponseModel>> updateProduct(@PathVariable("id") int id,
                                                                  @Valid @RequestBody ProductsModel productsModel){
        var update = service.updateProduct(id, productsModel);
        return new ResponseEntity<>(update, HttpStatusCode.valueOf(update.getStatus()));
    }
    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<GlobalHttpResponse<ProductsResponseModel>> deleteProduct(@PathVariable("id") int id){
        var delete = service.deleteProduct(id);
        return new ResponseEntity<>(delete, HttpStatusCode.valueOf(delete.getStatus()));
    }
    @GetMapping("/detailproduct/{id}")
    public ResponseEntity<GlobalHttpResponse<ProductsResponseModel>> getDetailProduct(@PathVariable("id") int id){
        var detail = service.detailProduct(id);
        return new ResponseEntity<>(detail, HttpStatusCode.valueOf(detail.getStatus()));
    }
}
