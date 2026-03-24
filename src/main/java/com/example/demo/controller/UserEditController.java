package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Users;
import com.example.demo.form.UserEditForm;
import com.example.demo.service.DepartmentsService;
import com.example.demo.service.PositionService;
import com.example.demo.service.UserEditService;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報編集画面の表示を行うコントローラー
 */
@Controller
@RequiredArgsConstructor
public class UserEditController {

	private final UsersService usersService;
	private final UserEditService userEditService;
	private final DepartmentsService departmentsService;
	private final PositionService positionService;

	/**
	 * ユーザー情報画面を表示
	 */
	@GetMapping("/users-view/{userId}")
	public String view(@PathVariable String userId, @ModelAttribute UserEditForm userEditForm, Model model) {

		// ユーザー情報取得
		Optional<Users> userOpt = usersService.findByUserId(userId);
		
		if (userOpt.isEmpty()) {
			return "error/user-not-found";
		}

		Users user = userOpt.get();

		// EntityをFormにセットする
		userEditForm.setUserId(user.getUserId());
		userEditForm.setFamilyName(user.getFamilyName());
		userEditForm.setGivenName(user.getGivenName());
		userEditForm.setFamilyNameKana(user.getFamilyNameKana());
		userEditForm.setGivenNameKana(user.getGivenNameKana());
		userEditForm.setMail(user.getMail());
		userEditForm.setPositionCd(user.getPositionCd());
		userEditForm.setExtensionTel(user.getExtensionTel());
		userEditForm.setMobileTel(user.getMobileTel());
		userEditForm.setBirthday(user.getBirthday());
		userEditForm.setGender(user.getGender());
		userEditForm.setHireDate(user.getHireDate());
		userEditForm.setResignationDate(user.getResignationDate());
		userEditForm.setRole(user.getRole());
		userEditForm.setAccountEnabled(user.isAccountEnabled());

		// メイン部署をFormにセット
		user.getUserDepartments().stream()
				.filter(ud -> ud.isMain())
				.findFirst()
				.ifPresent(ud -> userEditForm.setMainDeptCd(ud.getDeptCd()));

		// 表示用データのセット
		setupDisplayData(user, model);

		return "user-edit";
	}

	/**
	 * ユーザー情報の更新
	 */
	@PostMapping("/users-view/{userId}")
	public String update(@PathVariable String userId, @Validated @ModelAttribute UserEditForm userEditForm,
			BindingResult result, Model model) {

		// バリデーションエラー
		if (result.hasErrors()) {
			Optional<Users> userOpt = usersService.findByUserId(userId);
			userOpt.ifPresent(user -> setupDisplayData(user, model));
			return "user-edit";
		}

		userEditService.save(userEditForm);

		// 更新後のデータを取得して表示
		Optional<Users> userOpt = usersService.findByUserId(userId);
		
		if (userOpt.isPresent()) {
			setupDisplayData(userOpt.get(), model);
			model.addAttribute("successMessage", "更新しました。");
		}

		return "user-edit";
	}

	/**
	 * Modelで表示するデータ
	 */
	private void setupDisplayData(Users user, Model model) {

		model.addAttribute("departments", departmentsService.findAll());
		model.addAttribute("userDepartments", user.getUserDepartments());
		model.addAttribute("addDate", user.getAddDate());
		model.addAttribute("updDate", user.getUpdDate());
		model.addAttribute("lastLoginDate", user.getLastLoginDate());
		model.addAttribute("positions",positionService.findAll());
	}
}