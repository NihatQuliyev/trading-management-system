package express.az.tradingmanagementservice.service;

import express.az.tradingmanagementservice.model.dto.request.CategoryRequestDto;
import express.az.tradingmanagementservice.model.dto.response.CategoryResponseDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.entity.Category;

import java.util.List;

public interface CategoryService {

    GeneralResponse<String> save(CategoryRequestDto requestDto);
    GeneralResponse<List<CategoryResponseDto>> findAll();

    GeneralResponse<Category> findById(Long id);

}
