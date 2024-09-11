package playground.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheatCheckResponse {
    private String accountNumber;
    private String description;
    private Long amount;
}
