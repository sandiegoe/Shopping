package com.arex.service.product.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.arex.bean.PageInfo;
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
	public void testFindWithPage() {
		PageInfo<ProductType> pageInfo = productTypeService.findWithPage(ProductType.class, 0, 5);
		for (ProductType productType : pageInfo.getEntityList()) {
			System.out.println(productType);
		}
		System.out.println(pageInfo.getTotalResults());
	}
	
	@Test
	public void testFindWithPage2() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("producttypeid", "desc");
		PageInfo<ProductType> pageInfo = productTypeService.findWithPage(ProductType.class, 0, 5, orderby);
		for (ProductType productType : pageInfo.getEntityList()) {
			System.out.println(productType);
		}
		System.out.println(pageInfo.getTotalResults());
	}
	
	@Test
	public void testFindWithPage3() {
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("producttypeid", "desc");
		String hqlWhere = " where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hqlWhere += " and o.visible=? ";
		paramsList.add(true);
		Object[] params = paramsList.toArray();
		
		PageInfo<ProductType> pageInfo = productTypeService.findWithPage(ProductType.class, 0, 5, hqlWhere, params, orderby);
		for (ProductType productType : pageInfo.getEntityList()) {
			System.out.println(productType);
		}
		System.out.println(pageInfo.getTotalResults());
	}
	
	@Test
	public void testFindWithPage4() {
		
		String hqlWhere = " where 1=1 ";
		List<Object> paramsList = new ArrayList<Object>();
		hqlWhere += " and o.visible=? ";
		paramsList.add(true);
		Object[] params = paramsList.toArray();
		
		PageInfo<ProductType> pageInfo = productTypeService.findWithPage(ProductType.class, 0, 5, hqlWhere, params);
		for (ProductType productType : pageInfo.getEntityList()) {
			System.out.println(productType);
		}
		System.out.println(pageInfo.getTotalResults());
	}
	
	@Test
	public void testFindWithPage5() {
		PageInfo<ProductType> pageInfo = productTypeService.findWithPage(ProductType.class);
		for (ProductType productType : pageInfo.getEntityList()) {
			System.out.println(productType);
		}
		System.out.println(pageInfo.getTotalResults());
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
