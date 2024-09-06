package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.model.TheCheat;

public interface TheCheatRepository extends JpaRepository<TheCheat, Long> {
}
