package com.prosigmaka.backendjavafinal.service.categories;

import com.prosigmaka.backendjavafinal.entity.categories.CategoriesEntity;
import com.prosigmaka.backendjavafinal.helper.GlobalHttpResponse;
import com.prosigmaka.backendjavafinal.model.categories.CategoriesModel;
import com.prosigmaka.backendjavafinal.model.categories.CategoriesResponseModel;
import com.prosigmaka.backendjavafinal.repository.CategoriesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepo categoriesRepo;

    public GlobalHttpResponse<List<CategoriesResponseModel>> findAll(){
        List<CategoriesEntity> categories = categoriesRepo.findAll();
        List<CategoriesResponseModel> models = new ArrayList<>();
        for(CategoriesEntity data : categories){
            models.add(data.entityToModel());
        }
        return new GlobalHttpResponse<>(200, "Success", models);
    }
    public GlobalHttpResponse<CategoriesResponseModel> findById(int id){
        Optional<CategoriesEntity> categories = categoriesRepo.findById(id);
        if(categories.isEmpty()) {
            return new GlobalHttpResponse<>(404, "Data not found", null);
        } else {
            CategoriesResponseModel model = categories.get().entityToModel();
            return new GlobalHttpResponse<>(200,"Success", model);
        }
    }
    public GlobalHttpResponse<CategoriesResponseModel> saveCategory(CategoriesModel categories){
        CategoriesEntity save = categoriesRepo.save(categories.dtoToEntity());
        return new GlobalHttpResponse<>(201,"Success",save.entityToModel());
    }
    public GlobalHttpResponse<CategoriesResponseModel> updateById(int id, CategoriesModel categories){
        Optional<CategoriesEntity> entity = categoriesRepo.findById(id);
        if(entity.isEmpty()){
            return new GlobalHttpResponse<>(404,"Data not found", new CategoriesEntity().entityToModel());
        }else{
            CategoriesEntity categoriesEntity = entity.get();
            categoriesEntity.setName(categories.getName());
            categoriesRepo.save(categoriesEntity);
            return new GlobalHttpResponse<>(200, "Success",categoriesEntity.entityToModel());
        }
    }
    public GlobalHttpResponse<CategoriesResponseModel> deleteCategory(int id){
        Optional<CategoriesEntity> entity = categoriesRepo.findById(id);
        if(entity.isEmpty()){
            return new GlobalHttpResponse<>(404,"Data not found",new CategoriesEntity().entityToModel());
        } else{
            categoriesRepo.deleteById(id);
            return new GlobalHttpResponse<>(200,"Success",new CategoriesEntity().entityToModel());
        }

    }
}
