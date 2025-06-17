package com.example.terminal.controller;

import com.example.terminal.client.ContractClient;
import com.example.terminal.entity.Terminal;
//import com.example.terminal.response.ContractResponse;
import com.example.terminal.service.TerminalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terminals")
public class TerminalController {

    @Value("${eureka.instance.instance-id}")
    private String inst_id;

    private final ContractClient contractClient;
    private final TerminalService terminalService;

    public TerminalController(ContractClient contractClient, TerminalService terminalService) {
        this.contractClient = contractClient;
        this.terminalService = terminalService;
    }

    @GetMapping
    public List<Terminal> getAllTerminals() {
        return terminalService.getAllTerminals();
    }

//    @GetMapping("/{id}/contract")
//    public ResponseEntity<ContractResponse> getContractByID(@PathVariable Long id) {
//        Terminal terminal = terminalService.getTerminalById(id);
//        return contractClient.getContractById(terminal.getContractId());
//    }

    @GetMapping("/contract/{id}")
    public List<Terminal> getTerminalsByContractId(@PathVariable Long id) {
        return terminalService.findByContractId(id);
    }

    @GetMapping("/storage")
    public List<Terminal> getStorage() {
        return terminalService.getStorageTerminals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terminal> getTerminalById(@PathVariable Long id) {
        Terminal terminal = terminalService.getTerminalById(id);
        return terminal != null ? ResponseEntity.ok(terminal) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Terminal> createTerminal(@RequestBody Terminal terminal) {
        Terminal createdTerminal = terminalService.createTerminal(terminal);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTerminal);
    }

    @PutMapping
    public ResponseEntity<Terminal> updateTerminal(@RequestBody Terminal terminal) {
        Terminal updatedTerminal = terminalService.updateTerminal(terminal);
        return ResponseEntity.ok(updatedTerminal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerminal(@PathVariable Long id) {
        terminalService.deleteTerminal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public String test() { return inst_id; }
}
