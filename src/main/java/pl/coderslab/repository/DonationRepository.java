package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Donation;

public interface DonationRepository extends JpaRepository<Long, Donation> {
}
