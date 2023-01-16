CREATE TABLE employee
(
    id int NOT NULL AUTO_INCREMENT,
    firstname varchar(50),
    lastname varchar(50),
    email varchar(50),
    PRIMARY KEY (id)
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