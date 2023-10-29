package express.az.tradingmanagementservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import express.az.tradingmanagementservice.model.dto.request.CategoryRequestDto;
import express.az.tradingmanagementservice.model.dto.response.CategoryResponseDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.service.CategoryService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Test
    void testRegistration() {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("Apple");

        when(categoryService.save(any())).thenReturn(
                GeneralResponse.<String>builder()
                        .data("Apple")
                        .httpStatus(HttpStatus.CONTINUE)
                        .message("Success")
                        .responseTime(LocalDate.of(2023, 10, 27).atStartOfDay())
                        .build()
        );

        CategoryController categoryController = new CategoryController(categoryService);

        GeneralResponse<String> result = categoryController.registration(categoryRequestDto);

        assertEquals("Apple", result.getData());
        assertEquals(HttpStatus.CONTINUE, result.getHttpStatus());
        assertEquals("Success", result.getMessage());
        assertEquals(LocalDate.of(2023, 10, 27).atStartOfDay(), result.getResponseTime());
    }


    @Test
    void testFindAll() {

        when(categoryService.findAll()).thenReturn(
                GeneralResponse.<List<CategoryResponseDto>>builder()
                        .data(new ArrayList<>())
                        .httpStatus(HttpStatus.CONTINUE)
                        .message("Success")
                        .responseTime(LocalDate.of(2023, 10, 27).atStartOfDay())
                        .build()
        );

        CategoryController categoryController = new CategoryController(categoryService);

        GeneralResponse<List<CategoryResponseDto>> result = categoryController.findAll();

        assertEquals(new ArrayList<>(), result.getData());
        assertEquals(HttpStatus.CONTINUE, result.getHttpStatus());
        assertEquals("Success", result.getMessage());
        assertEquals(LocalDate.of(2023, 10, 27).atStartOfDay(), result.getResponseTime());
    }

}
