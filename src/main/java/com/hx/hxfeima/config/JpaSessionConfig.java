package com.hx.hxfeima.config;

import com.hx.hxfeima.core.model.Systems;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import javax.persistence.EntityManagerFactory;

@Configuration
public class JpaSessionConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

//    @Bean
//    public HibernateJpaSessionFactoryBean sessionFactory() {
//        HibernateJpaSessionFactoryBean sessionFactory=new HibernateJpaSessionFactoryBean();
//        return sessionFactory;
//    }


    @Bean
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }


    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate temp = new HibernateTemplate();
        temp.setSessionFactory (this.getSessionFactory ());
        return temp;
    }
    @Bean
    public Systems systems(){

        return new Systems();

    }

}