package com.ihortarkhan.ooplab61.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse {
    private boolean success;

    public static SuccessResponse ok() {
        return SuccessResponse.builder().success(true).build();
    }
}
