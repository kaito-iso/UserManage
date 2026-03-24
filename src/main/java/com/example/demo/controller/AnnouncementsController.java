package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Announcements;
import com.example.demo.form.AnnouncementsForm;
import com.example.demo.service.AnnouncementCategoryService;
import com.example.demo.service.AnnouncementsService;

import lombok.RequiredArgsConstructor;

/**
 * お知らせ作成変更画面の表示を制御するコントローラー
 */
@Controller
@RequiredArgsConstructor
public class AnnouncementsController {

	private final AnnouncementsService announcementsService;
	private final AnnouncementCategoryService announcementCategoryService;

	@GetMapping("/announcement/new")
	public String viewNew(@ModelAttribute AnnouncementsForm form, Model model) {

		model.addAttribute("categories", announcementCategoryService.findAll());
		form.setPublishDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

		return "announcement";
	}

	@GetMapping("/announcement/edit/{id}")
	public String viewEdit(@PathVariable Integer id, @ModelAttribute("announcementsForm") AnnouncementsForm form,
			Model model) {

		// お知らせを1件取得
		Announcements announcement = announcementsService.findById(id);

		form.setId(announcement.getId());
		form.setTitle(announcement.getTitle());
		form.setCategoryCd(announcement.getCategoryCd());
		form.setContent(announcement.getContent());
		form.setPublishDate(announcement.getPublishDate());
		form.setExpiresDate(announcement.getExpiresDate());

		model.addAttribute("categories", announcementCategoryService.findAll());

		return "announcement";
	}

	@GetMapping("/announcement/list")
	public String viewList(Model model) {

		model.addAttribute("announcements", announcementsService.findAll());
		return "announcement-list";
	}

	@PostMapping("/announcement/save")
	public String viewSave(@Validated @ModelAttribute AnnouncementsForm form, BindingResult result, Principal principal,
			Model model) {

		model.addAttribute("categories", announcementCategoryService.findAll());

		// バリデーションエラー
		if (result.hasErrors()) {
			return "announcement";
		}

		announcementsService.save(principal.getName(), form);

		return "redirect:/announcement/list";
	}

	@PostMapping("/announcement/delete/{id}")
	public String views(@PathVariable Integer id) {

		announcementsService.deleteById(id);

		return "redirect:/announcement/list";
	}

	@GetMapping("/announcements")
	public String userView(Model model) {
		
		model.addAttribute("announcements", announcementsService.publicFindAll());
		return "announcements";
	}
}
