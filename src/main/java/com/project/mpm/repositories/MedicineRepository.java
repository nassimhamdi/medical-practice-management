package com.project.mpm.repositories;

import com.project.mpm.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
    //add medicine
    @Modifying
    @Query(value = "insert into medicines values (:id, :name, :price)",nativeQuery = true)
    int insertIntoMedicineTable( @Param("id")int id,@Param("name")String name,@Param("price")double price);


}
