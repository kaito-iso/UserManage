package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * @IdClass
 */
@Entity
@Table(name="m_departments")
@Data
public class Departments {
	
	/** 部署CD */
	@Id
	@Column(name="dept_cd")
	private String deptCd;
	
	/** 部署名 */
	@Column(name="dept_name")
	private String deptName;
	
	/** ソート順 */
	@Column(name="display_order")
	private int displayOrder;

}
