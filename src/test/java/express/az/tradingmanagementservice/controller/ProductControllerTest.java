package express.az.tradingmanagementservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import express.az.tradingmanagementservice.model.dto.request.ProductRequestDto;
import express.az.tradingmanagementservice.model.dto.request.ProductRequestUpdateDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.ProductResponseDto;
import express.az.tradingmanagementservice.model.entity.Category;
import express.az.tradingmanagementservice.model.entity.Supplier;
import express.az.tradingmanagementservice.service.ProductService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {


    @MockBean
    private ProductService productService;


    @Test
    void testRegistration() {

        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setCategoryId(1L);
        productRequestDto.setDescription("iphone...");
        productRequestDto.setName("iphone");
        productRequestDto.setPrice(10.0d);
        productRequestDto.setSupplierId(1L);

        when(productService.save(any())).thenReturn(
                GeneralResponse.<String>builder()
                        .data("data")
                        .httpStatus(HttpStatus.CONTINUE)
                        .message("Success")
                        .responseTime(LocalDate.of(2023, 10, 27).atStartOfDay())
                        .build()
        );

        ProductController productController = new ProductController(productService);

        GeneralResponse<String> result = productController.registration(productRequestDto);

        assertEquals("data", result.getData());
        assertEquals(HttpStatus.CONTINUE, result.getHttpStatus());
        assertEquals("Success", result.getMessage());
        assertEquals(LocalDate.of(2023, 10, 27).atStartOfDay(), result.getResponseTime());
    }


    @Test
    void testFindAll() {
        when(productService.findAll()).thenReturn(
                GeneralResponse.<List<ProductResponseDto>>builder()
                        .data(new ArrayList<>())
                        .httpStatus(HttpStatus.CONTINUE)
                        .message("Success")
                        .responseTime(LocalDate.of(2023, 10, 27).atStartOfDay())
                        .build()
        );

        ProductController productController = new ProductController(productService);

        GeneralResponse<List<ProductResponseDto>> result = productController.findAll();

        assertEquals(new ArrayList<>(), result.getData());
        assertEquals(HttpStatus.CONTINUE, result.getHttpStatus());
        assertEquals("Success", result.getMessage());
        assertEquals(LocalDate.of(2023, 10, 27).atStartOfDay(), result.getResponseTime());
    }


    @Test
    void testFindById() {
        Category category = Category.builder()
                .name("Apple")
                .build();
        Supplier supplier = Supplier.builder()
                .name("apple")
                .address("Nerimanov")
                .build();
        ProductResponseDto product =ProductResponseDto.builder()
                .name("Iphone")
                .description("apple...")
                .price(10.0)
                .category(category)
                .supplier(supplier)
                .build();
        GeneralResponse<ProductResponseDto> response = GeneralResponse.of("success", HttpStatus.CONTINUE, product);

        when(productService.findById(anyLong())).thenReturn(response);

        GeneralResponse<ProductResponseDto> result = productService.findById(1L);
        assertEquals(HttpStatus.CONTINUE, result.getHttpStatus());
        assertEquals("success", result.getMessage());
    }



    @Test
    void testUpdate() {
        Category category = Category.builder()
                .name("Apple")
                .build();
        Supplier supplier = Supplier.builder()
                .name("apple")
                .address("Nerimanov")
                .build();
        ProductResponseDto product =ProductResponseDto.builder()
                .name("Iphone")
                .description("Apple history...")
                .price(10.0)
                .category(category)
                .supplier(supplier)
                .build();
        GeneralResponse<ProductResponseDto> expectedResponse = GeneralResponse.of("success", HttpStatus.CONTINUE, product);

        when(productService.update(anyLong(), any())).thenReturn(expectedResponse);

        ProductRequestUpdateDto productRequestUpdateDto = new ProductRequestUpdateDto();
        productRequestUpdateDto.setCategoryId(1L);
        productRequestUpdateDto.setDescription("Apple history...");
        productRequestUpdateDto.setName("Iphone 15");
        productRequestUpdateDto.setPrice(10.0d);
        productRequestUpdateDto.setSupplierId(1L);

        GeneralResponse<ProductResponseDto> actualResponse = productService.update(1L, productRequestUpdateDto);

        assertEquals(HttpStatus.CONTINUE, actualResponse.getHttpStatus());
        assertEquals("success", actualResponse.getMessage());
    }


    @Test
    void testDelete() {
        Category category = Category.builder()
                .name("Apple")
                .build();
        Supplier supplier = Supplier.builder()
                .name("apple")
                .address("Nerimanov")
                .build();
        ProductResponseDto product =ProductResponseDto.builder()
                .name("Iphone")
                .description("Apple history...")
                .price(10.0)
                .category(category)
                .supplier(supplier)
                .build();
        GeneralResponse<ProductResponseDto> response = GeneralResponse.of("success", HttpStatus.CONTINUE, product);

        when(productService.delete(anyLong())).thenReturn(response);

        GeneralResponse<ProductResponseDto> actualResponse = productService.delete(1L);

        assertEquals(HttpStatus.CONTINUE, actualResponse.getHttpStatus());
        assertEquals("success", actualResponse.getMessage());
    }
}
