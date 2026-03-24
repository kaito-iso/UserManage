package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 管理者メニュー画面の表示を制御するコントローラー
 */
@Controller
public class AdministratorMenuController {

	/**
	 * ログイン成功後に遷移するメインメニュー画面
	 */
	@GetMapping("/administrator/menu")
	public String view() {
		return "administrator-menu";
	}

}
