#  **Запуск тестов**

gradle test

#  **Генерация аллюр отчета** 

gradle allureReport

Отчет будет создан в /build/reports/index.html 

Его можно открыть через браузер

# **Для использования Docker**

Создать jar

gradle build jar

В терминале ввести команду для сборки образа докера

docker build --no-cache -t my-image .

после поднятия ввести команду для запуска образа 

docker run my-image
#   p e t S t o r e A u t o t e s t  
 