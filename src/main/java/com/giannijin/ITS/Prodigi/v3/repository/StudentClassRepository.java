package com.giannijin.ITS.Prodigi.v3.repository;

import com.giannijin.ITS.Prodigi.v3.model.StudentClass;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Transactional

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    @Query("SELECT sc FROM StudentClass sc WHERE sc.startYear = :startYear OR sc.endYear = :endYear")
    List<StudentClass> findByStartYearOrEndYear (@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);

    @Query("SELECT sc FROM StudentClass sc WHERE sc.date BETWEEN :startDate AND :endDate")
    List<StudentClass> findByDateBetween (@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT sc FROM StudentClass sc WHERE sc.name = :name")
    List<StudentClass> findByName(@Param("name") String name);


    @Query("SELECT sc FROM StudentClass sc WHERE sc.startYear = :startYear AND sc.endYear = :endYear")
    List<StudentClass> findByStartYearAndEndYear(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);


}



