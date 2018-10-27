package org.blogram.domain.role;

import org.blogram.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	private String name;

	protected Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public static Role create(String name) {
		return new Role(name);
	}
}
