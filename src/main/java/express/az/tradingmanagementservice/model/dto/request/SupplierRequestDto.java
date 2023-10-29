package express.az.tradingmanagementservice.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import static express.az.tradingmanagementservice.model.constant.Constants.NAME_REGEX;

@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequestDto {

    @Size(min = 3, max = 50, message = NAME_REGEX)
    private String name;

    @NotBlank
    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
