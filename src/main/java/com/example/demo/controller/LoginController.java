package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.LoginForm;

/**
 * ログイン画面の表示を制御するコントローラー
 */
@Controller
public class LoginController {

	/**
	 * ログイン画面を表示する
	 * @ModelAttribute: 
	 * * 1. 画面表示のときにLoginForm オブジェクトを作成して Model に登録する
	 * * 2. これにより、HTML（Thymeleaf）側の th:object="${loginForm}" と紐付けが可能
	 * * 3. 入力エラーで戻ってきた際に、入力内容を保持する役割も持つ
	 */
	@GetMapping("/login")
	public String view(@ModelAttribute LoginForm loginForm) {
		return "login";
	}
	
	@GetMapping("/")
	public String view() {
		return "redirect:/login";
	}

}