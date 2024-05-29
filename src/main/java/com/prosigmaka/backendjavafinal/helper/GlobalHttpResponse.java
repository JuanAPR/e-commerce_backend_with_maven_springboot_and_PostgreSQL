package com.prosigmaka.backendjavafinal.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalHttpResponse<T> {
    private int status;
    private String msg;
    private T data;
}
