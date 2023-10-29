package express.az.tradingmanagementservice.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import static express.az.tradingmanagementservice.model.constant.Constants.*;

@Getter
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestUpdateDto {
    @Size(min = 3, max = 50, message = NAME_REGEX)
    private String name;

    @Size(max = 255, message = DESCRIPTION_REGEX)
    private String description;

    @Positive(message = PRICE_REGEX)
    private Double price;

    private Long categoryId;

    private Long supplierId;


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}