package com.example.terminal.repository;

import com.example.terminal.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminalRepository extends JpaRepository<Terminal, Long> {
    List<Terminal> findByContractId(Long contractId);
    List<Terminal> findByContractIdIsNull();
}
