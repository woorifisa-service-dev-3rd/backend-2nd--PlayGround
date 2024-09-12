package playground.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import playground.dto.UserInsertDTO;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userName;

	private String phoneNumber;

	@Builder.Default
	@OneToMany(mappedBy = "user")
	private List<Account> accounts = new ArrayList<>();

	public static User from(UserInsertDTO userInsertDTO) {
		return User.builder().userName(userInsertDTO.getName()).phoneNumber(userInsertDTO.getPhone_number())
				.build();
	}

}
