package playground.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountOftenDTO {
    Long bankCode;
    String accountNumber;
    Long uid;
    String userName;
}
