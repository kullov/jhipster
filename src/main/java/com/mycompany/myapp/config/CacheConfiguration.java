package com.mycompany.myapp.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.PersistentToken.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".persistentTokens");
            createCache(cm, com.mycompany.myapp.domain.Organization.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Organization.class.getName() + ".requests");
            createCache(cm, com.mycompany.myapp.domain.Organization.class.getName() + ".interns");
            createCache(cm, com.mycompany.myapp.domain.Organization.class.getName() + ".requestAssignments");
            createCache(cm, com.mycompany.myapp.domain.Intern.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Intern.class.getName() + ".registerRequests");
            createCache(cm, com.mycompany.myapp.domain.Intern.class.getName() + ".requestAssignments");
            createCache(cm, com.mycompany.myapp.domain.Intern.class.getName() + ".internAbilities");
            createCache(cm, com.mycompany.myapp.domain.Teacher.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Ability.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Ability.class.getName() + ".interns");
            createCache(cm, com.mycompany.myapp.domain.Ability.class.getName() + ".requests");
            createCache(cm, com.mycompany.myapp.domain.RegisterRequest.class.getName());
            createCache(cm, com.mycompany.myapp.domain.RequestAssignment.class.getName());
            createCache(cm, com.mycompany.myapp.domain.RequestAssignment.class.getName() + ".statuses");
            createCache(cm, com.mycompany.myapp.domain.Request.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Request.class.getName() + ".registerRequests");
            createCache(cm, com.mycompany.myapp.domain.Request.class.getName() + ".requestAbilities");
            createCache(cm, com.mycompany.myapp.domain.Request.class.getName() + ".requestStatuses");
            createCache(cm, com.mycompany.myapp.domain.AbilityCategory.class.getName());
            createCache(cm, com.mycompany.myapp.domain.AbilityCategory.class.getName() + ".abilityTypes");
            createCache(cm, com.mycompany.myapp.domain.Status.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Status.class.getName() + ".requests");
            createCache(cm, com.mycompany.myapp.domain.Status.class.getName() + ".requestAssignments");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
