package playground.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserInsertDTO {

    private String name;
    private String phone_number;
    private String account_number;
    private String finance_name;
    private Long depositAndWithdrawalMoney;
    private String description;

    private LocalDateTime dateTime;

    public UserInsertDTO(){
        this.dateTime = LocalDateTime.now();
    }

}
//UserInsertDTO.java