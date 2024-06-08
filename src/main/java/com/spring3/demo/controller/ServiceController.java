package com.spring3.demo.controller;

import com.spring3.demo.domain.response.BodyResponse;
import com.spring3.demo.security.interceptor.AllowRoles;
import com.spring3.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/service")
public class ServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    private final UserService userService;
    @AllowRoles(roles={"admin", "guest"})
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> hello() {
        return ResponseEntity.ok(null);
    }
    @AllowRoles(roles={"admin"})
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(new BodyResponse(HttpStatus.OK.value(), "Success", userService.getAllUsers()));
    }
}
