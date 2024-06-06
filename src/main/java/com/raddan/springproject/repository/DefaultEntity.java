package com.raddan.springproject.repository;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DefaultEntity {
    private int id;
    private String data;
    private String hash;
    private LocalDateTime lifetime;
    private boolean isPublic;
}
