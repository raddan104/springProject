package com.raddan.springproject.repository;

import java.util.List;

public interface DefaultRepository {
    DefaultEntity getByHash(String hash);

    List<DefaultEntity> getListOfPublicAndAlive(int amount);

    void add(DefaultEntity defaultEntity);
}
