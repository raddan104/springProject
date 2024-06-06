package com.raddan.springproject.service;

import com.raddan.springproject.api.request.DefaultRequest;
import com.raddan.springproject.api.request.PublicStatus;
import com.raddan.springproject.api.response.DefaultResponse;
import com.raddan.springproject.api.response.DefaultUrlResponse;
import com.raddan.springproject.repository.DefaultEntity;
import com.raddan.springproject.repository.DefaultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultServiceImpl implements DefaultService {

    private String host;
    private int publicListSize;

    private final DefaultRepository repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Autowired
    @ConstructorBinding
    public DefaultServiceImpl(
            @Value("${app.host}") String host,
            @Value("${app.public_list_size}") int publicListSize,
            DefaultRepository repository
    ) {
        this.host = host;
        this.publicListSize = publicListSize;
        this.repository = repository;
    }

    @Override
    public DefaultResponse getByHash(String hash) {
        DefaultEntity defaultEntity = repository.getByHash(hash);
        return new DefaultResponse(defaultEntity.getData(), defaultEntity.isPublic());
    }

    @Override
    public List<DefaultResponse> getFirstPublicDefaults() {
        List<DefaultEntity> list = repository.getListOfPublicAndAlive(publicListSize);
        return list.stream().map(defaultEntity ->
             new DefaultResponse(defaultEntity.getData(), defaultEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public DefaultUrlResponse create(DefaultRequest request) {

        int hash = generateId();
        DefaultEntity defaultEntity = new DefaultEntity();
        defaultEntity.setData(request.getData());
        defaultEntity.setId(hash);
        defaultEntity.setHash(Integer.toHexString(hash));
        defaultEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        defaultEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(defaultEntity);

        return new DefaultUrlResponse(host + "/" + defaultEntity.getHash());

    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
