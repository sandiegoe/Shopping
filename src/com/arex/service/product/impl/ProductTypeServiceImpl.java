package com.arex.service.product.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.arex.bean.product.ProductType;
import com.arex.service.base.DAOSupport;
import com.arex.service.product.ProductTypeService;

@Service
@Transactional
public class ProductTypeServiceImpl extends DAOSupport<ProductType> implements ProductTypeService {

}
