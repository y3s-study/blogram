package org.blogram.domain.category;

import org.blogram.domain.common.BaseEntity;
import org.blogram.domain.member.Member;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parent;

	protected Category() {
	}

	private Category(String name, Member member, Category parent) {
		this.name = name;
		this.member = member;
		this.parent = parent;
	}

	public static Category create(String name, Member member, Category parent) {
		return new Category(name, member, parent);
	}
}

