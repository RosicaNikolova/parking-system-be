CREATE TABLE employee
(
    id int NOT NULL AUTO_INCREMENT,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id int NOT NULL AUTO_INCREMENT,
    email varchar(50),
    password varchar(200),
    PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    id        int         NOT NULL AUTO_INCREMENT,
    user_id   int         NOT NULL,
    role_name varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE appointment
(
    id int NOT NULL AUTO_INCREMENT,
    comesbycar BOOLEAN,
    licenseplate varchar(50),
    outlookappointmentid varchar(200),
    datetime datetime,
    endtime time,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(50),
    phonenumber varchar(50),
    employee_id int NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id, employee_id),
    FOREIGN KEY (employee_id) REFERENCES employee (id)
)