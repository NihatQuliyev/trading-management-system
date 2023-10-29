package express.az.tradingmanagementservice.service.impl;

import express.az.tradingmanagementservice.helper.ProductServiceHelper;
import express.az.tradingmanagementservice.mapper.ProductMapper;
import express.az.tradingmanagementservice.model.constant.Constants;
import express.az.tradingmanagementservice.model.dto.request.ProductRequestDto;
import express.az.tradingmanagementservice.model.dto.request.ProductRequestUpdateDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.ProductResponseDto;
import express.az.tradingmanagementservice.model.entity.Product;
import express.az.tradingmanagementservice.model.enums.ExceptionsEnum;
import express.az.tradingmanagementservice.repository.ProductRepository;
import express.az.tradingmanagementservice.service.CategoryService;
import express.az.tradingmanagementservice.service.ProductService;
import express.az.tradingmanagementservice.exception.GeneralException;
import express.az.tradingmanagementservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IProductService implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    private final ProductServiceHelper helper ;


    @Override
    public GeneralResponse<String> save(ProductRequestDto requestDto) {
        Product product = mapper.productRequestDtoToProduct(requestDto);
        categoryService.findById(requestDto.getCategoryId());
        supplierService.findById(requestDto.getSupplierId());
        repository.save(product);
        String productName = product.getName();

        return GeneralResponse.of(Constants.SAVE_SUCCESSFULLY, HttpStatus.CREATED,productName);
    }

    @Override
    public GeneralResponse<List<ProductResponseDto>> findAll() {
        List<Product> products = repository.findAll();
        products.stream()
                .findAny()
                .orElseThrow(() -> new GeneralException(ExceptionsEnum.PRODUCT_NOT_FOUND));
        List<ProductResponseDto> productResponse = mapper.productListToProductResponseDtoList(products);

        return GeneralResponse.of(Constants.PRODUCT_SHOW_SUCCESS,HttpStatus.OK, productResponse) ;
    }

    @Override
    public GeneralResponse<ProductResponseDto> findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new GeneralException(ExceptionsEnum.PRODUCT_NOT_FOUND));
        ProductResponseDto responseDto = mapper.productToProductResponseDto(product);

        return GeneralResponse.of(Constants.FIND_PRODUCT,HttpStatus.OK,responseDto);

    }

    @Override
    public GeneralResponse<ProductResponseDto> update(Long id, ProductRequestUpdateDto updateProduct) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new GeneralException(ExceptionsEnum.PRODUCT_NOT_FOUND));
        helper.checkingUpdate(updateProduct,existingProduct);
        repository.save(existingProduct);
        ProductResponseDto productResponseDto = mapper.productToProductResponseDto(existingProduct);

        return GeneralResponse.of(Constants.UPDATE_SUCCESSFULLY,HttpStatus.OK,productResponseDto);
    }

    @Override
    public GeneralResponse<ProductResponseDto> delete(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new GeneralException(ExceptionsEnum.PRODUCT_NOT_FOUND));
        repository.deleteById(id);
        ProductResponseDto productResponseDto = mapper.productToProductResponseDto(product);

        return GeneralResponse.of(Constants.DELETE_BY_ID_SUCCESSFULLY,HttpStatus.OK,productResponseDto);
    }
}
