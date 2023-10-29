package express.az.tradingmanagementservice.controller;

import express.az.tradingmanagementservice.model.dto.request.ProductRequestDto;
import express.az.tradingmanagementservice.model.dto.request.ProductRequestUpdateDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.dto.response.ProductResponseDto;
import express.az.tradingmanagementservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trading/product/")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/registration")
    public GeneralResponse<String> registration(@RequestBody @Valid ProductRequestDto requestDto) {
        log.info("Request dto {}", requestDto);
        return productService.save(requestDto);
    }

    @GetMapping("/all-product")
    public GeneralResponse<List<ProductResponseDto>> findAll() {
        GeneralResponse<List<ProductResponseDto>> productList = productService.findAll();
        log.info("All product list {}", productList);
        return productList;
    }

    @GetMapping("/{id}")
    public GeneralResponse<ProductResponseDto> findById(@PathVariable Long id) {
        log.info("Request id {}",id);

        return productService.findById(id);

    }

    @PutMapping("/{id}")
    public GeneralResponse<ProductResponseDto> update(@PathVariable Long id,@RequestBody @Valid ProductRequestUpdateDto requestDto) {
        log.info("request id {} , Request product {}",id,requestDto);
        return productService.update(id,requestDto);

    }

    @DeleteMapping("/{id}")
    public GeneralResponse<ProductResponseDto> delete(@PathVariable Long id) {
        log.info("Request id {}",id);
        return productService.delete(id);
    }
}
