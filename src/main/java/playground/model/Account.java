package playground.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@ToString
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
}
