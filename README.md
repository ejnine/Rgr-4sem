### Интернет-магазин "Книжный бульвар"
# Описание проекта

## Данное веб приложение разработано с целью сдать расчетно-графическую работу в СГТУ по дисциплине: "Технологии и методы программирования".

## Связь с разработчиками:
* Email: 
* 	slavazin@inbox.ru - Зинин В.С.;
* 	EMSmirnova2002@yandex.ru  - Смирнова Е.М.;
* 	rulina_64@mail.ru - Рулина А.С.;


### Как запустить данное приложение?
##Необходимо скачать данное приложение(https://github.com/ejnine/Rgr-4sem.git);
#Установить->Запустить OpenServer(Последняя стабильная версия)
*Версия для MySql - min 8.0
##Cоздать схему под названием kb_database(OpenServer->Дополнительно->phpMyAdmin)
* Для конфигурации приложения откройте файл "application.properties"
*spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/kb_database, где 3306 порт для MySql(OpenServer->Настройки->Сервер)
*spring.datasource.username=(root - по умолчанию)
*spring.datasource.password=(root - по умолчанию)

## Приложение запускается через IDE "InteliIDEA", где необходима предустановленная JAVA (версия 17.* и выше),
* так же com.fasterxml.jackson.core:jackson (версия 2.13.*)

### Готово. Приложение будет доступно на странице localhost:661
