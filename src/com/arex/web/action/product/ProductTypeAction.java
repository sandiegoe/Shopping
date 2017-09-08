package com.arex.web.action.product;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arex.bean.product.ProductType;
import com.arex.dto.product.ProductTypeDTO;
import com.arex.service.product.ProductTypeService;
import com.arex.web.action.base.CommonAction;

@Controller
@Scope(value="prototype")
public class ProductTypeAction extends CommonAction<ProductTypeDTO> {

	@Resource(name="productTypeServiceImpl")
	private ProductTypeService productTypeService;
	
	public String list() {
		
		ProductType productType = productTypeService.find(7);
		
		request.setAttribute("productType", productType);
		
		return "list";
	}
}
