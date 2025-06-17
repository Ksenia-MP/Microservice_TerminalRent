package com.example.contract.service;

import com.example.contract.entity.Contract;
import com.example.contract.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getAllContracts() { return contractRepository.findAll(); }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }
}
