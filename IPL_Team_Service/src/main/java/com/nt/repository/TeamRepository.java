package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

	public boolean existsByTeamName(String name);

}
