package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * ログイン画面用のフォーム
 * userIdとpasswordを保持してSpring Securityの認証プロセスに渡す
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Data
public class LoginForm {
	
	/**
	 * @NotBlank: 未入力や空文字・スペースのみの場合にエラーにする
	 */
	@NotBlank
	private String userId;
	
	/**
	 * @NotBlank: 未入力や空文字・スペースのみの場合にエラーにする
	 */
	@NotBlank
	private String password;
	
}