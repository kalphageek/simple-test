package me.kalpha.jpatest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DB별 EntityManager를 PersistenceContext Named Bean으로 등록한다.
 */
@Configuration
public class EntityManagerConfig {
    @Autowired
    @PersistenceContext(unitName = Constants.TR_UNIT_NAME)
    EntityManager trEntityManager;

    @Autowired
    @PersistenceContext(unitName = Constants.CATALOG_UNIT_NAME)
    EntityManager catalogEntityManager;

    @Bean(name = Constants.TR_UNIT_NAME)
    public EntityManager getTrEntityManager() {
        return this.trEntityManager;
    }

    @Bean(name = Constants.CATALOG_UNIT_NAME)
    public EntityManager getCatalogEntityManager() {
        return this.catalogEntityManager;
    }
}
