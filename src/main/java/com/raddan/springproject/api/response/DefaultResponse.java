package com.raddan.springproject.api.response;

import com.raddan.springproject.api.request.PublicStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DefaultResponse {
    private final String data;
    private final boolean isPublic;
}
