# Тестовое задание в deeplay

## Используемые технологии

1. JDK 17

## Запуск программ

Для каждого из модулей существует задача `myJar` в gradle, которую можно запустить следующим образом:
```shell

./gradlew <module_name>:myJar
```

Например, для первой задачи:
```shell

./gradlew task1:myJar
```

Эта задача создает `.jar` файл по пути `<project_root>/<module_name>/build/libs/...jar`.

Далее, можно запустить этот `jar` файл с помощью команды:
```shell

java -jar <file_name>.jar
```