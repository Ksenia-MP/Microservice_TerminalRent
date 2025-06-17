package com.example.contract.client;

import com.example.contract.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("terminal")
public interface TerminalClient {
    @GetMapping("/terminals/contract/{id}")
    List<TerminalResponse> getTerminalsByContractId(@PathVariable Long id, @RequestHeader("Authorization") String token);

}
