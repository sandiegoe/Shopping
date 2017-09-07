package com.arex.bean.product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arex.service.product.ProductTypeService;
import com.arex.service.product.impl.ProductTypeServiceImpl;

public class ProductTypeTest {

	@Test
	public void testSaveProduct() {
		
		ProductType productType = new ProductType();
		
		//加载hibernate配置
		Configuration cfg = new Configuration();
		cfg.configure();
		
		//构建session
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		//开启事物，持久化对象
		session.beginTransaction();
		session.save(productType);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	@Test
	public void testSaveProduct2() {
		
		ProductType productType = new ProductType();
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("arex");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(productType);
		em.getTransaction().commit();
		em.close();
		entityManagerFactory.close();
	}

	@Test
	public void testSpring() {
		
		ProductType productType = new ProductType();
		productType.setName("服装");
		productType.setNote("这是服装");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ProductTypeService productTypeService = ctx.getBean("productTypeServiceImpl", ProductTypeServiceImpl.class);
		productTypeService.save(productType);
		
	}
}
