package com.example.terminal.service;

import com.example.terminal.entity.Terminal;
import com.example.terminal.repository.TerminalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {
    private final TerminalRepository terminalRepository;

    public TerminalService(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    public List<Terminal> getAllTerminals() { return terminalRepository.findAll(); }

    public List<Terminal> findByContractId(Long id) { return terminalRepository.findByContractId(id); }
    public List<Terminal> getStorageTerminals() {return terminalRepository.findByContractIdIsNull(); }

    public Terminal getTerminalById(Long id) { return terminalRepository.findById(id).orElse(null); }

    public Terminal createTerminal(Terminal terminal) { return terminalRepository.save(terminal); }

    public Terminal updateTerminal(Terminal terminal) { return terminalRepository.save(terminal); }

    public void deleteTerminal(Long id) { terminalRepository.deleteById(id); }
}
