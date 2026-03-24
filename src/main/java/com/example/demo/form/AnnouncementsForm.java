package com.example.demo.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * お知らせの作成&変更のフォーム
 * @Data: Lombokの機能で、Getter/Setter/toStringなどを自動生成
 */
@Data
public class AnnouncementsForm {

	/** ID */
	private Integer id;

	/** タイトル */
	@NotBlank(message = "タイトルは必須です")
	@Size(max = 100, message = "タイトルは100文字以内で入力してください")
	private String title;

	/** 内容 */
	@NotBlank(message = "内容は必須です")
	@Size(max = 500, message = "内容は500文字以内で入力してください")
	private String content;

	/** カテゴリーCD */
	//@NotBlank(message = "カテゴリーを選択してください")
	private String categoryCd;

	/** 公開開始日 */
	@NotNull(message = "公開開始日時を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime publishDate;

	/** 公開終了日 */
	@Future(message = "公開終了日は現在より後の日時を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime expiresDate;
}
