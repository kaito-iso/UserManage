package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Users;
import com.example.demo.form.SignUpForm;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面の表示および検索制御を行うコントローラー
 */
@Controller
@RequiredArgsConstructor
public class UsersViewController {

	private final UsersService usersService;

	/**
	 * ユーザー一覧画面を表示し、指定された条件で検索結果を表示する
	 */
	@GetMapping("/users-view")
	public String view(@ModelAttribute SignUpForm usersForm, Model model) {

		// 検索条件がない場合(空文字)は全件取得
		List<Users> users = usersService.findUsers(usersForm.getSearchName());

		model.addAttribute("users", users);

		// 検索窓の値を保持
		model.addAttribute("usersForm", usersForm);

		return "users-view";
	}

	/**
	 * ユーザー削除後のユーザー一覧画面を表示
	 */
	@PostMapping("/users-view/{userId}/delete")
	public String view(@PathVariable String userId, @ModelAttribute SignUpForm usersForm, Model model,
			Principal principal, HttpServletRequest request) {
		
		// ユーザー削除
		usersService.deleteByUserId(userId);

		// 削除したのが「自分（ログイン中ユーザー）」か判定
		if (principal != null && principal.getName().equals(userId)) {
			
			// 強制ログアウト
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			SecurityContextHolder.clearContext();
			return "redirect:/login?selfDeleted";
		}

		// 検索条件がない場合(空文字)は全件取得
		List<Users> users = usersService.findUsers(usersForm.getSearchName());

		model.addAttribute("users", users);

		return "redirect:/users-view";
	}
}
