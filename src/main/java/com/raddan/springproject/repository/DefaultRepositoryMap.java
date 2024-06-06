package com.raddan.springproject.repository;

import com.raddan.springproject.exception.NotFoundEntityException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class DefaultRepositoryMap implements DefaultRepository {

    private final Map<String, DefaultEntity> vault = new ConcurrentHashMap<>();

    @Override
    public DefaultEntity getByHash(String hash) {
        DefaultEntity defaultEntity = vault.get(hash);
        if (defaultEntity == null) {
            throw new NotFoundEntityException("Pastebox not found with hash: " + hash);
        }
        return defaultEntity;
    }

    @Override
    public List<DefaultEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(DefaultEntity::isPublic)
                .filter(defaultEntity -> defaultEntity.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(DefaultEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(DefaultEntity defaultEntity) {
        vault.put(defaultEntity.getHash(), defaultEntity);
    }
}
