package com.example.contract.repository;

import com.example.contract.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@FeignClient("auth-service")
public interface AuthClient {

    @RequestMapping(method = POST, value = "/auth/verify")
    UserDto getUserInfo(String username);

}
