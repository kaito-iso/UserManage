package com.example.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー・部署所属テーブル（m_users_departments）の複合主キー用クラス
 * JPAの@IdClassとして利用される
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDepartmentsId implements Serializable{
	
	/** 部署コード (m_users_departments.dept_cd) */
	private String deptCd;
	
	/** ユーザーID (m_users_departments.user_id) */
    private String userId;

}
