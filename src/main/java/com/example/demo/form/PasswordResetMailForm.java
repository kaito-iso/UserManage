package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * パスワード再設定画面用のフォーム
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Data
public class PasswordResetMailForm {

	/**
	 * @NotBlank: 未入力や空文字・スペースのみの場合にエラーにする
	 * message: エラー時に画面に表示する文言を指定する
	 */
	@NotBlank(message = "メールアドレスを入力してください")
	private String mail;

}