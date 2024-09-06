package playground.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRecentDTO {
    Long bankCode;
    String accountNumber;
    Long uid;
    String userName;
}