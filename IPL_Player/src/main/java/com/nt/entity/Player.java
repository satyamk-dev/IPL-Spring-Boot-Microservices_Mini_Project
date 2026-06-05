package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Player {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "P_IPL", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer playerId;

	@Column(length=30)
	@NonNull
	private String playerName;

	private Integer age;

	@NonNull
	@Column(length=30)
	private String roll;

	@NonNull
	@ManyToOne(targetEntity = Team.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="t_Id", referencedColumnName="teamId")
	private Team team;

	// meta data
	@Version
	private Integer updateCount;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createOn;

	@UpdateTimestamp
	private LocalDateTime lastUpdateOn;

	@Column(length = 30, updatable = false)
	private String createBy;

	@Column(length = 30)
	private String updatedBy;

}
