package playground.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransferDTO {
    Long money;
    Long depositAndWithdrawalMoney;
    String fianceName;
    Long fianceId;
    String accountNumber;
    String description;
}
