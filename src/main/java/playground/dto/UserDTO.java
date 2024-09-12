package playground.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import playground.dto.UserInsertDTO;
import playground.model.Account;
import playground.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String phoneNumber;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Account> accounts = new ArrayList<>();

    public static UserDTO from(User user){
        return UserDTO.builder().id(user.getId()).userName(user.getUserName()).phoneNumber(user.getPhoneNumber()).build();
    }

}