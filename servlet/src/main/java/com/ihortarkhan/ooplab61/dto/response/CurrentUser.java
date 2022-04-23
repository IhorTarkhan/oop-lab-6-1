package com.ihortarkhan.ooplab61.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUser {
    Long id;
    String username;
}
