# mobile-study-app
Back-end RestFull приложение, предназначенное для программы обучения.  
Состоит из 3 CRUD с основными сущностями Event(мероприятие), Animal(фактически профиль пользователя),
User(пользователь).  
Image - картинка сохраняющаяся в БД в виде массива байтов. Реализует отношение 1 к 1 с "Animal".
ImageId связан с userId foreign key
Location - координаты точки на карте. Реализует отношение 1 к 1 с "Event". LocationId связан с eventId foreign key
В приложение интегрирована защита Spring Security и распространяется на все эндпойнты кроме публичных.

Public endpoints:
- "/v1/authorization"
- "/v1/recovery"
- "/v1/email"
- "/v1/registration"
- "/v1/saveEvent"
- "/v1/event/{id}"
- "/v1/eventList"
- "/v1/event"

## User
- POST "/v1/registration" - эндпойнт предназначенный для регистрации пользователя.  
  Пример запроса:
  email, password обязательные для заполнения
  http://localhost:8080/v1/registration  
  {  
  "email": "test@mail.ru",  
  "password": "test@mail.ru"  
  }  
  Response:  
  HTTPSTATUS 200 при успешной регистрации,  
  HTTPSTATUS 400 BadRequest в случае наличия пользователя в базе данных

- POST "/v1/recovery" - эндпойнт предназначенный для восстановления пользователя (update password).  
  Пример запроса:
  email, password не могут быть пустыми
  http://localhost:8080/v1/recovery  
  {  
  "email": "test@mail.ru",  
  "password": "test@mail.ru"  
  }  
  Response:  
  HTTPSTATUS 200 при успешной замене пароля  
  HTTPSTATUS 400 BadRequest попытка смена пароля при отсутствующем в бд пользователе

- GET "/v1/email" - эндпойнт предназначенный для проверки наличия email в бд.  
  Пример запроса:  
  http://localhost:8080/v1/email?email=test@mail.ru  
  Response:  
  HTTPSTATUS 200 "Ура! E-mail свободен! Идем дальше..." - доступно для записи  
  HTTPSTATUS 200 "Такой e-mail занят :("  - данный email занят

- POST "/v1/authorization" - эндпойнт предназначенный для авторизации пользователя.   
  Пример запроса:  
  http://localhost:8080/v1/email?email=test@mail.ru  
  {
  "email": "test@mail.ru",
  "password": "test@mail.ru"
  }
  Response:  
  HTTPSTATUS 200  "authToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiO... " - пользователь авторизован  
  HTTPSTATUS 401 "Unauthorized" - авториазация не удалась

## Animal
Доступно только авторизаванному пользователю. (В headers добавлять авторизационный токен)
Например: "Authorization" : "Bearer eyJhbGc.eyYyMH0.BPN18SLJYDy3xuQ7ONIPa65A ..."

- POST "/v1/animal" - эндпойнт предназначенный для сохранения профиля в бд  
  Пример запроса:
  name, city, age, breed, registrationDate обязательные для заполнения
  http://localhost:8080/v1/animal
  {
  "name": "test1",
  "city": "Ufa",
  "age": "11",
  "breed": "pitbul",
  "description": "beautiful dog",
  "ownerName": "Katy",
  "gender": "man",
  "registrationDate": "2013-09-29T18:46:19Z"
  }
  Response:   
  HTTPSTATUS 200 при успешном сохранении профиля,  
  HTTPSTATUS 400 BadRequest в случае некорректных данных

- DELETE "/v1/animal" - эндпойнт предназначенный для удаления профиля
  Пример запроса:
  http://localhost:8080/v1/animal
  Response:   
  HTTPSTATUS 200 при успешном сохранении профиля,  
  HTTPSTATUS 400 BadRequest в случае некорректных данных

- GET "/v1/animal" - эндпойнт предназначенный для удаления профиля
  Пример запроса:
  http://localhost:8080/v1/animal
  Response:
  HTTPSTATUS 200 
  {  
  "id": 1   
  "name": "test1"    
  "city": "Ufa"  
  "age": 11  
  "breed": "pitbul"  
  "gender": "man"  
  "ownerName": "Katy"  
  "description": ""  
  "registrationDate": "2013-09-29"  
  "image": null  
  }  
  HTTPSTATUS 400 BadRequest в случае некорректных данных

## Image

Доступно только авторизаванному пользователю. (В headers добавлять авторизационный токен)
Например: "Authorization" : "Bearer eyJhbGc.eyYyMH0.BPN18SLJYDy3xuQ7ONIPa65A ..."

- POST "/v1/animalImage" - эндпойнт предназначенный для сохранения картинки профиля в бд. 
  Так же необходимо приложить MultipartFile в RequestParam с именем "file"
  Пример запроса:
  http://localhost:8080/v1/animalImage
  Response:   
  HTTPSTATUS 200 при успешном сохранении профиля,  
  HTTPSTATUS 400 BadRequest в случае некорректных данных

- DELETE "/v1/animalImage" - эндпойнт предназначенный для удаления картинки профиля
  Пример запроса:
  http://localhost:8080/v1/animalImage  
  Response:   
  HTTPSTATUS 200 при успешном сохранении профиля,  
  HTTPSTATUS 400 BadRequest в случае некорректных данных

- GET "/v1/animalImage" - эндпойнт предназначенный для удаления профиля
  Пример запроса:
  http://localhost:8080/v1/animalImage  
  Response:  
  HTTPSTATUS 200 + картинка, при успешном запросе
  HTTPSTATUS 400 BadRequest в случае некорректных данных
  
## Event 
- POST "/v1/saveEvent" - эндпойнт предназначенный для сохранения мериприятия в бд.
  Пример запроса:
  Так же необходимо приложить 2 параметра:   
  + MultipartFile в RequestParam с именем "file"
  + объект payload например:
    {
    "name": "test1"    
    "eventType": "Выставка кошек"  
    "description": "description"  
    "phone": "+7(999)-000-00-00"  
    "location": [  
    "lat":"11.11111",  
    "lng":"11.11111"  
    ]}  

  Response:   
  HTTPSTATUS 200 при успешном сохранении профиля,  
  HTTPSTATUS 400 BadRequest в случае некорректных данных

- DELETE "/v1/event/{id}" - эндпойнт предназначенный для удаления метки мероприятия
  Пример запроса:
  http://localhost:8080/v1/event/{id}
  Response:   
  HTTPSTATUS 200 при успешном сохранении профиля,

- GET "/v1/eventList" - эндпойнт предназначенный для получения списка мероприятий
  Пример запроса:
  http://localhost:8080/v1/animal  
  Response:
  HTTPSTATUS 200  
  {  
  "id": 1   
  "name": "test1"    
  "description": "description"  
  "eventType": "eventType"  
  "location": [  
  "lat":"11.11111",  
  "lng":"11.11111"  
  ]}   
  }

- GET "/v1/event" - эндпойнт предназначенный для получения мероприятия
  Пример запроса:
  http://localhost:8080/v1/event?id=65
  Response:
  HTTPSTATUS 200  
  {  
  "id": 1   
  "name": "test1"    
  "description": "description"  
  "eventType": "eventType"  
  "image": "[bytes]"   
  "location": [  
  "lat":"11.11111",  
  "lng":"11.11111"  
  ]}  
  HTTPSTATUS 400 BadRequest в случае некорректных данных