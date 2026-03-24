package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * パスワード再設定画面用のフォーム
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Data
public class PasswordResetForm {
	
	/** ユーザーID */
	private String userId;

	/** 現在のパスワード */
	@NotBlank(message = "パスワードを入力してください")
	private String currentPassword;

	/** 新しいパスワード */
	@Size(min = 6, max = 32, message = "6～32文字で入力してください")
	private String newPassword1;

	@Size(min = 6, max = 32, message = "6～32文字で入力してください")
	private String newPassword2;

}