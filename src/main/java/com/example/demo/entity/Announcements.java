package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * お知らせの本文や掲示期間などを管理
 * @Entity: このクラスがJPAの管理対象（テーブルと連動するクラス）であることを示す
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Entity
@Table(name = "t_announcements")
@Data
public class Announcements {

	/** ID 自動採番 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動採番
	@Column(name = "announcement_id")
	private Integer id;

	/** タイトル */
	@Column(name = "title")
	private String title;

	/** 内容 */
	@Column(name = "content")
	private String content;

	/** カテゴリーCD */
	@Column(name = "category_cd")
	private String categoryCd;

	/** カテゴリー名 (リレーション) */
	@ManyToOne
	@JoinColumn(name = "category_cd", insertable = false, updatable = false)
	private AnnouncementCategory category;

	/** 公開開始日 */
	@Column(name = "publish_date")
	private LocalDateTime publishDate;

	/** 公開終了日 */
	@Column(name = "expires_date")
	private LocalDateTime expiresDate;

	/** 作成ユーザーID */
	@Column(name = "create_user_id")
	private String createUserId;

	/** 作成日時 */
	@Column(name = "add_date")
	private LocalDateTime addDate;

	/** 変更日時 */
	@Column(name = "upd_date")
	private LocalDateTime updDate;

}
