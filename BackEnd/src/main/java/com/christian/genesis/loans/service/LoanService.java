package com.christian.genesis.loans.service;

import com.christian.genesis.loans.model.Loan;
import com.christian.genesis.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(final LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoan(final Long id) {
        return loanRepository.findById(id);
    }

    public Loan save(Loan loan) {
        Optional<Loan> loanOptional = loanRepository.findById(loan.getId());
        if (!loanOptional.isPresent()) {
            throw new IllegalStateException("client not exists");
        }
        return loanRepository.save(loan);
    }
}
