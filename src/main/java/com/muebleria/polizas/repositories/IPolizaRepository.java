package com.muebleria.polizas.repositories;
import com.muebleria.polizas.dto.ConsultaPolizaResult;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Repository
public interface IPolizaRepository extends CrudRepository<ConsultaPolizaResult, Integer> {

    @Query(value = "EXEC ConsultarPolizaPorEmpleado :IdEmpleado", nativeQuery = true)
    List<ConsultaPolizaResult> consultarPolizaPorEmpleado(@Param("IdEmpleado") int idEmpleado);
    @Query(value = "EXEC ConsultarPoliza :IdPoliza", nativeQuery = true )
    ConsultaPolizaResult consultarPoliza(@Param("IdPoliza") int idPoliza);

    @Query(value = "EXEC ConsultarPolizas", nativeQuery = true)
    List<ConsultaPolizaResult> consultarPolizas();
    @Modifying
    @Query(value = "Exec CrearPoliza :EmpleadoGenero, :SKU, :Cantidad, :FechaModifico, :idPoliza OUTPUT", nativeQuery = true)
    @javax.transaction.Transactional
    public void savePoliza(int EmpleadoGenero, int SKU, int Cantidad, Date FechaModifico, int idPoliza);

    @Modifying
    @Query(value = "Exec EliminarPoliza :idPoliza", nativeQuery = true)
    @Transactional
    public void removePoliza(int idPoliza);
}
