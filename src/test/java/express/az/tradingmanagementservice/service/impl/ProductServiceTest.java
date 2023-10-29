package express.az.tradingmanagementservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import express.az.tradingmanagementservice.helper.ProductServiceHelper;
import express.az.tradingmanagementservice.mapper.ProductMapper;
import express.az.tradingmanagementservice.model.dto.request.ProductRequestDto;
import express.az.tradingmanagementservice.model.dto.request.ProductRequestUpdateDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.ProductResponseDto;
import express.az.tradingmanagementservice.model.entity.Category;
import express.az.tradingmanagementservice.model.entity.Product;
import express.az.tradingmanagementservice.model.entity.Supplier;
import express.az.tradingmanagementservice.repository.ProductRepository;
import express.az.tradingmanagementservice.service.CategoryService;
import express.az.tradingmanagementservice.service.SupplierService;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {IProductService.class})
@ExtendWith(SpringExtension.class)
class IProductServiceTest {
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private IProductService iProductService;

    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductServiceHelper productServiceHelper;

    @MockBean
    private SupplierService supplierService;


    @Test
    void testSave() {
        Category category = new Category();
        category.setName("Apple");

        Supplier supplier = new Supplier();
        supplier.setAddress("Nerimanov");
        supplier.setName("App");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("apple...");
        product.setName("Iphone");
        product.setPrice(1000.0d);
        product.setSupplier(supplier);
        when(productRepository.save(Mockito.any())).thenReturn(product);

        Category category2 = new Category();
        category2.setName("Apple");

        Supplier supplier2 = new Supplier();
        supplier2.setAddress("Nerimanov");
        supplier2.setName("App");

        Product product2 = new Product();
        product2.setCategory(category2);
        product2.setDescription("apple...");
        product2.setName("Iphone");
        product2.setPrice(1000.0d);
        product2.setSupplier(supplier2);
        when(productMapper.productRequestDtoToProduct(Mockito.any())).thenReturn(product2);

        Category category3 = new Category();
        category3.setName("Apple");
        GeneralResponse.GeneralResponseBuilder<Category> builderResult = GeneralResponse.builder();
        GeneralResponse.GeneralResponseBuilder<Category> messageResult = builderResult.data(category3)
                .httpStatus(HttpStatus.CONTINUE)
                .message("success");
        GeneralResponse<Category> buildResult = messageResult.responseTime(LocalDate.of(2023, 10, 27).atStartOfDay())
                .build();
        when(categoryService.findById(Mockito.<Long>any())).thenReturn(buildResult);

        Supplier supplier3 = new Supplier();
        supplier3.setAddress("Nerimanov");
        supplier3.setName("App");
        GeneralResponse.GeneralResponseBuilder<Supplier> builderResult2 = GeneralResponse.builder();
        GeneralResponse.GeneralResponseBuilder<Supplier> messageResult2 = builderResult2.data(supplier3)
                .httpStatus(HttpStatus.CONTINUE)
                .message("success");
        GeneralResponse<Supplier> buildResult2 = messageResult2.responseTime(LocalDate.of(2023, 10, 28).atStartOfDay())
                .build();
        when(supplierService.findById(Mockito.<Long>any())).thenReturn(buildResult2);
        GeneralResponse<String> actualSaveResult = iProductService.save(new ProductRequestDto());
        verify(productMapper).productRequestDtoToProduct(Mockito.<ProductRequestDto>any());
        verify(categoryService).findById(Mockito.<Long>any());
        verify(supplierService).findById(Mockito.<Long>any());
        verify(productRepository).save(Mockito.<Product>any());
        assertEquals("Iphone", actualSaveResult.getData());
        assertEquals("Save successfully!", actualSaveResult.getMessage());
        assertEquals(HttpStatus.CREATED, actualSaveResult.getHttpStatus());
    }


