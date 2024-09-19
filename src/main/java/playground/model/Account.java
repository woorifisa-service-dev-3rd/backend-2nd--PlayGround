package playground.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import playground.dto.UserInsertDTO;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Fiance fiance;

	@Builder.Default
	@OneToMany(mappedBy = "account")
	private List<TheCheat> theCheats = new ArrayList<>();

	@Column(nullable = false)
	private String accountNumber;

	@Column(nullable = false)
	private Long money;

	private String description;

	private Long depositAndWithdrawalMoney;

	@Enumerated(EnumType.STRING)
	private Type isDepositOrWithdrawal;

	@CreatedDate
	private LocalDateTime dateTime;

	public void changeUser(User user){
		if(this.user != null){
			this.user.getAccounts().remove(this);
		}
		this.user = user;
		user.getAccounts().add(this);
	}

	public void changeFiance(Fiance fiance){
		if(this.fiance != null){
			this.fiance.getAccounts().remove(this);
		}
		this.fiance = fiance;
		fiance.getAccounts().add(this);
	}

	public static Account from(UserInsertDTO userInsertDTO, User user, Fiance fiance) {
		return Account.builder()
				.user(user)  // Set the user
				.fiance(fiance)  // Set the fiance
				.accountNumber(userInsertDTO.getAccount_number())  // Set the account number from DTO
				.money(0L)  // Set money (assuming money comes from deposit/withdrawal)
				.description(userInsertDTO.getDescription())  // Set description
				.depositAndWithdrawalMoney(userInsertDTO.getDepositAndWithdrawalMoney())  // Set deposit/withdrawal amount
				.isDepositOrWithdrawal(Type.Deposit)  // Assuming Type enum, adjust as needed (you might need to map from DTO)
				.dateTime(userInsertDTO.getDateTime())  // Set dateTime (adjust if DTO provides a LocalDate instead of LocalDateTime)
				.build();
	}
}
