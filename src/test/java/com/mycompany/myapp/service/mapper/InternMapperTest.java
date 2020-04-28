package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InternMapperTest {

    private InternMapper internMapper;

    @BeforeEach
    public void setUp() {
        internMapper = new InternMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(internMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(internMapper.fromId(null)).isNull();
    }
}
