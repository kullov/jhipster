package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AbilityCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbilityCategory.class);
        AbilityCategory abilityCategory1 = new AbilityCategory();
        abilityCategory1.setId(1L);
        AbilityCategory abilityCategory2 = new AbilityCategory();
        abilityCategory2.setId(abilityCategory1.getId());
        assertThat(abilityCategory1).isEqualTo(abilityCategory2);
        abilityCategory2.setId(2L);
        assertThat(abilityCategory1).isNotEqualTo(abilityCategory2);
        abilityCategory1.setId(null);
        assertThat(abilityCategory1).isNotEqualTo(abilityCategory2);
    }
}
