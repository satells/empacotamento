SELECT
    P.name AS professor_name,
    SUM(EXTRACT(EPOCH FROM (CS.end_time - CS.start_time)) / 3600) AS total_hours_committed 
FROM
    PROFESSOR AS P
JOIN
    PROFESSOR_CLASS AS PC ON P.id = PC.professor_id
JOIN
    CLASS AS C ON PC.class_id = C.id
JOIN
    CLASS_SCHEDULE AS CS ON C.id = CS.class_id
GROUP BY
    P.name
ORDER BY
    P.name;

/*==================================================*/
--Horários ocupados
SELECT
    R.name AS room_name,
    B.name AS building_name,
    CS.day_of_week,
    CS.start_time,
    CS.end_time,
    C.code AS class_code,
    S.name AS subject_name
FROM
    ROOM AS R
JOIN
    BUILDING AS B ON R.building_id = B.id
JOIN
    CLASS_SCHEDULE AS CS ON R.id = CS.room_id
JOIN
    CLASS AS C ON CS.class_id = C.id
JOIN
    SUBJECT AS S ON C.subject_id = S.id
ORDER BY
    R.name, CS.day_of_week, CS.start_time;

/*==================================================*/
--Horários livres
SELECT
    R.name AS room_name,
    B.name AS building_name
FROM
    ROOM AS R
JOIN
    BUILDING AS B ON R.building_id = B.id
LEFT JOIN
    CLASS_SCHEDULE AS CS ON R.id = CS.room_id
WHERE
    CS.id IS NULL 
ORDER BY
    R.name;
/*==================================================*/


-- Consulta para Salas Ocupadas
SELECT
    R.name AS room_name,
    B.name AS building_name,
    CS.day_of_week,
    CS.start_time,
    CS.end_time,
    C.code AS class_code,
    S.name AS subject_name,
    'Ocupado' AS status 
FROM
    ROOM AS R
JOIN
    BUILDING AS B ON R.building_id = B.id
JOIN
    CLASS_SCHEDULE AS CS ON R.id = CS.room_id
JOIN
    CLASS AS C ON CS.class_id = C.id
JOIN
    SUBJECT AS S ON C.subject_id = S.id

UNION ALL

-- Consulta para Salas Totalmente Livres (sem nenhum agendamento)
SELECT
    R.name AS room_name,
    B.name AS building_name,
    NULL AS day_of_week,   
    NULL AS start_time,    
    NULL AS end_time,      
    NULL AS class_code,    
    NULL AS subject_name,  
    'Totalmente Livre' AS status 
FROM
    ROOM AS R
JOIN
    BUILDING AS B ON R.building_id = B.id
LEFT JOIN
    CLASS_SCHEDULE AS CS ON R.id = CS.room_id
WHERE
    CS.id IS NULL 
ORDER BY
    room_name, day_of_week, start_time;