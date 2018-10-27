package org.blogram.domain.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.blogram.domain.category.Category;
import org.blogram.domain.common.BaseEntity;
import org.blogram.domain.member.Member;

import javax.persistence.*;

@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	private String title;
	private String content;
	private boolean deleted;

	@Column(name = "view_count")
	private Long viewCount;

	@ManyToOne(optional = false)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id")
	private Category category;

	private Post(String title, String content, Member member, Category category) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.category = category;
		this.deleted = false;
		this.viewCount = 0L;
	}

	public static Post create(String title, String content, Member member, Category category) {
		return new Post(title, content, member, category);
	}
}
