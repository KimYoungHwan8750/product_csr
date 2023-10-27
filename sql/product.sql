create table PRODUCT
(
    PID      NUMBER not null
        primary key,
    PNAME    VARCHAR2(50),
    QUANTITY NUMBER CHECK (QUANTITY<=100000),
    PRICE    NUMBER CHECK (PRICE>=1000)
)
