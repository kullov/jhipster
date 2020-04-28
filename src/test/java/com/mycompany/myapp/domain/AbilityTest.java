package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AbilityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ability.class);
        Ability ability1 = new Ability();
        ability1.setId(1L);
        Ability ability2 = new Ability();
        ability2.setId(ability1.getId());
        assertThat(ability1).isEqualTo(ability2);
        ability2.setId(2L);
        assertThat(ability1).isNotEqualTo(ability2);
        ability1.setId(null);
        assertThat(ability1).isNotEqualTo(ability2);
    }
}
