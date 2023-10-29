package express.az.tradingmanagementservice.mapper;

import express.az.tradingmanagementservice.model.dto.request.SupplierRequestDto;
import express.az.tradingmanagementservice.model.dto.response.SupplierResponseDto;
import express.az.tradingmanagementservice.model.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mapping(target = "id",ignore = true)
    Supplier supplierRequestDtoToSupplier(SupplierRequestDto requestDto);
    SupplierResponseDto supplierToSupplierRequestDto(Supplier requestDto);
    List<SupplierResponseDto> supplierListToSupplierResponseDtoList(List<Supplier> suppliers);


}
