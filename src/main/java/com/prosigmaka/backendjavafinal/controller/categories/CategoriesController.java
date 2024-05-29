package com.prosigmaka.backendjavafinal.controller.categories;

import com.prosigmaka.backendjavafinal.helper.GlobalHttpResponse;
import com.prosigmaka.backendjavafinal.model.categories.CategoriesModel;
import com.prosigmaka.backendjavafinal.model.categories.CategoriesResponseModel;
import com.prosigmaka.backendjavafinal.service.categories.CategoriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pos/api")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService service;

    @GetMapping("/listcategories")
    public ResponseEntity<GlobalHttpResponse<List<CategoriesResponseModel>>> getAll(){
        var entity = service.findAll();
        return new ResponseEntity<>(entity, HttpStatusCode.valueOf(entity.getStatus()));
    }

    @GetMapping("/listcategories/{id}")
    public ResponseEntity<GlobalHttpResponse<CategoriesResponseModel>> getById(@PathVariable("id") int id){
        var entity = service.findById(id);
        return new ResponseEntity<>(entity, HttpStatusCode.valueOf(entity.getStatus()));
    }
    @PostMapping("/addcategories")
    public ResponseEntity<GlobalHttpResponse<CategoriesResponseModel>> insertData(@Valid @RequestBody CategoriesModel categoriesModel){
        var entity = service.saveCategory(categoriesModel);
        return new ResponseEntity<>(entity, HttpStatusCode.valueOf(entity.getStatus()));
    }
    @PutMapping("/updatecategories/{id}")
    public ResponseEntity<GlobalHttpResponse<CategoriesResponseModel>> updateData(@PathVariable("id") int id,
                                                           @Valid @RequestBody CategoriesModel categoriesModel){
        var entity = service.updateById(id, categoriesModel);
        return new ResponseEntity<>(entity, HttpStatusCode.valueOf(entity.getStatus()));
    }
    @DeleteMapping("/deletecategories/{id}")
    public ResponseEntity<GlobalHttpResponse<CategoriesResponseModel>> delete(@PathVariable("id") int id){
        var entity = service.deleteCategory(id);
        return new ResponseEntity<>(entity, HttpStatusCode.valueOf(entity.getStatus()));
    }

}
