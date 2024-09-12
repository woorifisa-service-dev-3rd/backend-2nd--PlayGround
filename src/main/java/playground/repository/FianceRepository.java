package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.model.Fiance;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FianceRepository extends JpaRepository<Fiance, Long> {
    Optional<Fiance> findByName(String fiance_name);
}