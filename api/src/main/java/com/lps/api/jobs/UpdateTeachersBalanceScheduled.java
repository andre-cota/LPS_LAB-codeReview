package com.lps.api.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lps.api.models.Professor;
import com.lps.api.services.ProfessorService;

@Component
public class UpdateTeachersBalanceScheduled {
    @Autowired
    ProfessorService professorService;

    @Scheduled(cron = "0 0 0 1 * ?")
    // @Scheduled(fixedRate = 1000 * 60 * 5) Todo: DESCOMENTAR ESSA LINHA para a
    // apresentação
    public void executeTask() {
        List<Professor> professors = professorService.findAll();
        professors.forEach(professor -> {
            if (professor.getDepartment() != null) {
                Long balance = professor.getBalance();
                balance += 1000;
                professor.setBalance(balance);
                professorService.save(professor);

            }
        });
    }
}