# Practice for Spring Boot, RabbitMQ, Redis
## Installation
- Clone source code of docker-ws at https://github.com/htramanh89/docker-ws
- Navigate to docker-ws: ``cd /path/tp/docker-ws``
- Build environment having MySQL, RabbitMQ, Redis by typing the command: ``docker-compose up``
- Change directory to the scripts of MySQL DB to get initial database with tables/test data created: ``cd /path/tp/docker-ws/cirrus/mysql/scripts``
- Execute the scripts in that directory sequentially (sorted by name) to create tables and test data as well (via HeidiSQL, MySQL Workbench, etc.). You can find DB credentials at: ``/path/tp/docker-ws/cirrus/mysql/docker-entrypoint-initdb.d/1_create_database.sql``
## Play with application
- /GET to view all authors 

``curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET  http://localhost:8080/author/getAll``

- /POST to save an author to DB

``curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{
      "userName": "author7",
      "password": "author7",
      "active": true
  }' http://localhost:8080/author/save``
  
 
