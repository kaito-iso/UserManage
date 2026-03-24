package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニュー画面（ホーム）の表示を制御するコントローラー
 */
@Controller
public class MenuController {

	/**
	 * ログイン成功後に遷移するメインメニュー画面
	 */
	@GetMapping("/menu")
	public String view(Model model) {
		
		String today = LocalDate.now().toString();
        
		model.addAttribute("today", today);
	    model.addAttribute("holidayName", "TEST祝日");
	    
		return "menu";
	}

}