package express.az.tradingmanagementservice.helper;

import express.az.tradingmanagementservice.model.dto.request.ProductRequestUpdateDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.entity.Category;
import express.az.tradingmanagementservice.model.entity.Product;
import express.az.tradingmanagementservice.model.entity.Supplier;
import express.az.tradingmanagementservice.service.CategoryService;
import express.az.tradingmanagementservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceHelper {

    @Autowired
    private  CategoryService categoryService;

    @Autowired
    private  SupplierService supplierService;

    public  void checkingUpdate(ProductRequestUpdateDto updateProduct, Product existingProduct) {
        if (updateProduct.getCategoryId() != null){
            GeneralResponse<Category> checkCategory = categoryService.findById(updateProduct.getCategoryId());
            existingProduct.setCategory(checkCategory.getData());
        }
        if (updateProduct.getSupplierId() != null){
            GeneralResponse<Supplier> checkSupplier = supplierService.findById(updateProduct.getSupplierId());
            existingProduct.setSupplier(checkSupplier.getData());
        }
        existingProduct.setName(updateProduct.getName() != null ? updateProduct.getName() : existingProduct.getName());
        existingProduct.setDescription(updateProduct.getDescription() != null ? updateProduct.getDescription() : existingProduct.getDescription());
        existingProduct.setPrice(updateProduct.getPrice() != null ? updateProduct.getPrice() : existingProduct.getPrice());
    }
}
