package org.acme.eshop.model;

import lombok.*;

@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
	private String firstname;
	private String lastname;
	private String email;
}
