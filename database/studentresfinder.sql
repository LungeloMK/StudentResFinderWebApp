-- StudentResFinderWebApp Derby schema
-- Default database connection used by the app:
-- jdbc:derby://localhost:1527/StudentResFinderDB;create=true, user app, password app

CREATE TABLE users (
    id_number BIGINT NOT NULL PRIMARY KEY,
    full_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(120) NOT NULL,
    salt VARCHAR(120) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    role VARCHAR(20) NOT NULL,
    registration_date TIMESTAMP NOT NULL
);

CREATE TABLE students (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    campus VARCHAR(120),
    preferred_location VARCHAR(120),
    CONSTRAINT fk_students_user FOREIGN KEY (user_id) REFERENCES users(id_number)
);

CREATE TABLE admins (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    department VARCHAR(120),
    CONSTRAINT fk_admins_user FOREIGN KEY (user_id) REFERENCES users(id_number)
);

CREATE TABLE landlords (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    business_name VARCHAR(120),
    document_number VARCHAR(80),
    verification_status VARCHAR(20) NOT NULL,
    rejection_reason VARCHAR(250),
    verified_at TIMESTAMP,
    CONSTRAINT fk_landlords_user FOREIGN KEY (user_id) REFERENCES users(id_number)
);

CREATE TABLE listings (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    landlord_id BIGINT NOT NULL,
    title VARCHAR(120) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    address VARCHAR(180) NOT NULL,
    location VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    rooms INTEGER NOT NULL,
    image_url VARCHAR(250),
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_listings_landlord FOREIGN KEY (landlord_id) REFERENCES landlords(user_id)
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_listings_location ON listings(location);
CREATE INDEX idx_listings_price ON listings(price);
CREATE INDEX idx_landlords_status ON landlords(verification_status);

-- Starter admin account:
-- email: admin@studentresfinder.local
-- password: Admin@12345
INSERT INTO users (id_number, full_name, last_name, email, password_hash, salt, phone, role, registration_date)
VALUES (
    1000000000000,
    'System',
    'Admin',
    'admin@studentresfinder.local',
    'I/jxza3QYZedjl2dkkIuBNxr0CzLeza5kUbdM9D/U2A=',
    'U3R1ZGVudFJlc0ZpbmRlckFkbWluU2FsdDIwMjY=',
    '0100000000',
    'ADMIN',
    CURRENT_TIMESTAMP
);

INSERT INTO admins (user_id, department)
VALUES (1000000000000, 'Trust and Verification');
