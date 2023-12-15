package com.muebleria.polizas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.muebleria.polizas.models.Empleado;

import javax.transaction.Transactional;

@EnableJpaRepositories
@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, String>{
    @Query(value = "Exec SeleccionarTodosEmpleados", nativeQuery = true)
    public List<Empleado> getListEmpleados();

    @Modifying
    @Query(value = "Exec InsertarEmpleado :nombre, :apellido, :puesto", nativeQuery = true)
    @Transactional
    public void saveEmpleado(String nombre, String apellido, String puesto);
}
