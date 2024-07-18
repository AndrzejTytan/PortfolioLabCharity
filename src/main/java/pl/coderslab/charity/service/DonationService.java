package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationService {
    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public long getDonationCount() {
        return donationRepository.count();
    }

    public Long getDonationQuantityTotal() {
        return donationRepository.getDonationQuantityTotal().orElse(0L);
    }
}
