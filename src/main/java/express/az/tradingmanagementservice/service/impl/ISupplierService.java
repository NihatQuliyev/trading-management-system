package express.az.tradingmanagementservice.service.impl;

import express.az.tradingmanagementservice.exception.GeneralException;
import express.az.tradingmanagementservice.mapper.SupplierMapper;
import express.az.tradingmanagementservice.model.constant.Constants;
import express.az.tradingmanagementservice.model.dto.request.SupplierRequestDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.SupplierResponseDto;
import express.az.tradingmanagementservice.model.entity.Supplier;
import express.az.tradingmanagementservice.model.enums.ExceptionsEnum;
import express.az.tradingmanagementservice.repository.SupplierRepository;
import express.az.tradingmanagementservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ISupplierService implements SupplierService {

    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    @Override
    public GeneralResponse<String> save(SupplierRequestDto requestDto) {
        Supplier supplier = mapper.supplierRequestDtoToSupplier(requestDto);
        repository.save(supplier);
        String name = supplier.getName();

        return GeneralResponse.of(Constants.SAVE_SUCCESSFULLY, HttpStatus.CREATED,name);
    }

    @Override
    public GeneralResponse<List<SupplierResponseDto>> findAll() {
        List<Supplier> suppliers = repository.findAll();
        suppliers.stream()
                .findAny()
                .orElseThrow(() -> new GeneralException(ExceptionsEnum.SUPPLIER_NOT_FOUND));
        List<SupplierResponseDto> categoryResponseDto = mapper.supplierListToSupplierResponseDtoList(suppliers);

        return GeneralResponse.of(Constants.SUPPLIER_SHOW_SUCCESS,HttpStatus.OK, categoryResponseDto) ;
    }

    @Override
    public GeneralResponse<Supplier> findById(Long id) {
        Supplier supplier = repository.findById(id).orElseThrow(() -> new GeneralException(ExceptionsEnum.SUPPLIER_NOT_FOUND));

        return GeneralResponse.of(Constants.FIND_SUPPLIER,HttpStatus.OK,supplier);
    }
}
