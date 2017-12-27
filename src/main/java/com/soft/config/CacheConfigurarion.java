package com.soft.config;
import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfigurarion {


   @Bean
   public EhCacheCacheManager cacheManger(CacheManager ehcacheCacheManger){

      return  new EhCacheCacheManager(ehcacheCacheManger);
   }

   @Bean
   public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
      EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
      ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
      return ehCacheManagerFactoryBean;
   }
}