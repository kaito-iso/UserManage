package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * ユーザー情報の編集を保持して登録するフォーム
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Data
public class UserEditForm {

	/**
	 * @Size: 文字数の範囲を制限
	 * @NotBlank: 未入力や空文字・スペースのみの場合にエラーにする
	 * message: エラー時に画面に表示する文言を指定する
	 */
	@NotBlank(message = "ユーザーIDは必須です")
	@Size(min = 1, max = 10, message = "1～10文字で入力してください")
	private String userId;

	/** ユーザー権限 : 初期値として "ROLE_USER" を設定 */
	@NotBlank
	private String role = "ROLE_USER";

	@NotBlank(message = "苗字は必須です")
	private String familyName;

	@NotBlank(message = "名前を必須です")
	private String givenName;

	@NotBlank(message = "苗字(カナ)を入力してください")
	@Pattern(regexp = "^[ァ-ヶー]*$", message = "苗字(カナ)は全角カタカナで入力してください")
	private String familyNameKana;

	@NotBlank(message = "名前(カナ)を入力してください")
	@Pattern(regexp = "^[ァ-ヶー]*$", message = "名前(カナ)は全角カタカナで入力してください")
	private String givenNameKana;

	@Email(message = "メールアドレスの形式が正しくありません")
	private String mail;

	/** 役職 */
	private String positionCd;

	/** 内線番号 */
	@Pattern(regexp = "^[0-9-]*$", message = "数字とハイフンのみ入力できます")
	private String extensionTel;

	/** 携帯番号 */
	@Pattern(regexp = "^[0-9-]*$", message = "数字とハイフンのみ入力できます")
	private String mobileTel;

	/** 誕生日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	/** 性別 */
	@Pattern(regexp = "^[MFO]$")
	private String gender;

	/** 入社日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hireDate;

	/** 退職日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate resignationDate;

	/** アカウント有効*/
	private Boolean accountEnabled;

	/** 部署CD */
	private String mainDeptCd;

}
