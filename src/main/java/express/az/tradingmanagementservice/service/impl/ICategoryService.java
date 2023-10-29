package express.az.tradingmanagementservice.service.impl;

import express.az.tradingmanagementservice.exception.GeneralException;
import express.az.tradingmanagementservice.mapper.CategoryMapper;
import express.az.tradingmanagementservice.model.constant.Constants;
import express.az.tradingmanagementservice.model.dto.request.CategoryRequestDto;
import express.az.tradingmanagementservice.model.dto.response.CategoryResponseDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.entity.Category;
import express.az.tradingmanagementservice.model.enums.ExceptionsEnum;
import express.az.tradingmanagementservice.repository.CategoryRepository;
import express.az.tradingmanagementservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ICategoryService implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public GeneralResponse<String> save(CategoryRequestDto requestDto) {
        Category category = mapper.categoryRequestDtoToCategory(requestDto);
        repository.save(category);
        String name = category.getName();

        return GeneralResponse.of(Constants.SAVE_SUCCESSFULLY, HttpStatus.CREATED, name);
    }

    @Override
    public GeneralResponse<List<CategoryResponseDto>> findAll() {
        List<Category> categories = repository.findAll();
        categories.stream()
                .findAny()
                .orElseThrow(() -> new GeneralException(ExceptionsEnum.CATEGORY_NOT_FOUND));
        List<CategoryResponseDto> categoryResponse = mapper.categoryListToCategoryResponseDtoList(categories);

        return GeneralResponse.of(Constants.CATEGORIES_SHOW_SUCCESS,HttpStatus.OK, categoryResponse) ;
    }

    @Override
    public GeneralResponse<Category> findById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new GeneralException(ExceptionsEnum.CATEGORY_NOT_FOUND));

        return GeneralResponse.of(Constants.FIND_CATEGORY,HttpStatus.OK,category);
    }

}
