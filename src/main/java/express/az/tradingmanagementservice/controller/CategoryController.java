package express.az.tradingmanagementservice.controller;


import express.az.tradingmanagementservice.model.dto.request.CategoryRequestDto;
import express.az.tradingmanagementservice.model.dto.response.CategoryResponseDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/trading/category/")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/registration")
    public GeneralResponse<String> registration(@RequestBody @Valid CategoryRequestDto requestDto){
        log.info("Request dto {}",requestDto);
        return categoryService.save(requestDto);
    }

    @GetMapping("/all-category")
    public GeneralResponse<List<CategoryResponseDto>> findAll(){
        GeneralResponse<List<CategoryResponseDto>> categoryList = categoryService.findAll();
        log.info("All category list {}",categoryList);
        return categoryList;
    }
}
