package com.raddan.springproject;

import com.raddan.springproject.api.response.DefaultResponse;
import com.raddan.springproject.exception.NotFoundEntityException;
import com.raddan.springproject.repository.DefaultEntity;
import com.raddan.springproject.repository.DefaultRepository;
import com.raddan.springproject.service.DefaultService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DefaultServiceTest {

    @Autowired
    private DefaultService defaultService;

    @MockBean
    private DefaultRepository defaultRepository;

    @Test
    public void notExistHash() {
        when(defaultRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> defaultService.getByHash("gdfhskjgfhd"));
    }

    @Test
    public void getExistHash() {
        DefaultEntity entity = new DefaultEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(defaultRepository.getByHash("1")).thenReturn(entity);

        DefaultResponse expected = new DefaultResponse("11", true);
        DefaultResponse actual = defaultService.getByHash("1");

        assertEquals(expected, actual);
    }

}
