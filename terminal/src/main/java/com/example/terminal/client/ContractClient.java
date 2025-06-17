package com.example.terminal.client;

import com.example.terminal.response.ContractResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("contract")
public interface ContractClient {

//    @GetMapping("/contracts/{id}")
//    ResponseEntity<ContractResponse> getContractById(@PathVariable Long id);
}
