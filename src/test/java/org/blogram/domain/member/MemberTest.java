package org.blogram.domain.member;

import org.blogram.domain.role.Role;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTest {

	private Member sut;

	@Before
	public void setUp() {
		sut = new Member();
	}

	@Test
	public void addSingleRole() {
		// given
		Role testRole = Role.create("TEST_ROLE");

		// when
		sut.addRole(testRole);

		// then
		assertThat(sut.getRoles()).contains(testRole);
	}

	@Test
	public void addMultipleRoles() {
		// given
		Role userRole = Role.create("USER");
		Role adminRole = Role.create("ADMIN");

		// when
		sut.addRole(userRole);
		sut.addRole(adminRole);

		// then
		assertThat(sut.getRoles())
				.contains(userRole)
				.contains(adminRole);
	}
}