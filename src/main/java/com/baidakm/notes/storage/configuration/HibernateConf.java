package com.baidakm.notes.storage.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConf {
	
	@Bean
	public SessionFactory sessionFactory() {
		SessionFactory factory = 
				new org.hibernate.cfg.Configuration()
										.configure()
										.buildSessionFactory();
		return factory;
	}
}
