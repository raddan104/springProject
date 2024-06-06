package com.raddan.springproject.controller;

import com.raddan.springproject.api.request.DefaultRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
public class DefaultController {

    @GetMapping("/")
    public Collection<String> getPublicPasteList() {
        return Collections.emptyList();
    }

    @GetMapping("/{hash}")
    public String getByHash(@PathVariable String hash) {
        return hash;
    }

    @PostMapping("/")
    public String add(@RequestBody DefaultRequest request) {
        return request.getData();
    }

}
