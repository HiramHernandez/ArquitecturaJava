package com.muebleria.polizas.repositories;
import com.muebleria.polizas.dto.ConsultaPolizaResult;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IPolizaRepository extends CrudRepository<ConsultaPolizaResult, Integer> {
    @Transactional
    @Procedure(name = "ConsultarPolizaPorEmpleado")
    List<ConsultaPolizaResult> consultarPolizaPorEmpleado(@Param("IdEmpleado") int idEmpleado);

    @Query(value = "EXEC ConsultarPolizaPorEmpleado :IdEmpleado", nativeQuery = true)
    List<ConsultaPolizaResult> consultarPolizaPorEmpleadoWithPrint(@Param("IdEmpleado") int idEmpleado);
}
