CREATE TABLE IF NOT EXISTS student (
    student_id UUID PRIMARY KEY NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    gender     VARCHAR(50) NOT NULL
        CHECK (gender = 'FEMALE' OR
               gender = 'female' OR
               gender = 'MALE'   OR
               gender = 'male')
);