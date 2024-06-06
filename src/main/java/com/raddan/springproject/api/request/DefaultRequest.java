package com.raddan.springproject.api.request;

import lombok.Data;

@Data
public class DefaultRequest {
    private String data;
    private Long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
