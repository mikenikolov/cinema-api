# 🎬 Cinema API 🎬

Cinema API - a REST API with which you can conveniently control the main functions of the cinema.

**You can test this already deployed project at the following link:**

❗ Before testing read [how to use](#-how-to-use) this API

https://modern-cinema-api.herokuapp.com/

## 🎯 Features
- Registration / Authentication
- Roles (Admin, User)
- Add a cinema hall
    - Get all cinema halls
- Add a movie
    - Get all movies
- Create/Update/Delete a movie session
    - Get all movie sessions for a specific movie and day
- Get a user by email
- Put a ticket in the cart
- Complete the order
- Get all completed orders


## 🔨 Technologies used in the project
- Java 11
- Maven 3.8.1
- PostgreSQL 14.2
- Apache Tomcat 9.0.50
- Hibernate 5.4.27
- Spring Framework 5.2.2 (Web, Security)
- Log4j 2.18.0
- Lombok 1.18.24
- JSON

## ❓ How to use
You need to install a special software (example: [Postman](https://www.postman.com/), cURL) which allows
you to send different [requests](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods) to the server.

_Or you can also create your own UI or application to display all the information from the response in a more beautiful way
(Website, Mobile Application, etc.)_

In the **next step** there are some [endpoints](#-endpoints), their request methods, and description.
To use them you need to write URL with some kind of endpoint in `request URL` field,
depending on the endpoint create a request with
`body/path-variable/request-parameter` and at the end - send the request. After this you will receive
a [JSON](https://en.wikipedia.org/wiki/JSON) object or no-body response with [code status](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status).

Before using endpoints you need to authenticate yourself with `/login` endpoint

As a step for authentication, the application uses
**Basic Authentication** [(read more)](https://en.wikipedia.org/wiki/Basic_access_authentication)
which means that the credentials in `(email:password)` format encoded in Base64 and stored in the
Authorization header.

❗ Access to endpoints depends on the roles of the logged in user. There is two roles: `Admin` and `User`

Credentials for testing already deployed project:

|  Role   |       Email        | Password   |
|:-------:|:------------------:|:-----------|
| `Admin` | `admin@cinema.com` | `pass1234` |
| `User`  |  `alice@mail.com`  | `1234pass` |


## 🌐 Endpoints

|  Method  | Role | URL                                                              | Request body                                                  | Description                                                                          |
|----------|:----:|------------------------------------------------------------------|---------------------------------------------------------------|--------------------------------------------------------------------------------------|
|  `POST`  | A U  | `/register`                                                      | `{"email","password","repeatPassword"}`                       | Register a new user with `user` role **(available for guests)**                      |
|  `POST`  | A U  | `/login`                                                         | `{"username", "password"}` (form-based, Basic Authentication) | Login **(available for guests)**                                                     |
|  `GET`   | A U  | `/logout`                                                        | None                                                          | Logout                                                                               |
|  `GET`   |  A   | `/by-email?email={email}`                                        | None                                                          | Get a user by email                                                                  |
|  `POST`  |  A   | `/cinema-halls`                                                  | `{"capacity","description"}`                                  | Create a new cinema hall                                                             |
|  `GET`   | A U  | `/cinema-halls`                                                  | None                                                          | Get all cinema halls                                                                 |
|  `POST`  |  A   | `/movies`                                                        | `{"title","description"}`                                     | Create a new movie                                                                   |
|  `GET`   | A U  | `/movies`                                                        | None                                                          | Get all movies                                                                       |
|  `GET`   | A U  | `/movie-sessions/available?movieId={movieId}&date={dd.MM.yyyy}`  | None                                                          | Get all movie sessions for a specific movie and day                                  |
|  `PUT`   |  A   | `/movie-sessions/{movieSessionId}`                               | `{"movieId","cinemaHallId","showTime"}`                       | Update a specific movie session (`showTime` pattern must be `yyyy-MM-dd'T'HH:mm:ss`) |
| `DELETE` |  A   | `/movie-sessions/{movieSessionId}`                               | None                                                          | Delete a movie session                                                               |
|  `PUT`   |  U   | `/shopping-carts/movie-sessions?movieSessionId={movieSessionId}` | None                                                          | Put a ticket in the cart                                                             |
|  `GET`   |  U   | `/shopping-carts/by-user`                                        | None                                                          | Get all tickets in the cart                                                          |
|  `PUT`   |  U   | `/orders/complete`                                               | None                                                          | Complete the order                                                                   |
|  `GET`   |  U   | `/orders`                                                        | None                                                          | Get all completed orders                                                             |

## ⚙ How to setup?
1. Install PostgreSQL, Tomcat, Java, Maven, Git
2. Clone a newest version of the project
3. Configure a Tomcat and PostgreSQL
4. Change database properties in `src/main/resources/db.properties`
   to the actual properties for your database
5. Change credentials of initial users in `src/main/resources/credentials.properties`

   ❗ These properties are for creating default admin and user which are created when you start the application
6. Start a configured Tomcat

After these steps you can start using the REST application ✨

## 🤵 Author
_Mykhailo Nikolov_

https://github.com/mikenikolov
