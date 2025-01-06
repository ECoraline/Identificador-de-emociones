# Identificador de Emociones

Este proyecto es una aplicación desarrollada en **Java** para identificar emociones a partir de texto mediante el uso de una API de inteligencia artificial. Fue creado como parte de un proyecto propio.

## Descripción

La aplicación analiza entradas de texto proporcionadas por el usuario y consulta una API de IA para determinar la emoción predominante en el texto. Las emociones detectadas pueden incluir alegría, tristeza, enojo, sorpresa, entre otras.

## Características

- **Procesamiento de texto**: Analiza texto ingresado por el usuario para identificar emociones.
- **Integración con API de IA**: Envía datos a una API externa y procesa la respuesta.
- **Código modular**: Estructura clara y organizada en clases.

## Requisitos

- **Java** 11 o superior
- Un IDE como IntelliJ IDEA, Eclipse, o NetBeans (opcional)
- Conexión a internet para la consulta a la API

## Instalación

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone https://github.com/ECoraline/Identificador-de-emociones.git
2. Abre el proyecto en tu IDE favorito o compílalo desde la línea de comandos.

3. Asegúrate de tener configuradas las dependencias necesarias, como cualquier biblioteca externa usada (por ejemplo, Gson para procesar JSON).
## Configuración de la API
1. Registra una cuenta en la plataforma de la API de IA que utiliza este proyecto.
2. Obtén tu clave API.
3. Abre el archivo ConsultaAPI.java y reemplaza el valor de la clave API directamente en la clase:
  ```bash
  private static final String API_KEY = "TU_CLAVE_API";
  ```
Asegúrate de que el resto del código esté configurado para enviar la clave API junto con las solicitudes HTTP.

## Contribuciones
Las contribuciones son bienvenidas. Si deseas colaborar, sigue estos pasos:

Haz un fork del repositorio.
- Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
- Realiza tus cambios y haz commit de ellos (git commit -m 'Añadir nueva funcionalidad').
- Haz push a la rama (git push origin feature/nueva-funcionalidad).
- Abre un Pull Request.

## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](./LICENSE) para más detalles.

## Créditos
Desarrollado por Uriel Aguirre.

## Agradecimientos

Este proyecto utiliza la API de **Cohere** para la identificación de emociones a partir de texto. Gracias a **Cohere** por proporcionar una API accesible para pruebas, que ha sido esencial para el funcionamiento de este proyecto.

Puedes obtener más información sobre Cohere en su [sitio web oficial](https://cohere.ai).


