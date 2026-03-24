package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.HolidayService;

import lombok.RequiredArgsConstructor;

/**
 * メニュー画面（ホーム）の表示を制御するコントローラー
 */
@Controller
@RequiredArgsConstructor
public class MenuController {
	
	private final HolidayService holidayService;

	/**
	 * ログイン成功後に遷移するメインメニュー画面
	 */
	@GetMapping("/menu")
	public String view(Model model) {
		
		String today = LocalDate.now().toString();
		String holidayName = holidayService.getHolidayName(today);
        
		model.addAttribute("today", today);
	    model.addAttribute("holidayName", holidayName);
	    
		return "menu";
	}

}