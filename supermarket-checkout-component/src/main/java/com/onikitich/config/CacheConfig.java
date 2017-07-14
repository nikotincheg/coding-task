package com.onikitich.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.ConfigurationFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.onikitich.config.CacheNames.PRODUCTS;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        net.sf.ehcache.config.Configuration configuration = ConfigurationFactory.parseConfiguration();
        configuration.setName("supermarket-checkout-app");

        Cache priceMultipleCache = new Cache(
                new CacheConfiguration()
                        .name(PRODUCTS)
                        .maxEntriesLocalHeap(100)
                        .timeToLiveSeconds(43200)
        );

        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create(configuration);
        cacheManager.addCacheIfAbsent(priceMultipleCache);
        return new EhCacheCacheManager(cacheManager);
    }
}
