package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "IPL_TEAM")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer teamId;

	@NonNull
	@Column(length = 30)
	private String teamName;

	@NonNull
	@Column(length = 30)
	private String teamOwner;

	// meta date

	@Version
	private Integer updateCount;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createOn;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime lastUpdateOn;

	@Column(length = 30, updatable = false)
	private String createBy;

	@Column(length = 30, insertable = false)
	private String updatedBy;

}
