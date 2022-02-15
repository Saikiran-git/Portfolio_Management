package com.cognizant.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name="AuthenticationAndAuthorization", url="http://localhost:8000/")
public interface AuthClient {

    @GetMapping("/validate")
    public Boolean verify(@RequestHeader("Authorization") String Header);
}
