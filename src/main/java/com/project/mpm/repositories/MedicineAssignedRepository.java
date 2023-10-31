package com.project.mpm.repositories;
import com.project.mpm.entities.MedicineAssigned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineAssignedRepository extends JpaRepository<MedicineAssigned,Integer> {
    //	pat_id, medicine_id, prescription, medicine_qty
    @Modifying
    @Query(value= "insert into medicines_assigned values(0, :patId,:medicineId,:prescription,:medicineQty)",nativeQuery = true)
    int addIntoMedicineAssigned(@Param("patId") int patId,@Param("medicineId") int medicineId,@Param("prescription") String prescription,@Param("medicineQty")int medicineQty);

}
