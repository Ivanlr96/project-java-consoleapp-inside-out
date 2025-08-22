# Mi Diario - Aplicación de Consola

**Descripción:**
Mi Diario es una aplicación de consola que permite a los usuarios gestionar sus momentos vividos. Cada momento tiene una emoción asignada y registra tanto la fecha en la que ocurrió como las fechas de creación y modificación del registro.

## Estructura de un momento vivído
- Identificador único
- Título
- Descripción
- Emoción
- Fecha del momento
- Fecha de creación
- Fecha de modificación

## Emociones disponibles
- Alegría
- Tristeza
- Ira
- Asco
- Miedo
- Ansiedad
- Envidia
- Vergüenza
- Aburrimiento
- Nostalgia

## Historias de usuario y criterios de aceptación

1. **Añadir un momento vivido**
   - **Como:** usuario
   - **Quiero:** añadir un momento vivido
   - **Para poder:** visualizarlo cuando lo necesite recordar
   - **Criterios de aceptación:**
     - Se debe ingresar título, descripción, emoción y fecha del momento.
     - El momento se guarda con un identificador único y fecha de creación.

2. **Recuperar la lista de momentos vividos**
   - **Como:** usuario
   - **Quiero:** recuperar la lista de los momentos vividos registrados
   - **Para poder:** repasarlos
   - **Criterios de aceptación:**
     - Se debe mostrar la lista completa con todos los campos de cada momento.
     - La lista debe estar ordenada por fecha de creación o por fecha del momento.

3. **Suprimir un momento vivido**
   - **Como:** usuario
   - **Quiero:** suprimir un momento vivido
   - **Para poder:** evitar duplicados y mantener la lista organizada
   - **Criterios de aceptación:**
     - El usuario debe poder seleccionar el momento por su identificador.
     - El momento eliminado no debe aparecer más en la lista.

4. **Filtrar momentos por emoción**
   - **Como:** usuario
   - **Quiero:** obtener los momentos vividos según su emoción
   - **Para poder:** visualizarlos
   - **Criterios de aceptación:**
     - El usuario selecciona una emoción.
     - Se muestran todos los momentos que coinciden con la emoción seleccionada.

5. **Filtrar momentos por fecha o mes**
   - **Como:** usuario
   - **Quiero:** obtener los momentos vividos en un mes determinado
   - **Para poder:** revisarlos
   - **Criterios de aceptación:**
     - El usuario ingresa un mes y un año.
     - Se muestran todos los momentos ocurridos en ese período.

6. **Salir del programa**
   - **Como:** usuario
   - **Quiero:** salir del programa
   - **Para poder:** iniciar otro
   - **Criterios de aceptación:**
     - El programa debe cerrarse de manera segura al seleccionar la opción de salida.

##  Pre-requisitos

Antes de ejecutar este proyecto asegúrate de tener instalado:

- [Java 17 o superior](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/) (para gestionar dependencias y compilar el proyecto)

## Pasos para la instalación

1. Clonar este repositorio:
   ```bash
   git clone https://github.com/Ivanlr96/project-java-consoleapp-inside-out
   cd project-java-consoleapp-inside-out
   Ejecutar el programa desde App.java


## Diagrama de Clases

```mermaid
classDiagram
    %% MODELOS
    class Moment {
        -String title
        -LocalDate date
        -String description
        -EmotionEnum emotion
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
        +Moment(String, LocalDate, String, EmotionEnum)
        +getTitle() String
        +getDate() LocalDate
        +getDescription() String
        +getEmotionEnum() EmotionEnum
        +getCreatedAt() LocalDateTime
        +getUpdatedAt() LocalDateTime
        +setUpdatedAt() void
        +toString() String
    }

    class EmotionEnum {
        <<enumeration>>
        ALEGRIA
        TRISTEZA
        IRA
        ASCO
        MIEDO
        ANSIEDAD
        ENVIDIA
        VERGUENZA
        ABURRIMIENTO
        NOSTALGIA
        +getDisplayName() String
    }

    %% DTOs
    class MomentDTO {
        +title String
        +date LocalDate
        +description String
        +emotion EmotionEnum
    }

    class MomentResponseDTO {
        +title String
        +date String
        +description String
        +emotion EmotionEnum
    }

    %% MAPPERS
    class MomentMapper {
        +toEntity(MomentDTO) Moment
    }

    class MomentResponseMapper {
        +toResponseDTO(Moment) MomentResponseDTO
    }

    %% BASE DE DATOS
    class InterfaceDatabase~T~ {
        +store(T) void
        +getAll() List~T~
        +delete(int) boolean
    }

    class DiaryDatabase {
        -List~Moment~ moments
        +store(Moment) void
        +getAll() List~Moment~
        +delete(int) boolean
    }

    InterfaceDatabase <|.. DiaryDatabase

    %% REPOSITORIO
    class MomentRepository {
        -InterfaceDatabase~Moment~ db
        +MomentRepository()
        +setDb(String) void
        +storeMoment(Moment) void
        +getAllMoments() List~Moment~
        +deleteMoment(int) boolean
        +getMomentsByEmotion(EmotionEnum) List~Moment~
        +getMomentsByDate(LocalDate) List~Moment~
    }

    %% SINGLETONS
    class MomentControllerSingleton {
        -static MomentController instance
        +getInstance() MomentController
    }

    class MomentRepositorySingleton {
        -static MomentRepository INSTANCE
        +getInstance() MomentRepository
        +resetInstance() void
    }

    %% CONTROLADOR
    class MomentController {
        -MomentRepository repository
        +MomentController()
        +storeMoment(MomentDTO) void
        +getAllMoments() List~MomentResponseDTO~
        +showAllMoments() void
        +deleteMoment(int) boolean
        +showMomentsByEmotion(EmotionEnum) List~MomentResponseDTO~
        +getMomentsByDate(LocalDate) List~MomentResponseDTO~
    }

    %% VISTAS
    class View {
        -static SCANNER Scanner
    }

    class HomeView {
        +printMenu() void
    }

    class MomentPostView {
        +printStoreMenu() void
    }

    class AllMomentsView {
        +printMoments(List~MomentResponseDTO~) void
    }

    class MomentFilterView {
        +printFilterMenu() void
    }

    class MomentFilterByDateView {
        +printFilterMenu() void
    }

    class MomenFilterByEmotionView {
        +printFilterMenu() void
    }

    class MomentDeleteView {
        +printDeleteMenu() void
    }

    %% RELACIONES
    MomentController --> MomentRepository
    MomentController --> MomentDTO
    MomentController --> MomentResponseDTO
    MomentRepository --> InterfaceDatabase
    MomentRepository --> DiaryDatabase
    DiaryDatabase --> Moment
    MomentMapper --> Moment
    MomentMapper --> MomentDTO
    MomentResponseMapper --> Moment
    MomentResponseMapper --> MomentResponseDTO
    HomeView --> MomentController
    MomentPostView --> MomentController
    AllMomentsView --> MomentResponseDTO
    MomentFilterView --> MomenFilterByEmotionView
    MomentFilterView --> MomentFilterByDateView
    MomentFilterByDateView --> MomentController
    MomenFilterByEmotionView --> MomentController
    MomentDeleteView --> MomentController
    View <|-- HomeView
    View <|-- MomentPostView
    View <|-- AllMomentsView
    View <|-- MomentFilterView
    View <|-- MomentFilterByDateView
    View <|-- MomenFilterByEmotionView
    View <|-- MomentDeleteView

```

## Tests
![Tests](image.png)

## Autor:
Iván Lorenzo
