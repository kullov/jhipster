package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class RegisterRequestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegisterRequest.class);
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setId(1L);
        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setId(registerRequest1.getId());
        assertThat(registerRequest1).isEqualTo(registerRequest2);
        registerRequest2.setId(2L);
        assertThat(registerRequest1).isNotEqualTo(registerRequest2);
        registerRequest1.setId(null);
        assertThat(registerRequest1).isNotEqualTo(registerRequest2);
    }
}
