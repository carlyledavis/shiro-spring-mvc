package com.carlyledavis;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
public class ShiroConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public LifecycleBeanPostProcessor beanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setDispatchOptionsRequest(true);

        return servlet;
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.addUrlMappings("/hello");

        return registration;
    }

    @Bean
    public DefaultSecurityManager securityManager(Realm realm) {
        DefaultSecurityManager sm = new DefaultWebSecurityManager();
        sm.setRealm(realm);
        return sm;
    }

    @Bean
    public Realm realm(){
        SimpleAccountRealm realm = new SimpleAccountRealm( "test-account" );
        realm.addAccount( "test", "test" , "admin" );
        return realm;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean()
    public ShiroFilterFactoryBean shiroFilter(DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMapping = new HashMap<>();
        filterChainDefinitionMapping.put("/*", "authc");

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new BasicHttpAuthenticationFilter());

        shiroFilter.setFilters(filters);
        return shiroFilter;
    }

}
