package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class RegisterRequestDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegisterRequestDTO.class);
        RegisterRequestDTO registerRequestDTO1 = new RegisterRequestDTO();
        registerRequestDTO1.setId(1L);
        RegisterRequestDTO registerRequestDTO2 = new RegisterRequestDTO();
        assertThat(registerRequestDTO1).isNotEqualTo(registerRequestDTO2);
        registerRequestDTO2.setId(registerRequestDTO1.getId());
        assertThat(registerRequestDTO1).isEqualTo(registerRequestDTO2);
        registerRequestDTO2.setId(2L);
        assertThat(registerRequestDTO1).isNotEqualTo(registerRequestDTO2);
        registerRequestDTO1.setId(null);
        assertThat(registerRequestDTO1).isNotEqualTo(registerRequestDTO2);
    }
}
