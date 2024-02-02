CREATE TABLE IF NOT EXISTS doctors
(
    id        SERIAL PRIMARY KEY,
    uuid      UUID DEFAULT gen_random_uuid() NOT NULL,
    full_name VARCHAR(100)                   NOT NULL

);

CREATE TABLE IF NOT EXISTS patients
(
    id            SERIAL PRIMARY KEY,
    uuid          UUID DEFAULT gen_random_uuid() NOT NULL,
    full_name     VARCHAR(100)                   NOT NULL,
    date_of_birth DATE                           NOT NULL

);

CREATE TABLE IF NOT EXISTS appointments
(
    id               SERIAL PRIMARY KEY,
    doctor_id        INT       NOT NULL,
    patient_id       INT       NOT NULL,
    appointment_time TIMESTAMP NOT NULL,

    FOREIGN KEY (doctor_id) REFERENCES doctors (id),
    FOREIGN KEY (patient_id) REFERENCES patients (id)
);

CREATE TABLE IF NOT EXISTS schedule_template
(
    id         SERIAL PRIMARY KEY,
    doctor_id  INT NOT NULL,
    work_days  TEXT[],
    work_hours JSONB,

    FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);

CREATE TABLE IF NOT EXISTS doctor_schedule
(
    id            SERIAL PRIMARY KEY,
    doctor_id     INT  NOT NULL,
    schedule_date DATE NOT NULL,
    template_id   INT  NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctors (id),
    FOREIGN KEY (template_id) REFERENCES schedule_template (id)
);



