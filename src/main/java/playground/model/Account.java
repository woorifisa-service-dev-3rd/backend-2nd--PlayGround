package playground.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@ToString
public class Account {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	private Fiance fiance;

	@Builder.Default
	@OneToMany(mappedBy = "account")
	private List<TheCheat> theCheats = new ArrayList<>();

	private String accountNumber;

	private Long money;

	private String description;

	private Long depositAndWithdrawalMoney;

	@Enumerated(EnumType.STRING)
	private Type isDepositOrWithdrawal;

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
}
