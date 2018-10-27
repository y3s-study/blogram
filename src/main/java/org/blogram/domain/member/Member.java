package org.blogram.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.blogram.domain.common.BaseEntity;
import org.blogram.domain.role.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	private String email;
	private String password;
	private String name;

	@ManyToMany
	@JoinTable(
			name = "authority",
			joinColumns = @JoinColumn(name = "member_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "last_login_time")
	private LocalDateTime lastLoginTime;

	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new HashSet<>();
		}

		this.roles.add(role);
	}
}
