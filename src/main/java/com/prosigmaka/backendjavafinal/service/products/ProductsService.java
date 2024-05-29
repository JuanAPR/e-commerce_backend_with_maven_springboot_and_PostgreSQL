package com.prosigmaka.backendjavafinal.service.products;

import com.prosigmaka.backendjavafinal.entity.categories.CategoriesEntity;
import com.prosigmaka.backendjavafinal.entity.products.ProductsEntity;
import com.prosigmaka.backendjavafinal.helper.GlobalHttpResponse;
import com.prosigmaka.backendjavafinal.model.product.ProductsModel;
import com.prosigmaka.backendjavafinal.model.product.ProductsResponseModel;
import com.prosigmaka.backendjavafinal.repository.CategoriesRepo;
import com.prosigmaka.backendjavafinal.repository.ProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepo productsRepo;
    private final CategoriesRepo categoriesRepo;

    public GlobalHttpResponse<List<ProductsResponseModel>> getAllProductCategory(Integer id,
                                                                          String sortOrder, String sortBy){ //get
        try{
            if (id == null){
                Sort.Direction direction = Sort.Direction.fromString(sortOrder.toUpperCase());
                Sort sort = Sort.by(direction, sortBy);
                List<ProductsEntity> products = productsRepo.findAll(sort);
                List<ProductsResponseModel> model = new ArrayList<>();
                for(ProductsEntity data : products){
                    model.add(data.entityToModel());
                }
                return new GlobalHttpResponse<>(200,"ok",model);
            } else {
//                Sort.Direction direction = Sort.Direction.fromString(sortOrder.toUpperCase());
//                Sort sorts = Sort.by(direction, sortBy);
                List<ProductsEntity> products = productsRepo.findByCategoryId_Id(id);
                List<ProductsResponseModel> modelList = new ArrayList<>();
                for(ProductsEntity data : products){
                    modelList.add(data.entityToModel());
                }
                if(products.isEmpty()){
                    return new GlobalHttpResponse<>(404,"Product not found",modelList);
                }else{
                    return new GlobalHttpResponse<>(200,"Success",modelList);
                }
            }
        } catch (Exception e){
            return new GlobalHttpResponse<>(404,"Error",null);
        }
    }
    public GlobalHttpResponse<List<ProductsResponseModel>> getAllProductTitle(String title, String sortOrder, String sortBy){
        try{
            if(title == null){
                Sort.Direction direction = Sort.Direction.fromString(sortOrder.toUpperCase());
                Sort sort = Sort.by(direction, sortBy);
                List<ProductsEntity> products = productsRepo.findAll(sort);
                List<ProductsResponseModel> model = new ArrayList<>();
                for(ProductsEntity data : products){
                    model.add(data.entityToModel());
                }
                return new GlobalHttpResponse<>(200,"Success",model);
            } else {
                Sort.Direction direction = Sort.Direction.fromString(sortOrder.toUpperCase());
                Sort sorts = Sort.by(direction, sortBy);
                List<ProductsEntity> productsEntities = productsRepo.findByTitleContainingIgnoreCase(title,sorts);
                List<ProductsResponseModel> modelTitle = new ArrayList<>();
                for(ProductsEntity data : productsEntities){
                    modelTitle.add(data.entityToModel());
                }
                if(productsEntities.isEmpty()){
                    return new GlobalHttpResponse<>(404,"Data not found",modelTitle);
                }
                return new GlobalHttpResponse<>(200,"Success",modelTitle);
            }
        } catch (Exception e){
            return new GlobalHttpResponse<>(404,"Error",null);
        }
    }
    public GlobalHttpResponse<ProductsResponseModel> addProduct(ProductsModel productsModel){ //post
        try{
            ProductsEntity products = productsModel.dtoToEntity();
            CategoriesEntity categories = categoriesRepo.findById(productsModel.getCategoryId()).orElse(null);
            if(categories == null){
                //return new GlobalHttpResponse<>(404,"Data not found", new ProductsEntity().entityToModel());
                throw new NullPointerException();
            } else {
                products.setCategoryId(categories);
                ProductsEntity entity = productsRepo.save(products);
                return new GlobalHttpResponse<>(201,"success",entity.entityToModel());
            }

        }catch (NullPointerException e){
            return new GlobalHttpResponse<>(404,"Data not found", new ProductsEntity().entityToModel());
        }
    }
    public GlobalHttpResponse<ProductsResponseModel> updateProduct(int id, ProductsModel productsModel){ //put
        Optional<ProductsEntity> products = productsRepo.findById(id);
        if(products.isEmpty()){
            return new GlobalHttpResponse<>(404,"Product not found",new ProductsEntity().entityToModel());

        }
        CategoriesEntity categories = categoriesRepo.findById(productsModel.getCategoryId()).orElse(null);
        if(categories == null){
            return new GlobalHttpResponse<>(404,"Category not found",new ProductsEntity().entityToModel());
        }
        ProductsEntity entity = products.get();
        entity.setTitle(productsModel.getTitle());
        entity.setImage(productsModel.getImage());
        entity.setPrice(productsModel.getPrice());
        entity.setCategoryId(categories);
        ProductsEntity result = productsRepo.save(entity);
        return new GlobalHttpResponse<>(200,"Success",result.entityToModel());
    }
    public GlobalHttpResponse<ProductsResponseModel> deleteProduct(int id){ //delete
        Optional<ProductsEntity> products = productsRepo.findById(id);
        if(products.isEmpty()){
            return new GlobalHttpResponse<>(404,"Product not found",new ProductsEntity().entityToModel());
        }
        ProductsEntity entity = products.get();
        productsRepo.deleteById(id);
        return new GlobalHttpResponse<>(200,"Success",null);
    }

    public GlobalHttpResponse<ProductsResponseModel> detailProduct(int id){
        Optional<ProductsEntity> products = productsRepo.findById(id);
        if(products.isEmpty()){
            return new GlobalHttpResponse<>(404,"Product not found",null);
        } else {
            ProductsResponseModel model = products.get().entityToModel();
            return new GlobalHttpResponse<>(200,"Success",model);
        }
    }
}
