package com.kruczek.email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailDto {
	private String id;
	private String from;
	private String to;
	private String text;
}