    @Test
    void testFindAll() {
        Category category = new Category();
        category.setName("Apple");

        Supplier supplier = new Supplier();
        supplier.setAddress("Nerimanov");
        supplier.setName("App");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("apple...");
        product.setName("Iphone");
        product.setPrice(1000.0d);
        product.setSupplier(supplier);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.productListToProductResponseDtoList(Mockito.any()))
                .thenReturn(new ArrayList<>());
        GeneralResponse<List<ProductResponseDto>> actualFindAllResult = iProductService.findAll();
        verify(productMapper).productListToProductResponseDtoList(Mockito.any());
        verify(productRepository).findAll();
        assertEquals("Product were successfully printed", actualFindAllResult.getMessage());
        assertEquals(HttpStatus.OK, actualFindAllResult.getHttpStatus());
        assertTrue(actualFindAllResult.getData().isEmpty());
    }




    @Test
    void testFindById() {
        Category category = new Category();
        category.setName("Apple");

        Supplier supplier = new Supplier();
        supplier.setAddress("Nerimanov");
        supplier.setName("App");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("apple...");
        product.setName("Iphone");
        product.setPrice(1000.0d);
        product.setSupplier(supplier);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Category category2 = new Category();
        category2.setName("Apple");
        ProductResponseDto.ProductResponseDtoBuilder priceResult = ProductResponseDto.builder()
                .category(category2)
                .description("apple...")
                .id(1L)
                .name("Iphone")
                .price(1000.0d);

        Supplier supplier2 = new Supplier();
        supplier2.setAddress("Nerimanov");
        supplier2.setName("App");
        ProductResponseDto buildResult = priceResult.supplier(supplier2).build();
        when(productMapper.productToProductResponseDto(Mockito.<Product>any())).thenReturn(buildResult);
        GeneralResponse<ProductResponseDto> actualFindByIdResult = iProductService.findById(1L);
        verify(productMapper).productToProductResponseDto(Mockito.<Product>any());
        verify(productRepository).findById(Mockito.<Long>any());
        assertEquals("Product successful show found for id", actualFindByIdResult.getMessage());
        assertEquals(HttpStatus.OK, actualFindByIdResult.getHttpStatus());
    }

    @Test
    void testUpdate() {
        Category category = new Category();
        category.setName("Apple");

        Supplier supplier = new Supplier();
        supplier.setAddress("Nerimanov");
        supplier.setName("App");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("apple...");
        product.setName("Iphone");
        product.setPrice(1000.0d);
        product.setSupplier(supplier);
        Optional<Product> ofResult = Optional.of(product);

        Category category2 = new Category();
        category2.setName("Apple");

        Supplier supplier2 = new Supplier();
        supplier2.setAddress("Nerimanov");
        supplier2.setName("App");

        Product product2 = new Product();
        product2.setCategory(category2);
        product2.setDescription("apple...");
        product2.setName("Iphone");
        product2.setPrice(1000.0d);
        product2.setSupplier(supplier2);
        when(productRepository.save(Mockito.any())).thenReturn(product2);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Category category3 = new Category();
        category3.setName("Apple");
        ProductResponseDto.ProductResponseDtoBuilder priceResult = ProductResponseDto.builder()
                .category(category3)
                .description("apple...")
                .id(1L)
                .name("Iphone")
                .price(1000.0d);

        Supplier supplier3 = new Supplier();
        supplier3.setAddress("Nerimanov");
        supplier3.setName("App");
        ProductResponseDto buildResult = priceResult.supplier(supplier3).build();
        when(productMapper.productToProductResponseDto(Mockito.any())).thenReturn(buildResult);
        doNothing().when(productServiceHelper)
                .checkingUpdate(Mockito.any(), Mockito.any());
        GeneralResponse<ProductResponseDto> actualUpdateResult = iProductService.update(1L,
                new ProductRequestUpdateDto());
        verify(productServiceHelper).checkingUpdate(Mockito.any(), Mockito.any());
        verify(productMapper).productToProductResponseDto(Mockito.any());
        verify(productRepository).findById(Mockito.<Long>any());
        verify(productRepository).save(Mockito.any());
        assertEquals("Update successfully product", actualUpdateResult.getMessage());
        assertEquals(HttpStatus.OK, actualUpdateResult.getHttpStatus());
    }

    @Test
    void testDelete() {
        Category category = new Category();
        category.setName("Apple");

        Supplier supplier = new Supplier();
        supplier.setAddress("Nerimanov");
        supplier.setName("App");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("apple...");
        product.setName("Iphone");
        product.setPrice(1000.0d);
        product.setSupplier(supplier);
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepository).deleteById(Mockito.<Long>any());
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Category category2 = new Category();
        category2.setName("Apple");
        ProductResponseDto.ProductResponseDtoBuilder priceResult = ProductResponseDto.builder()
                .category(category2)
                .description("apple...")
                .id(1L)
                .name("Iphone")
                .price(1000.0d);

        Supplier supplier2 = new Supplier();
        supplier2.setAddress("Nerimanov");
        supplier2.setName("App");
        ProductResponseDto buildResult = priceResult.supplier(supplier2).build();
        when(productMapper.productToProductResponseDto(Mockito.<Product>any())).thenReturn(buildResult);
        GeneralResponse<ProductResponseDto> actualDeleteResult = iProductService.delete(1L);
        verify(productMapper).productToProductResponseDto(Mockito.<Product>any());
        verify(productRepository).deleteById(Mockito.<Long>any());
        verify(productRepository).findById(Mockito.<Long>any());
        assertEquals("According to the id, the product was successfully deleted", actualDeleteResult.getMessage());
        assertEquals(HttpStatus.OK, actualDeleteResult.getHttpStatus());
    }

}

