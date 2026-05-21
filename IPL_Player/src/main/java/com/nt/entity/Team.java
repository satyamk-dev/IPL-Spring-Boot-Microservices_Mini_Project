package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "IPL_TEAM")
@AllArgsConstructor
@NoArgsConstructor
public class Team {

	@Id
	// @SequenceGenerator(name = "gen1", sequenceName = "TID_SEQ", initialValue = 1,
	// allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer teamId;

	@Column(length = 25)
	private String teamName;

	@Column(length = 25)
	private String teamOwner;

	// meta data

	@Version
	private Integer updateCount;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime lastUpdateOn;

	@Column(length = 30, updatable = false)
	private String createBy;

	@Column(length = 30, insertable = false)
	private String updateBy;

}
