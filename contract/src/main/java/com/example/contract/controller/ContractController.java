package com.example.contract.controller;

import com.example.contract.client.TerminalClient;
import com.example.contract.entity.Contract;
import com.example.contract.response.TerminalResponse;
import com.example.contract.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final TerminalClient terminalClient;
    private final ContractService contractService;

    public ContractController(TerminalClient terminalClient, ContractService contractService) {
        this.terminalClient = terminalClient;
        this.contractService = contractService;
    }

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        Contract contract = contractService.getContractById(id);
        return contract != null ? ResponseEntity.ok(contract) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        Contract createdContract = contractService.createContract(contract);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContract);
    }

    @PutMapping
    public ResponseEntity<Contract> updateContract(@RequestBody Contract contract) {
        Contract updatedContract = contractService.updateContract(contract);
        return ResponseEntity.ok(updatedContract);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/terminals")
    public List<TerminalResponse> getTerminalsByContractId(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        List<TerminalResponse> result = terminalClient.getTerminalsByContractId(id, token);
        return result;
    }
}
