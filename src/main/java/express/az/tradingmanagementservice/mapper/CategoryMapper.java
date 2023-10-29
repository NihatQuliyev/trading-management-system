package express.az.tradingmanagementservice.mapper;

import express.az.tradingmanagementservice.model.dto.request.CategoryRequestDto;
import express.az.tradingmanagementservice.model.dto.response.CategoryResponseDto;
import express.az.tradingmanagementservice.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id",ignore = true)
    Category categoryRequestDtoToCategory(CategoryRequestDto requestDto);
    CategoryResponseDto categoryResponseDtoToCategory(Category category);
    List<CategoryResponseDto> categoryListToCategoryResponseDtoList(List<Category> category);


}
