package express.az.tradingmanagementservice.service;

import express.az.tradingmanagementservice.model.dto.request.ProductRequestDto;
import express.az.tradingmanagementservice.model.dto.request.ProductRequestUpdateDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.ProductResponseDto;
import java.util.List;

public interface ProductService {
    GeneralResponse<String> save(ProductRequestDto requestDto);
    GeneralResponse<List<ProductResponseDto>> findAll();
    GeneralResponse<ProductResponseDto> findById(Long id);
    GeneralResponse<ProductResponseDto> update(Long id, ProductRequestUpdateDto requestDto);
    GeneralResponse<ProductResponseDto> delete(Long id);

}
