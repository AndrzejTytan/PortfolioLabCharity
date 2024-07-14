package pl.coderslab.charity.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class HomeController {
    private final DonationService donationService;
    private final InstitutionService institutionService;

    public HomeController(DonationService donationService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        return "index";
    }

    @ModelAttribute("donationCount")
    public long donationCount() {
        return donationService.getDonationCount();
    }

    @ModelAttribute("donationQuantityTotal")
    public long donationQuantityTotal() {
        return donationService.getDonationQuantityTotal();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.getNInstitutions(4);
    }
}
