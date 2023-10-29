package express.az.tradingmanagementservice.mapper;

import express.az.tradingmanagementservice.model.dto.request.ProductRequestDto;
import express.az.tradingmanagementservice.model.dto.response.ProductResponseDto;
import express.az.tradingmanagementservice.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(target = "id",ignore = true)
    Product productRequestDtoToProduct(ProductRequestDto requestDto);
    List<ProductResponseDto> productListToProductResponseDtoList(List<Product> productList);
    ProductResponseDto productToProductResponseDto(Product product);

}
