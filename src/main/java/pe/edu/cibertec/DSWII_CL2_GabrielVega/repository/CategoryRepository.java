package pe.edu.cibertec.DSWII_CL2_GabrielVega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII_CL2_GabrielVega.model.bd.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
