package express.az.tradingmanagementservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import express.az.tradingmanagementservice.mapper.SupplierMapper;
import express.az.tradingmanagementservice.model.dto.request.SupplierRequestDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.SupplierResponseDto;
import express.az.tradingmanagementservice.model.entity.Supplier;
import express.az.tradingmanagementservice.repository.SupplierRepository;
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

@ContextConfiguration(classes = {ISupplierService.class})
@ExtendWith(SpringExtension.class)
class SupplierServiceTest {
    @Autowired
    private ISupplierService iSupplierService;

    @MockBean
    private SupplierMapper supplierMapper;

    @MockBean
    private SupplierRepository supplierRepository;


    @Test
    void testSave() {

        Supplier supplier = Supplier.builder()
                .address("Nerimanov")
                .name("App")
                .build();
        when(supplierRepository.save(Mockito.any())).thenReturn(supplier);

        Supplier supplierTest = Supplier.builder()
                .address("Nerimanov")
                .name("App")
                .build();
        when(supplierMapper.supplierRequestDtoToSupplier(Mockito.any())).thenReturn(supplierTest);
        GeneralResponse<String> actualSaveResult = iSupplierService.save(new SupplierRequestDto("Nihat", "Nerimanov"));
        verify(supplierMapper).supplierRequestDtoToSupplier(Mockito.any());
        verify(supplierRepository).save(Mockito.any());
        assertEquals("App", actualSaveResult.getData());
        assertEquals("Save successfully!", actualSaveResult.getMessage());
        assertEquals(HttpStatus.CREATED, actualSaveResult.getHttpStatus());
    }


    @Test
    void testFindAll() {
        Supplier supplier = Supplier.builder()
                .name("App")
                .address("Nerimanov")
                .build();

        ArrayList<Supplier> supplierList = new ArrayList<>();
        supplierList.add(supplier);
        when(supplierRepository.findAll()).thenReturn(supplierList);
        when(supplierMapper.supplierListToSupplierResponseDtoList(Mockito.<List<Supplier>>any()))
                .thenReturn(new ArrayList<>());
        GeneralResponse<List<SupplierResponseDto>> actualFindAllResult = iSupplierService.findAll();
        verify(supplierMapper).supplierListToSupplierResponseDtoList(Mockito.<List<Supplier>>any());
        verify(supplierRepository).findAll();
        assertEquals("Supplier were successfully printed!", actualFindAllResult.getMessage());
        assertEquals(HttpStatus.OK, actualFindAllResult.getHttpStatus());
        assertTrue(actualFindAllResult.getData().isEmpty());
    }

    @Test
    void testFindById() {
        Supplier supplier = new Supplier();
        supplier.setAddress("Nerimanov");
        supplier.setName("App");
        Optional<Supplier> ofResult = Optional.of(supplier);
        when(supplierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        GeneralResponse<Supplier> actualFindByIdResult = iSupplierService.findById(1L);
        verify(supplierRepository).findById(Mockito.<Long>any());
        assertEquals("Supplier successful show found for id", actualFindByIdResult.getMessage());
        assertEquals(HttpStatus.OK, actualFindByIdResult.getHttpStatus());
        assertSame(supplier, actualFindByIdResult.getData());
    }
}

