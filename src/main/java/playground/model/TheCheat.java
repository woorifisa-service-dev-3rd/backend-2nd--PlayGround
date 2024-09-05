package playground.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class TheCheat {

	@ManyToOne(fetch = FetchType.LAZY)
	private Account account;
	private String description;

	public void changeAccount(Account account){
		if(this.account != null){
			this.account.getTheCheats().remove(this);
		}
		this.account = account;
		account.getTheCheats().add(this);
	}
}
