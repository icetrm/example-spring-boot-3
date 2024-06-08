package com.spring3.demo.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BodyResponse {
    private Integer status;
    private String message;
    private Object data;
}
