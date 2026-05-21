package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

}
