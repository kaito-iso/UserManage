package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * エラー画面の表示を制御するコントローラー
 */
@Controller
public class ErrorsController {
	
	/**
	 * アクセス権限なしエラー画面
	 */
	@GetMapping("/error/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}

}
