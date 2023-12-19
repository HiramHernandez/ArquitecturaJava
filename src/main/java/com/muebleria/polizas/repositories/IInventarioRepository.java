package com.muebleria.polizas.repositories;
import com.muebleria.polizas.dto.ConsultaInventarioResult;

import java.util.Date;
import java.util.List;

import com.muebleria.polizas.dto.ConsultaPolizaResult;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Repository
public interface IInventarioRepository extends CrudRepository<ConsultaInventarioResult, Integer>{

    @Query(value = "EXEC SeleccionarTodoInventario", nativeQuery = true)
    List<ConsultaInventarioResult> consultarInventario();

}
