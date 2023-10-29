package express.az.tradingmanagementservice.controller;

import express.az.tradingmanagementservice.model.dto.request.SupplierRequestDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.SupplierResponseDto;
import express.az.tradingmanagementservice.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trading/supplier/")
@RequiredArgsConstructor
@Slf4j
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/registration")
    public GeneralResponse<String> registration(@RequestBody @Valid SupplierRequestDto requestDto) {
        log.info("Request dto {}", requestDto);
        return supplierService.save(requestDto);
    }

    @GetMapping("/all-supplier")
    public GeneralResponse<List<SupplierResponseDto>> findAll() {
        GeneralResponse<List<SupplierResponseDto>> supplierList = supplierService.findAll();
        log.info("All supplier list {}", supplierList);
        return supplierList;
    }
}
