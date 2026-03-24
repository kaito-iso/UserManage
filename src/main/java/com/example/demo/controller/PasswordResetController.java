package com.example.demo.controller;

import java.security.Principal;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.exception.UserEditException;
import com.example.demo.form.PasswordResetForm;
import com.example.demo.form.PasswordResetMailForm;
import com.example.demo.service.PasswordResetService;

import lombok.RequiredArgsConstructor;

/**
 * パスワードリセット画面の表示を制御するコントローラー
 */
@Controller
@RequiredArgsConstructor
public class PasswordResetController {

	private final PasswordResetService passwordResetService;

	/**
	 * 画面表示
	 * @ModelAttribute:
	 * * 1. 画面表示のときにPasswordResetForm オブジェクトを作成して Model に登録する
	 * * 2. これにより、HTML（Thymeleaf）側の th:object="${passwordResetForm}" と紐付けが可能
	 * * 3. 入力エラーで戻ってきた際に、入力内容を保持する役割も持つ
	 */
	@GetMapping("/password-reset-mail")
	public String getView(PasswordResetMailForm passwordResetMailForm) {
		return "password-reset-mail";
	}

	@GetMapping("/password-reset")
	public String getView(PasswordResetForm passwordResetForm, Model model) {

		return "password-reset";
	}

	/**
	 * 送信処理
	 * @ModelAttribute:
	 * * HTMLから届いた入力値を自動的にFormオブジェクトの各フィールドに詰め込み
	 * * エラー時に値を保持したまま画面に戻す役割
	 * @Valid:
	 * * Formクラスに記述された制約（@NotBlankや@Emailなど）に従って、
	 * * 詰め込まれた値が正しい形式かどうかを確認する
	 * * 確認結果（エラーの有無）は直後の BindingResult に格納される
	 */
	@PostMapping("/password-reset-mail")
	public String postView(@Valid @ModelAttribute PasswordResetMailForm passwordResetMailForm, BindingResult result,
			Model model) {

		// バリデーションエラー
		if (result.hasErrors()) {
			return "password-reset-mail";
		}

		// passwordResetServiceを使ってメールアドレスの存在チェック
		var userOpt = passwordResetService.findByMail(passwordResetMailForm.getMail());

		// メールアドレスがある場合
		if (userOpt.isPresent()) {

			model.addAttribute("successMessage", "指定のメールアドレスに送信しました");
		} else {

			model.addAttribute("duplicationError", "入力されたメールアドレスは登録されていません");
		}

		return "password-reset-mail";
	}

	@PostMapping("/password-reset")
	public String postView(Model model, @Validated @ModelAttribute PasswordResetForm passwordResetForm,
			BindingResult result,
			Principal principal,
			RedirectAttributes redirectAttributes) {

		// バリデーションエラー
		if (result.hasErrors()) {
			return "password-reset";
		}

		passwordResetForm.setUserId(principal.getName());

		try {
			passwordResetService.save(passwordResetForm);
		} catch (UserEditException e) {

			result.rejectValue(e.getFieldName(), null, e.getMessage());
			return "password-reset";

		}

		redirectAttributes.addFlashAttribute("successMessage", "パスワードを更新しました。");
		return "redirect:/password-reset";
	}

}