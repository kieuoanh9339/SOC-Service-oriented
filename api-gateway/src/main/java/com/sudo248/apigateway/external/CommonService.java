package com.sudo248.apigateway.external;

import com.sudo248.domain.exception.ApiException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "COMMON-SERVICE", url = "http://localhost:8081/internal")
@Service
public interface CommonService {
    @GetMapping("/validate/{token}")
    boolean validateToken(@PathVariable("token") String token) throws ApiException;

    @GetMapping("/user-id/{token}")
    String getUserIdFromToken(@PathVariable("token") String token);
}
