package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 設定画面の表示を制御するコントローラー
 */
@Controller
public class SettingsController {

	/**
	 * 設定画面を表示する
	 */
	@GetMapping("/settings")
	public String view() {
		return "settings";
	}

}