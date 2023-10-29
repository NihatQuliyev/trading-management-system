package express.az.tradingmanagementservice.service;

import express.az.tradingmanagementservice.model.dto.request.SupplierRequestDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.SupplierResponseDto;
import express.az.tradingmanagementservice.model.entity.Supplier;

import java.util.List;

public interface SupplierService {
    GeneralResponse<String> save(SupplierRequestDto supplierRequestDto );
    GeneralResponse<List<SupplierResponseDto>> findAll();
    GeneralResponse<Supplier> findById(Long id);

}
