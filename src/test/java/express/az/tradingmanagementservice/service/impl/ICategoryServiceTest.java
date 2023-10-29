package express.az.tradingmanagementservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import express.az.tradingmanagementservice.mapper.CategoryMapper;
import express.az.tradingmanagementservice.model.dto.request.CategoryRequestDto;
import express.az.tradingmanagementservice.model.dto.response.CategoryResponseDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.entity.Category;
import express.az.tradingmanagementservice.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = {ICategoryService.class})
@ExtendWith(SpringExtension.class)
class ICategoryServiceTest {
    @MockBean
    private CategoryMapper categoryMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private ICategoryService iCategoryService;

    @Test
    void testSave() {

        Category category = Category.builder()
                .name("Apple")
                .build();
        when(categoryRepository.save(Mockito.any())).thenReturn(category);
        when(categoryMapper.categoryRequestDtoToCategory(Mockito.any())).thenReturn(category);

        GeneralResponse<String> actualSaveResult = iCategoryService.save(new CategoryRequestDto("Apple"));

        verify(categoryMapper).categoryRequestDtoToCategory(Mockito.any());
        verify(categoryRepository).save(Mockito.any());

        assertEquals("Apple", actualSaveResult.getData());
        assertEquals("Save successfully!", actualSaveResult.getMessage());
        assertEquals(HttpStatus.CREATED, actualSaveResult.getHttpStatus());
    }


    @Test
    void testFindAll() {
        Category category = Category.builder()
                .name("Apple")
                .build();

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(categoryRepository.findAll()).thenReturn(categoryList);
        when(categoryMapper.categoryListToCategoryResponseDtoList(Mockito.<List<Category>>any()))
                .thenReturn(new ArrayList<>());
        GeneralResponse<List<CategoryResponseDto>> actualFindAllResult = iCategoryService.findAll();
        verify(categoryMapper).categoryListToCategoryResponseDtoList(Mockito.<List<Category>>any());
        verify(categoryRepository).findAll();
        assertEquals("Categories were successfully printed!", actualFindAllResult.getMessage());
        assertEquals(HttpStatus.OK, actualFindAllResult.getHttpStatus());
        assertTrue(actualFindAllResult.getData().isEmpty());
    }
}

