package com.example.dadu.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dadu.db";
    public static final String TABLE_MATHEMATICS = "mathematics";
    public static final String TABLE_GEOGRAPHY = "geography";
    public static final String TABLE_HISTORY = "history";
    public static final String TABLE_SPORTS = "sports";
    public static final String TABLE_SCIENCES = "sciences";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_MATHEMATICS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "question TEXT NOT NULL," +
                "option1 TEXT NOT NULL," + // Correct answer
                "option2 TEXT NOT NULL," +
                "option3 TEXT NOT NULL," +
                "option4 TEXT NOT NULL," +
                "image TEXT DEFAULT '')");

        db.execSQL("CREATE TABLE " + TABLE_GEOGRAPHY + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "question TEXT NOT NULL," +
                "option1 TEXT NOT NULL," + // Correct answer
                "option2 TEXT NOT NULL," +
                "option3 TEXT NOT NULL," +
                "option4 TEXT NOT NULL," +
                "image TEXT DEFAULT '')");

        db.execSQL("CREATE TABLE " + TABLE_HISTORY + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "question TEXT NOT NULL," +
                "option1 TEXT NOT NULL," + // Correct answer
                "option2 TEXT NOT NULL," +
                "option3 TEXT NOT NULL," +
                "option4 TEXT NOT NULL," +
                "image TEXT DEFAULT '')");

        db.execSQL("CREATE TABLE " + TABLE_SPORTS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "question TEXT NOT NULL," +
                "option1 TEXT NOT NULL," + // Correct answer
                "option2 TEXT NOT NULL," +
                "option3 TEXT NOT NULL," +
                "option4 TEXT NOT NULL," +
                "image TEXT DEFAULT '')");

        db.execSQL("CREATE TABLE " + TABLE_SCIENCES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "question TEXT NOT NULL," +
                "option1 TEXT NOT NULL," + // Correct answer
                "option2 TEXT NOT NULL," +
                "option3 TEXT NOT NULL," +
                "option4 TEXT NOT NULL," +
                "image TEXT DEFAULT '')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_MATHEMATICS);
        db.execSQL("DROP TABLE " + TABLE_GEOGRAPHY);
        db.execSQL("DROP TABLE " + TABLE_HISTORY);
        db.execSQL("DROP TABLE " + TABLE_SPORTS);
        db.execSQL("DROP TABLE " + TABLE_SCIENCES);
        onCreate(db);
    }

    public void insertMathematics(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Pregunta 1
        values.put("question", "¿Cuál es el número que sigue en la secuencia: 1, 4, 9, 16, ...?");
        values.put("option1", "25"); // Correcta
        values.put("option2", "36");
        values.put("option3", "49");
        values.put("option4", "64");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 2
        values.put("question", "Si 5 personas pueden construir 5 casas en 5 días, ¿cuánto tiempo tomaría que 10 personas construyan 10 casas?");
        values.put("option1", "5 días"); // Correcta
        values.put("option2", "10 días");
        values.put("option3", "20 días");
        values.put("option4", "50 días");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 3
        values.put("question", "Si tienes un cubo y pintas todas sus caras de rojo, luego lo cortas en 27 cubos más pequeños, ¿cuántos de estos cubos tendrán al menos una cara roja?");
        values.put("option1", "9"); // Correcta
        values.put("option2", "18");
        values.put("option3", "21");
        values.put("option4", "27");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 4
        values.put("question", "Si tienes un círculo con un diámetro de 6 cm, ¿cuál es su área?");
        values.put("option1", "9π"); // Correcta
        values.put("option2", "12π");
        values.put("option3", "18π");
        values.put("option4", "36π");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 5
        values.put("question", "Si x + 1 = 5, ¿cuál es el valor de x?");
        values.put("option1", "4"); // Correcta
        values.put("option2", "6");
        values.put("option3", "9");
        values.put("option4", "10");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 6
        values.put("question", "¿Cuál es el resultado de la siguiente operación? √16 + 5 - 2");
        values.put("option1", "9"); // Correcta
        values.put("option2", "13");
        values.put("option3", "16");
        values.put("option4", "19");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 7
        values.put("question", "Si tienes un número entero y le sumas 5, luego le restas 3 y finalmente lo divides por 2, ¿cuál será el resultado?");
        values.put("option1", "La mitad del número original"); // Correcta
        values.put("option2", "El número original");
        values.put("option3", "2 veces el número original");
        values.put("option4", "El número original más 1");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 8
        values.put("question", "¿Cuál es la suma de todos los ángulos internos de un triángulo?");
        values.put("option1", "180 grados"); // Correcta
        values.put("option2", "270 grados");
        values.put("option3", "360 grados");
        values.put("option4", "450 grados");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 9
        values.put("question", "Si un pastel se puede dividir en 8 pedazos con 3 cortes rectos, ¿cuántos pedazos se pueden obtener con 5 cortes rectos?");
        values.put("option1", "16"); // Correcta
        values.put("option2", "20");
        values.put("option3", "24");
        values.put("option4", "28");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);

        // Pregunta 10
        values.put("question", "Si un autobús tiene 50 asientos y en cada asiento hay 2 personas, ¿cuántas piernas hay en total dentro del autobús?");
        values.put("option1", "100"); // Correcta
        values.put("option2", "150");
        values.put("option3", "200");
        values.put("option4", "250");
        values.put("image", "");
        db.insert(TABLE_MATHEMATICS, null, values);
    }

    public void insertGeography(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Pregunta 1
        values.put("question", "¿Cuál es el río más largo del mundo?");
        values.put("option1", "Amazonas"); // Correcta
        values.put("option2", "Nilo");
        values.put("option3", "Yangtsé");
        values.put("option4", "Mississippi");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 2
        values.put("question", "¿Cuál es el país más grande del mundo en superficie?");
        values.put("option1", "Rusia"); // Correcta
        values.put("option2", "Canadá");
        values.put("option3", "China");
        values.put("option4", "Estados Unidos");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 3
        values.put("question", "¿Cuál es el punto más alto del planeta Tierra?");
        values.put("option1", "Monte Everest"); // Correcta
        values.put("option2", "Monte Kilimanjaro");
        values.put("option3", "Monte Aconcagua");
        values.put("option4", "Monte McKinley");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 4
        values.put("question", "¿Cuál es el océano más grande del mundo?");
        values.put("option1", "Océano Pacífico"); // Correcta
        values.put("option2", "Océano Atlántico");
        values.put("option3", "Océano Índico");
        values.put("option4", "Océano Ártico");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 5
        values.put("question", "¿Cuál es la capital de Australia?");
        values.put("option1", "Camberra"); // Correcta
        values.put("option2", "Sídney");
        values.put("option3", "Melbourne");
        values.put("option4", "Brisbane");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 6
        values.put("question", "¿Cuál es el país con mayor población en el mundo?");
        values.put("option1", "China"); // Correcta
        values.put("option2", "India");
        values.put("option3", "Estados Unidos");
        values.put("option4", "Indonesia");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 7
        values.put("question", "¿Cuál es el desierto más grande del mundo?");
        values.put("option1", "Sahara"); // Correcta
        values.put("option2", "Gobi");
        values.put("option3", "Desierto de Arabia");
        values.put("option4", "Desierto del Kalahari");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 8
        values.put("question", "¿Cuál es el país con mayor cantidad de islas en el mundo?");
        values.put("option1", "Suecia"); // Correcta
        values.put("option2", "Filipinas");
        values.put("option3", "Canadá");
        values.put("option4", "Indonesia");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 9
        values.put("question", "¿Cuál es la montaña más alta de África?");
        values.put("option1", "Monte Kilimanjaro"); // Correcta
        values.put("option2", "Monte Elbrus");
        values.put("option3", "Monte Aconcagua");
        values.put("option4", "Monte McKinley");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);

        // Pregunta 10
        values.put("question", "¿Cuál es el país con mayor cantidad de fronteras en el mundo?");
        values.put("option1", "China"); // Correcta
        values.put("option2", "Brasil");
        values.put("option3", "Rusia");
        values.put("option4", "India");
        values.put("image", "");
        db.insert(TABLE_GEOGRAPHY, null, values);
    }

    public void insertHistory(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Pregunta 1
        values.put("question", "¿En qué año comenzó la Primera Guerra Mundial?");
        values.put("option1", "1914"); // Correcta
        values.put("option2", "1917");
        values.put("option3", "1919");
        values.put("option4", "1921");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 2
        values.put("question", "¿Quién fue el primer presidente de Estados Unidos?");
        values.put("option1", "George Washington"); // Correcta
        values.put("option2", "Thomas Jefferson");
        values.put("option3", "Abraham Lincoln");
        values.put("option4", "John F. Kennedy");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 3
        values.put("question", "¿Cuál fue el último territorio en ser anexado a los Estados Unidos?");
        values.put("option1", "Hawái"); // Correcta
        values.put("option2", "Alaska");
        values.put("option3", "Puerto Rico");
        values.put("option4", "Guam");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 4
        values.put("question", "¿En qué año se firmó la Declaración de Independencia de los Estados Unidos?");
        values.put("option1", "1776"); // Correcta
        values.put("option2", "1781");
        values.put("option3", "1790");
        values.put("option4", "1802");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 5
        values.put("question", "¿Cuál fue el líder del movimiento de los derechos civiles en Estados Unidos?");
        values.put("option1", "Martin Luther King Jr."); // Correcta
        values.put("option2", "Malcolm X");
        values.put("option3", "Rosa Parks");
        values.put("option4", "Harriet Tubman");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 6
        values.put("question", "¿En qué año terminó la Segunda Guerra Mundial?");
        values.put("option1", "1945"); // Correcta
        values.put("option2", "1942");
        values.put("option3", "1948");
        values.put("option4", "1950");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 7
        values.put("question", "¿Cuál fue el primer país en enviar un ser humano al espacio?");
        values.put("option1", "Unión Soviética"); // Correcta
        values.put("option2", "Estados Unidos");
        values.put("option3", "China");
        values.put("option4", "Reino Unido");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 8
        values.put("question", "¿En qué año cayó el Muro de Berlín?");
        values.put("option1", "1989"); // Correcta
        values.put("option2", "1991");
        values.put("option3", "1985");
        values.put("option4", "1993");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 9
        values.put("question", "¿Quién escribió la obra 'Romeo y Julieta'?");
        values.put("option1", "William Shakespeare"); // Correcta
        values.put("option2", "Jane Austen");
        values.put("option3", "F. Scott Fitzgerald");
        values.put("option4", "Charles Dickens");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);

        // Pregunta 10
        values.put("question", "¿En qué país se llevó a cabo la Revolución Rusa?");
        values.put("option1", "Rusia"); // Correcta
        values.put("option2", "Alemania");
        values.put("option3", "Francia");
        values.put("option4", "Estados Unidos");
        values.put("image", "");
        db.insert(TABLE_HISTORY, null, values);
    }

    public void insertSports(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Pregunta 1
        values.put("question", "¿Cuál es el deporte más popular del mundo?");
        values.put("option1", "Fútbol"); // Correcta
        values.put("option2", "Baloncesto");
        values.put("option3", "Tenis");
        values.put("option4", "Golf");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 2
        values.put("question", "¿Cuál es el deporte nacional de Canadá?");
        values.put("option1", "Hockey sobre hielo"); // Correcta
        values.put("option2", "Béisbol");
        values.put("option3", "Fútbol");
        values.put("option4", "Rugby");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 3
        values.put("question", "¿Cuál es el evento deportivo más antiguo del mundo?");
        values.put("option1", "Juegos Olímpicos"); // Correcta
        values.put("option2", "Copa Mundial de la FIFA");
        values.put("option3", "Super Bowl");
        values.put("option4", "Tour de Francia");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 4
        values.put("question", "¿Cuál es el equipo de fútbol más exitoso en la historia de la Copa del Mundo?");
        values.put("option1", "Brasil"); // Correcta
        values.put("option2", "Alemania");
        values.put("option3", "Italia");
        values.put("option4", "Argentina");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 5
        values.put("question", "¿Cuál es el tenista con más títulos de Grand Slam en la historia?");
        values.put("option1", "Roger Federer"); // Correcta
        values.put("option2", "Rafael Nadal");
        values.put("option3", "Novak Djokovic");
        values.put("option4", "Serena Williams");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 6
        values.put("question", "¿Cuál es el equipo de baloncesto más exitoso en la historia de la NBA?");
        values.put("option1", "Boston Celtics"); // Correcta
        values.put("option2", "Los Angeles Lakers");
        values.put("option3", "Chicago Bulls");
        values.put("option4", "Golden State Warriors");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 7
        values.put("question", "¿Cuál es el deporte más practicado en los Juegos Olímpicos de Invierno?");
        values.put("option1", "Patinaje sobre hielo"); // Correcta
        values.put("option2", "Esquí alpino");
        values.put("option3", "Snowboard");
        values.put("option4", "Bobsleigh");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 8
        values.put("question", "¿Cuál es el golfista con más victorias en torneos Major?");
        values.put("option1", "Jack Nicklaus"); // Correcta
        values.put("option2", "Tiger Woods");
        values.put("option3", "Phil Mickelson");
        values.put("option4", "Rory McIlroy");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 9
        values.put("question", "¿Cuál es el equipo de béisbol más exitoso en la historia de las Grandes Ligas?");
        values.put("option1", "New York Yankees"); // Correcta
        values.put("option2", "Boston Red Sox");
        values.put("option3", "Los Angeles Dodgers");
        values.put("option4", "St. Louis Cardinals");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);

        // Pregunta 10
        values.put("question", "¿Cuál es el nadador con más medallas olímpicas en la historia?");
        values.put("option1", "Michael Phelps"); // Correcta
        values.put("option2", "Usain Bolt");
        values.put("option3", "Jesse Owens");
        values.put("option4", "Carl Lewis");
        values.put("image", "");
        db.insert(TABLE_SPORTS, null, values);
    }

    public void insertSciences(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Pregunta 1
        values.put("question", "¿Cuál es el elemento químico más abundante en el universo?");
        values.put("option1", "Hidrógeno"); // Correcta
        values.put("option2", "Oxígeno");
        values.put("option3", "Carbono");
        values.put("option4", "Helio");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 2
        values.put("question", "¿Cuál es el órgano más grande del cuerpo humano?");
        values.put("option1", "La piel"); // Correcta
        values.put("option2", "El hígado");
        values.put("option3", "El corazón");
        values.put("option4", "El cerebro");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 3
        values.put("question", "¿Cuál es el planeta más grande del sistema solar?");
        values.put("option1", "Júpiter"); // Correcta
        values.put("option2", "Saturno");
        values.put("option3", "Neptuno");
        values.put("option4", "Urano");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 4
        values.put("question", "¿Cuál es el proceso mediante el cual las plantas convierten la luz solar en energía?");
        values.put("option1", "Fotosíntesis"); // Correcta
        values.put("option2", "Respiración");
        values.put("option3", "Digestión");
        values.put("option4", "Evaporación");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 5
        values.put("question", "¿Cuál es el órgano responsable de la producción de insulina en el cuerpo humano?");
        values.put("option1", "El páncreas"); // Correcta
        values.put("option2", "Los riñones");
        values.put("option3", "El hígado");
        values.put("option4", "El corazón");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 6
        values.put("question", "¿Cuál es el gas responsable del efecto invernadero?");
        values.put("option1", "Dióxido de carbono"); // Correcta
        values.put("option2", "Oxígeno");
        values.put("option3", "Nitrógeno");
        values.put("option4", "Metano");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 7
        values.put("question", "¿Cuál es el hueso más largo del cuerpo humano?");
        values.put("option1", "Fémur"); // Correcta
        values.put("option2", "Cráneo");
        values.put("option3", "Húmero");
        values.put("option4", "Tibia");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 8
        values.put("question", "¿Cuál es el proceso mediante el cual los seres vivos obtienen energía de los alimentos?");
        values.put("option1", "Respiración"); // Correcta
        values.put("option2", "Digestión");
        values.put("option3", "Fotosíntesis");
        values.put("option4", "Evaporación");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 9
        values.put("question", "¿Cuál es la partícula subatómica con carga positiva?");
        values.put("option1", "Protón"); // Correcta
        values.put("option2", "Electrón");
        values.put("option3", "Neutrón");
        values.put("option4", "Átomo");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);

        // Pregunta 10
        values.put("question", "¿Cuál es el componente principal de la capa de ozono?");
        values.put("option1", "Oxígeno"); // Correcta
        values.put("option2", "Nitrógeno");
        values.put("option3", "Dióxido de carbono");
        values.put("option4", "Hidrógeno");
        values.put("image", "");
        db.insert(TABLE_SCIENCES, null, values);
    }
}
