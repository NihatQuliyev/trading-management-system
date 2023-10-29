package express.az.tradingmanagementservice.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;
import static express.az.tradingmanagementservice.model.constant.Constants.*;

@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank
    @Size(min = 3, max = 50, message = NAME_REGEX)
    private String name;

    @NotBlank
    @Size(max = 255, message = DESCRIPTION_REGEX)
    private String description;

    @NotNull
    @Positive(message = PRICE_REGEX)
    private Double price;

    @NotNull(message = CATEGORY_URGENT)
    @Min(value = 1,message = CATEGORY_REGEX)
    private Long categoryId;

    @NotNull(message = SUPPLIER_URGENT)
    @Min(value = 1,message = SUPPLIER_REGEX)
    private Long supplierId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}