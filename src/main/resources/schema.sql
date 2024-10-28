-- Create User Table
CREATE TABLE User_details (
    user_id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255),
    middle_name VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    contact VARCHAR(20),
    expiration_date TIMESTAMP,
    timeout TIMESTAMP,
    description TEXT,
    date_time_format TIMESTAMP
);

-- Create Role Table
CREATE TABLE Role (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE
);

-- Create Scope Table
CREATE TABLE Scope (
    scope_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    scope_name VARCHAR(255) NOT NULL
);

-- Create Permission Table
CREATE TABLE Permission (
    permission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    permission_name VARCHAR(255),
    role_id BIGINT,
    scope_id BIGINT,
    primary_group_name VARCHAR(255),
    language_name VARCHAR(255),
    organization VARCHAR(255),
    time_zone VARCHAR(255),
    memo TEXT,
    FOREIGN KEY (role_id) REFERENCES Role(role_id),
    FOREIGN KEY (scope_id) REFERENCES Scope(scope_id)
);

-- Create User_Role Junction Table
CREATE TABLE user_role (
    user_id VARCHAR(255),
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES User_details(user_id),
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
);
