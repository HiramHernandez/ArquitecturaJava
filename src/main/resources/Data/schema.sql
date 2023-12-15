USE [POLIZAS]
GO
/****** Object:  Table [dbo].[Empleado]    Script Date: 14/12/2023 05:02:32 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empleado](
	[IdEmpleado] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](255) NULL,
	[Apellido] [nvarchar](255) NULL,
	[Puesto] [nvarchar](255) NULL,
 CONSTRAINT [PK__Empleado__CE6D8B9EE74CD0D9] PRIMARY KEY CLUSTERED
(
	[IdEmpleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Inventario]    Script Date: 14/12/2023 05:02:33 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inventario](
	[IdInventario] [int] IDENTITY(1,1) NOT NULL,
	[SKU] [int] NULL,
	[Nombre] [nvarchar](255) NULL,
	[Cantidad] [int] NULL,
 CONSTRAINT [PK__Inventar__1927B20C20596E0B] PRIMARY KEY CLUSTERED
(
	[IdInventario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UQ__Inventar__CA1ECF0DB25FBDC6] UNIQUE NONCLUSTERED
(
	[SKU] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Polizas]    Script Date: 14/12/2023 05:02:33 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Polizas](
	[IdPoliza] [int] IDENTITY(1,1) NOT NULL,
	[EmpleadoGenero] [int] NULL,
	[SKU] [int] NULL,
	[Cantidad] [int] NULL,
	[Fecha] [date] NULL,
 CONSTRAINT [PK__Polizas__8E3943B32372E887] PRIMARY KEY CLUSTERED
(
	[IdPoliza] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Polizas]  WITH CHECK ADD  CONSTRAINT [FK__Polizas__SKU__3C69FB99] FOREIGN KEY([SKU])
REFERENCES [dbo].[Inventario] ([SKU])
GO
ALTER TABLE [dbo].[Polizas] CHECK CONSTRAINT [FK__Polizas__SKU__3C69FB99]
GO
ALTER TABLE [dbo].[Polizas]  WITH CHECK ADD  CONSTRAINT [FK_Polizas_EmpleadoGenero_Empleado_IdEmpleado] FOREIGN KEY([EmpleadoGenero])
REFERENCES [dbo].[Empleado] ([IdEmpleado])
GO
ALTER TABLE [dbo].[Polizas] CHECK CONSTRAINT [FK_Polizas_EmpleadoGenero_Empleado_IdEmpleado]
GO
/****** Object:  StoredProcedure [dbo].[CrearPoliza]    Script Date: 14/12/2023 05:02:33 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[CrearPoliza]
    @EmpleadoGenero NVARCHAR(255),
    @SKU INT,
    @Cantidad INT,
    @Fecha DATE
AS
BEGIN
    -- Asegurar que no exista una póliza con el mismo ID
    IF NOT EXISTS (SELECT 1 FROM Polizas WHERE IdPoliza = @SKU)
    BEGIN
        INSERT INTO Polizas (EmpleadoGenero, SKU, Cantidad, Fecha)
        VALUES (@EmpleadoGenero, @SKU, @Cantidad, @Fecha);

        -- Restar la cantidad del inventario
        UPDATE Inventario SET Cantidad = Cantidad - @Cantidad WHERE SKU = @SKU;
    END
END;
GO
/****** Object:  StoredProcedure [dbo].[EliminarPoliza]    Script Date: 14/12/2023 05:02:33 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[EliminarPoliza]
    @IdPoliza INT
AS
BEGIN
    -- Obtener información necesaria antes de eliminar la póliza
    DECLARE @SKU INT, @Cantidad INT;
    SELECT @SKU = SKU, @Cantidad = Cantidad FROM Polizas WHERE IdPoliza = @IdPoliza;

    -- Eliminar la póliza
    DELETE FROM Polizas WHERE IdPoliza = @IdPoliza;

    -- Restablecer la cantidad en el inventario correspondiente
    UPDATE Inventario SET Cantidad = Cantidad + @Cantidad WHERE SKU = @SKU;
END;
GO
