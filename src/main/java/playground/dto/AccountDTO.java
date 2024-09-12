package playground.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import playground.model.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AccountDTO {

    private Long id;

    private UserDTO user;

    private FianceDTO fiance;


    private String accountNumber;

    private Long money;

    private String description;

    private Long depositAndWithdrawalMoney;

    private Type isDepositOrWithdrawal;

    private LocalDateTime dateTime;


    public static AccountDTO from(Account account){
        return AccountDTO.builder().id(account.getId()).user(UserDTO.from(account.getUser())).fiance(FianceDTO.from(account.getFiance()))
                .accountNumber(account.getAccountNumber()).money(account.getMoney()).description(account.getDescription())
                .depositAndWithdrawalMoney(account.getDepositAndWithdrawalMoney()).isDepositOrWithdrawal(account.getIsDepositOrWithdrawal())
                .dateTime(account.getDateTime()).build();
    }
}
