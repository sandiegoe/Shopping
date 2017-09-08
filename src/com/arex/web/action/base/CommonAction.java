package com.arex.web.action.base;

import java.lang.reflect.ParameterizedType;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class CommonAction<T> extends ActionSupport implements ModelDriven<T>, ServletRequestAware, ServletResponseAware{
	
	protected T model;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ServletContext application;

	public CommonAction() {
		 ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		 Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
		 try {
			this.model = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public T getModel() {
		return model;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.application = request.getServletContext();
		this.session = request.getSession();
	}
}
