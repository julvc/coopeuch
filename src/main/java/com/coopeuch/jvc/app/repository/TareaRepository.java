package com.coopeuch.jvc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coopeuch.jvc.app.entity.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{

}
