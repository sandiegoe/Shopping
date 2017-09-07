package com.arex.service.product.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.bean.product.ProductType;
import com.arex.service.product.ProductTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class ProductTypeServiceImplTest {

	@PersistenceContext
	private EntityManager em;
	@Resource(name="productTypeServiceImpl")
	ProductTypeService productTypeService;
	
	@Test
	public void testSave(){
		//prepare for data
		ProductType productType = new ProductType();
		productType.setName("书籍");
		productType.setNote("进步的阶梯");
		
		productTypeService.save(productType);
	}
	
	@Test
	public void testFind() {
		ProductType productType = productTypeService.find(ProductType.class, 1);
		System.out.println(productType.toString());
	}
	
	@Test
	public void testFindWithArray() {
		List<ProductType> productTypeList = productTypeService.find(ProductType.class, new Object[]{1, 2, 3});
		for (ProductType productType : productTypeList) {
			System.out.println(productType);
		}
	}
	
	@Test
	public void testDelete() {
		productTypeService.delete(ProductType.class, 1);
	}
	
	@Test
	public void testDeleteWithArray() {
		productTypeService.delete(ProductType.class, new Object[]{2, 3});
	}
	
	@Test
	public void testDeleteBySetVisibleWithArray() {
		productTypeService.deleteBySetVisible(ProductType.class, new Object[]{4, 5, 6});
	}
	
	@Test
	public void testDeleteBySetVisible() {
		productTypeService.deleteBySetVisible(ProductType.class, 1);
	}
	
	@Test
	public void testUpdate() {
		ProductType productType = productTypeService.find(ProductType.class, 1);
		productType.setName("运动产品");
		productType.setNote("这是运动产品");
		productTypeService.update(productType);
	}
}
