package com.raddan.springproject.service;

import com.raddan.springproject.api.request.DefaultRequest;
import com.raddan.springproject.api.response.DefaultResponse;
import com.raddan.springproject.api.response.DefaultUrlResponse;

import java.util.List;

public interface DefaultService {
    DefaultResponse getByHash(String hash);
    List<DefaultResponse> getFirstPublicDefaults();
    DefaultUrlResponse create(DefaultRequest request);
}
