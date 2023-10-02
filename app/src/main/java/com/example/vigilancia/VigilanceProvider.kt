package com.example.vigilancia

class VigilanceProvider {
    companion object{
        val vigilanceList = listOf<VigilanciasProgramadas>(
            VigilanciasProgramadas(
                institucion = "Universidad Autonoma de Guadalajara",
                direccion = "Av. Tonaltecas 332",
                clave = "SICYT.SES.DGIS.V0000",
                photo = "https://cdn.milenio.com/uploads/media/2020/09/02/une-tampico_0_23_1200_745.jpg",
                status = 1),

            VigilanciasProgramadas(
                institucion = "Uteg",
                direccion = "Av. Zapopan 2342",
                clave = "SICYT.SES.DGIS.V0001",
                photo = "https://sic.cultura.gob.mx/images/63159",
                status = 1),
            VigilanciasProgramadas(
                institucion = "SICYT",
                direccion = "Av. Guadalajara 2350",
                clave = "SICYT.SES.DGIS.V0002",
                photo = "https://img.gruporeforma.com/imagenes/420x280/1/941/940342.jpg",
                status = 1),
            VigilanciasProgramadas(
                institucion = "Universidad Autonoma de Guadalajara",
                direccion = "Av. Tonaltecas 332",
                clave = "SICYT.SES.DGIS.V0003",
                photo = "https://cdn.milenio.com/uploads/media/2020/09/02/une-tampico_0_23_1200_745.jpg",
                status = 2),
            VigilanciasProgramadas(
                institucion = "Universidad Autonoma de Guadalajara",
                direccion = "Av. Tonaltecas 332",
                clave = "SICYT.SES.DGIS.V0004",
                photo = "https://cdn.milenio.com/uploads/media/2020/09/02/une-tampico_0_23_1200_745.jpg",
                status = 3),
        )
    }
}