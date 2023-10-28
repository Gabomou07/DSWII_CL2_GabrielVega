package pe.edu.cibertec.DSWII_CL2_GabrielVega.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private Integer categoryid;
    @Column(name = "categoryname")
    private String categoryname;
    @Column(name = "description")
    private String description;
}
