create table CAR (
  id IDENTITY primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  power DOUBLE,
  value DEC(20),
  year_of_issue YEAR
);

create table AIRPLANE (
  id IDENTITY primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  manufacturer VARCHAR2(500),
  year_of_issue YEAR,
  fuel_Capacity INT,
  value DEC(20),
  seats INT
);

create table ASSESSMENT (
  id IDENTITY  primary  key,
  COLLATERAL_ID INT,
  COLLATERAL_TYPE VARCHAR2(50),
  value DEC(20),
  assessed_Date TIMESTAMP
)