package com.example.FinalExamProject.Configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.stream.Collectors;


@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {
    private static final long CACHE_EVICT_DELAYS_MS = 600_000; // 10mins

    @Bean
    public CacheManager cacheManager()
    {
        ConcurrentMapCacheManager mapCacheManager = new ConcurrentMapCacheManager();
        mapCacheManager.setCacheNames(EnumSet.allOf(CacheName.class)
                .stream()
                .map(CacheName::getValue)
                .collect(Collectors.toList()));

        return mapCacheManager;
    }
    @Component
    public class CacheEvictor{
        private final CacheManager cacheManager;

        public CacheEvictor(CacheManager cacheManager) {
            this.cacheManager = cacheManager;
        }

        @Scheduled(fixedDelay = CACHE_EVICT_DELAYS_MS )
        public void EvictCache(){
            cacheManager
                    .getCacheNames()
                    .forEach(CacheName -> cacheManager.getCache(CacheName).clear());
        }
    }

}
