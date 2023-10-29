package express.az.tradingmanagementservice.controller;

import express.az.tradingmanagementservice.model.dto.request.SupplierRequestDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.SupplierResponseDto;
import express.az.tradingmanagementservice.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SupplierController.class})
@ExtendWith(SpringExtension.class)
public class SupplierControllerTest {

    @MockBean
    private SupplierService supplierService;

    @Test
    void testRegistration() {
        SupplierRequestDto supplierRequestDto = new SupplierRequestDto();
        supplierRequestDto.setAddress("Nerimanov");
        supplierRequestDto.setName("Apple");
        GeneralResponse<String> response = GeneralResponse.of("success", HttpStatus.CONTINUE, "apple...");

        when(supplierService.save(any())).thenReturn(response);

        GeneralResponse<String> actualResponse = supplierService.save(supplierRequestDto);

        assertEquals(HttpStatus.CONTINUE, actualResponse.getHttpStatus());
        assertEquals("success", actualResponse.getMessage());
        assertEquals("apple...", actualResponse.getData());
    }


    @Test
    void testFindAll() {
        List<SupplierResponseDto> suppliers = new ArrayList<>();
        GeneralResponse<List<SupplierResponseDto>> response = GeneralResponse.of("success", HttpStatus.CONTINUE, suppliers);

        when(supplierService.findAll()).thenReturn(response);

        GeneralResponse<List<SupplierResponseDto>> actualResponse = supplierService.findAll();

        assertEquals(HttpStatus.CONTINUE, actualResponse.getHttpStatus());
        assertEquals("success", actualResponse.getMessage());
        assertTrue(actualResponse.getData().isEmpty());
    }


}

