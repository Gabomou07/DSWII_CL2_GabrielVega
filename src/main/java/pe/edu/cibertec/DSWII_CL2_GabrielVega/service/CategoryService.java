package pe.edu.cibertec.DSWII_CL2_GabrielVega.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII_CL2_GabrielVega.model.bd.Category;
import pe.edu.cibertec.DSWII_CL2_GabrielVega.repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> listarCategorias(){
        return categoryRepository.findAll();
    }

}
