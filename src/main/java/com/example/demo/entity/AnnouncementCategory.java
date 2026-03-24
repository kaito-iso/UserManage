package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * お知らせのカテゴリーを管理
 * @Entity: このクラスがJPAの管理対象（テーブルと連動するクラス）であることを示す
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Entity
@Table(name = "m_announcements_category")
@Data
public class AnnouncementCategory {

	/** カテゴリーCD */
	@Id
	@Column(name = "category_cd")
	private String categoryCd;

	/** カテゴリー名 */
	@Column(name = "category_name")
	private String categoryName;

	/** ソート順 */
	@Column(name = "display_order")
	private int displayOrder;

	@Column(name = "color")
	private String color;

}
