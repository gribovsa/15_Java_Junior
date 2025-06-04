
## Добавлю зависимости Jackson.

Это требуется, чтобы можно было править файл, в который сериализирован объект

~~~~jackson
        <dependencies>
        <!-- Добавляем зависимость для Jackson -->
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.16.0</version>
        </dependency>

        <!-- Поддержка xml сериализатора -->
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml -->
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
            <version>2.16.0</version>
        </dependency>
    </dependencies>
~~~~~