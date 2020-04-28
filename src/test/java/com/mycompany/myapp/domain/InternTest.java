package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class InternTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Intern.class);
        Intern intern1 = new Intern();
        intern1.setId(1L);
        Intern intern2 = new Intern();
        intern2.setId(intern1.getId());
        assertThat(intern1).isEqualTo(intern2);
        intern2.setId(2L);
        assertThat(intern1).isNotEqualTo(intern2);
        intern1.setId(null);
        assertThat(intern1).isNotEqualTo(intern2);
    }
}
