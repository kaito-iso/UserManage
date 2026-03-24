package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * ユーザーマスタ
 * @Entity: このクラスがJPAの管理対象（テーブルと連動するクラス）であることを示す
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Entity
@Table(name = "m_users")
@Data
public class Users {

	/**
	 * ユーザーID
	 * @Id: テーブルの主キーであることを示す
	 * @Column: Javaのフィールド名とDBのカラム名が異なる場合に、対応する名前を指定する
	 */
	@Id
	@Column(name = "user_id")
	private String userId;

	/** パスワード */
	@Column(name = "password")
	private String password;

	/** ユーザー権限 */
	@Column(name = "role")
	private String role;

	/** 苗字 */
	@Column(name = "family_name")
	private String familyName;

	/** 名前 */
	@Column(name = "given_name")
	private String givenName;

	/** 苗字カナ */
	@Column(name = "family_name_kana")
	private String familyNameKana;

	/** 名前カナ */
	@Column(name = "given_name_kana")
	private String givenNameKana;

	/** メールアドレス */
	@Column(name = "mail")
	private String mail;
	
	/** 役職 */
	@Column(name = "position_cd")
	private String positionCd;
	
	/** 内線電話 */
	@Column(name = "extension_tel")
	private String extensionTel;
	
	/** 携帯電話 */
	@Column(name = "mobile_tel")
	private String mobileTel;
	
	/** 誕生日 */
	@Column(name = "birthday")
	private LocalDate birthday;
	
	/** 性別 */
	@Column(name = "gender")
	private String gender;
	
	/** 入社日 */
	@Column(name = "hire_date")
	private LocalDate hireDate;
	
	/** 退職日 */
	@Column(name = "resignation_date")
	private LocalDate resignationDate;

	/** アカウントが有効かどうかを示すフラグ */
	@Column(name = "account_enabled")
	private boolean accountEnabled;

	/** 作成日時 */
	@Column(name = "add_date")
	private LocalDateTime addDate;

	/** 更新日時 */
	@Column(name = "upd_date")
	private LocalDateTime updDate;

	/** 最終ログイン日時 LoginSuccessHandlerで更新されます。 */
	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;

	/**
	 * @OneToMany : 親から子に1:多
	 */
	@OneToMany(mappedBy = "user")
	private List<UsersDepartments> userDepartments;

	/** 表示用フルネームを取得 */
	public String getDisplayName() {

		return this.familyName + " " + this.givenName;
	}

}