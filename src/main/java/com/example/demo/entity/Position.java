package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * 役職マスタ
 * @Entity: このクラスがJPAの管理対象（テーブルと連動するクラス）であることを示す
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Entity
@Table(name = "m_position")
@Data
public class Position {
	
	/** 役職CD */
	@Id
	@Column(name = "position_cd")
	private String positionCd;
	
	/** 役職名 */
	@Column(name = "position_name")
	private String positionName;
	
	/** ソート順 */
	@Column(name="display_order")
	private int displayOrder;
	
}
