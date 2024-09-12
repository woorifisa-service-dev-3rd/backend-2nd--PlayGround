package playground.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TransferRequest {
    Long amount; //보낸 돈
    Long bank; //은행 코드
    String account; //account_number
    String description;
    String memo;

}
