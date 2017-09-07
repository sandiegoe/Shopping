package com.arex.bean.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table
public class ProductType implements Serializable {

	private static final long serialVersionUID = 8902554013247969269L;
	private Integer producttypeid;
	private String name;
	private String note;
	private Boolean visible = true;
	
	/** 子类别和父类别 **/
	private Set<ProductType> children = new HashSet<ProductType>();
	private ProductType parent;

	@OneToMany(cascade={CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy="parent")
	public Set<ProductType> getChildren() {
		return children;
	}

	public void setChildren(Set<ProductType> children) {
		this.children = children;
	}

	@ManyToOne(cascade=CascadeType.REFRESH, optional=true)
	@JoinColumn(name="parentid")
	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}
	
	@Column(length=36,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=128)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(nullable=false)
	@Type(type="yes_no")
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getProducttypeid() {
		return producttypeid;
	}

	public void setProducttypeid(Integer producttypeid) {
		this.producttypeid = producttypeid;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((producttypeid == null) ? 0 : producttypeid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		if (producttypeid == null) {
			if (other.producttypeid != null)
				return false;
		} else if (!producttypeid.equals(other.producttypeid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductType [typeid=" + producttypeid + ", name=" + name + ", note="
				+ note + ", visible=" + visible + "]";
	}
}
