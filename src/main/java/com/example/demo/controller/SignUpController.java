package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.SignUpForm;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.DepartmentsService;
import com.example.demo.service.PositionService;
import com.example.demo.service.SignUpService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー新規登録画面の表示を制御するコントローラー
 */
@Controller
@RequiredArgsConstructor
public class SignUpController {

	private final UsersRepository usersRepository;
	private final SignUpService signUpService;
	private final DepartmentsService departmentsService;
	private final PositionService positionService; 

	/**
	 * 新規登録画面の表示 (GET)
	 */
	@GetMapping("/sign-up")
	public String view(SignUpForm usersForm,Model model) {
		
		model.addAttribute("departments", departmentsService.findAll());
		model.addAttribute("positions",positionService.findAll());
		
		return "sign-up";
	}

	/**
	 * 新規登録処理 (POST)
	 * @Validated:
	 * * Formクラス（usersForm）に定義された制約をチェックする
	 */
	@PostMapping("/sign-up")
	public String view(@Valid SignUpForm usersForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		// バリデーションエラー
		if (result.hasErrors()) {
			model.addAttribute("departments", departmentsService.findAll());
			model.addAttribute("positions",positionService.findAll());
			return "sign-up";
		}

		// ID重複チェック
		if (usersRepository.findByUserId(usersForm.getUserId()).isPresent()) {
			model.addAttribute("duplicationError", "このユーザーIDは既に使用されています");
			model.addAttribute("departments", departmentsService.findAll());
			return "sign-up";
		}

		// 保存実行
		signUpService.save(usersForm);

		/*
         * RedirectAttributes:
         * 1. 通常、リダイレクト（URLの強制移動）を行うと、Model内のデータは消失する。
         * 2. addFlashAttribute を使うと、リダイレクト先の画面が表示されるまで
         * データをセッションに一時保存し、一度だけ画面に表示させることができる。
         * 3. 表示後は自動的に削除されるため、ブラウザの更新ボタンを押しても
         * メッセージが二重に表示される心配がない。
         */
		redirectAttributes.addFlashAttribute("successMessage", "ユーザー「" + usersForm.getDisplayName() + "」さんの新規登録が完了しました！");
		
		return "redirect:/sign-up";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// ""や全角スペースや未入力の場合は、null として扱う設定
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}