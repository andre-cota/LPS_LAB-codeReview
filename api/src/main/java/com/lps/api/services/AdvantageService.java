package com.lps.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lps.api.dtos.AdvantageCreateAndUpdateRequest;
import com.lps.api.models.Advantage;
import com.lps.api.models.Company;
import com.lps.api.repositories.AdvantageRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AdvantageService {
    @Autowired
    private AdvantageRepository advantageRepository;

    @Autowired
    private CompanyService companyService;

    public List<Advantage> findByCompanyId(Long companyId) {
        return advantageRepository.findByCompanyId(companyId);
    }

    public void deleteById(Long id) {
        advantageRepository.deleteById(id);
    }

    public Advantage save(AdvantageCreateAndUpdateRequest request) {
        Advantage advantage = new Advantage();
        Company company = this.companyService.findById(request.companyId());

        advantage.setCompany(company);
        advantage.setDescription(request.description());
        advantage.setName(request.name());
        advantage.setUrlImage(request.urlImage());
        advantage.setAdvantageValue(request.value());

        return advantageRepository.save(advantage);
    }

    public Advantage updateAdvantage(Long id, AdvantageCreateAndUpdateRequest advantageDetails) {
        Advantage advantage = advantageRepository.findById(id).get();
        if (advantage != null) {
            advantage.setName(advantageDetails.name());
            advantage.setDescription(advantageDetails.description());
            advantage.setAdvantageValue(advantageDetails.value());
            advantage.setUrlImage(advantageDetails.urlImage());

            return advantageRepository.save(advantage);
        } else {
            return null;
        }
    }
}
