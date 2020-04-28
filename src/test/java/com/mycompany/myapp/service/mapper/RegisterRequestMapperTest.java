package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterRequestMapperTest {

    private RegisterRequestMapper registerRequestMapper;

    @BeforeEach
    public void setUp() {
        registerRequestMapper = new RegisterRequestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(registerRequestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(registerRequestMapper.fromId(null)).isNull();
    }
}
