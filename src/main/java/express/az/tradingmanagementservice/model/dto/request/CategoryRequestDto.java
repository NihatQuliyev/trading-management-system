package express.az.tradingmanagementservice.model.dto.request;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

import static express.az.tradingmanagementservice.model.constant.Constants.NAME_REGEX;

@Builder
@Validated
public class CategoryRequestDto {

    @Size(min = 3, max = 50, message = NAME_REGEX)
    private String name;

    public CategoryRequestDto() {
    }

    public CategoryRequestDto(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
