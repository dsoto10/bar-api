package com.init.bar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.bar.model.Bar;

public interface BarDAO extends JpaRepository<Bar, Integer> {

}
