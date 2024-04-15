package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Staff;

public interface StaffRepository extends JpaRepository<Staff,String> {

boolean existsByNameAndEmployeeIdAndEmployeeRoleAndPassword(String name, String id, String role, String password);


}
