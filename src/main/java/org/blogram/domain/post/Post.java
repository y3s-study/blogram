package org.blogram.domain.post;

import org.blogram.domain.common.BaseEntity;
import org.blogram.domain.member.Member;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	private String title;
	private boolean deleted;
	private Long viewCount;

	@ManyToOne(optional = false)
	@JoinColumn(name = "member_id")
	private Member member;
}
