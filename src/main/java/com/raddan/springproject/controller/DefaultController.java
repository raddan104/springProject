package com.raddan.springproject.controller;

import com.raddan.springproject.api.request.DefaultRequest;
import com.raddan.springproject.api.response.DefaultResponse;
import com.raddan.springproject.api.response.DefaultUrlResponse;
import com.raddan.springproject.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class DefaultController {

    private final DefaultService defaultService;

    @GetMapping("/")
    public Collection<DefaultResponse> getPublicPasteList() {
        return defaultService.getFirstPublicDefaults();
    }

    @GetMapping("/{hash}")
    public DefaultResponse getByHash(@PathVariable String hash) {
        return defaultService.getByHash(hash);
    }

    @PostMapping("/")
    public DefaultUrlResponse add(@RequestBody DefaultRequest request) {
        return defaultService.create(request);
    }

}
