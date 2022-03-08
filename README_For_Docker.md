# Instructions to run MySQL Database with PHPMyAdmin with Docker

### Make sure you have Docker installed:
```bash
docker --version
```

### Make sure you have Docker Compose installed:
```bash
docker-compose --version
```

## Docker Compose
That project contains a `docker-compose.yml` that `docker-compose` can use to start MySQL & PhpMyAdmin.
By using the `docker-compose.yml` file you don't have to install any extra development dependencies for MySQL.

### 1. Installing containers:
Make sure you are in the current project's directory. `.../carrer_jump_start or sth else/`
```bash
docker-compose up
```
This will download, build and start the MySQL and PhpMyAdmin containers on your system.

### 2. Stopping:
```bash
docker-compose stop
```
This will stop the MySQL and PhpMyAdmin containers running on your system.

### 3. Starting again:
```bash
docker-compose start
```
When you already have downloaded the MySQL and PhpMyAdmin containers you can start them again quickly 
with `docker-compose start`

## PhpMyAdmin

PHPMyAdmin is available at [localhost:9090](http://localhost:9090). You can use it to create and drop databases.

Login with:
```
Server: mysql-local  
Username: root
Password: career_jumpstart
```

## Run Career Jump Start
1. Edit Configurations...
2. `+`
3. Maven
4. In the `Run` field type `spring-boot:run`
5. Change working directory to current project directory
6. Ok
7. Hit the play button

Good luck


