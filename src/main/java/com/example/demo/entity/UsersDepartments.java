package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * 部署マスタ
 * @Entity: このクラスがJPAの管理対象（テーブルと連動するクラス）であることを示す
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 * @IdClass : JPAで複合主キーを扱うための設定
 */
@Entity
@Table(name="m_users_departments")
@IdClass(UsersDepartmentsId.class)
@Data
public class UsersDepartments {
	
	/** 部署CD */
	@Id
	@Column(name="dept_cd")
	private String deptCd;
	
	/** ユーザーID */
	@Id
	@Column(name="user_id")
	private String userId;
	
	/** メイン所属区分 */
	@Column(name="is_main")
	private boolean isMain;
	
	/**
	 * @ManyToOne : 子から親に多:1
	 * @JoinColumn : JOIN条件を指示
	 * UsersDepartments.user_idをUsers.user_idでJOIN
	 * insertable=false, updatable=false : 読み取り専用
	 */
    @ManyToOne
    @JoinColumn(name="user_id", insertable=false, updatable=false)
    private Users user; 

    @ManyToOne
    @JoinColumn(name="dept_cd", insertable=false, updatable=false)
    private Departments department; 
	
}
