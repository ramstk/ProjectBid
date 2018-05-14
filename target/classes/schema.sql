CREATE TABLE SELLER(SID BIGINT PRIMARY KEY,
   SNAME VARCHAR2);
CREATE TABLE BUYER(BID BIGINT PRIMARY KEY,
   BNAME VARCHAR2);
CREATE TABLE BIDS(BID_ID BIGINT PRIMARY KEY, BID_AMOUNT DOUBLE, PID BIGINT, BID BIGINT);
CREATE TABLE PROJECT(PID BIGINT PRIMARY KEY, PNAME VARCHAR2, PDESC VARCHAR2, MAX_BUDGET DOUBLE, TIME_LIMIT VARCHAR2, PSTATUS VARCHAR2, SID BIGINT);
ALTER TABLE BIDS
ADD FOREIGN KEY (PID) REFERENCES PROJECT(PID);
ALTER TABLE BIDS
ADD FOREIGN KEY (BID) REFERENCES BUYER(BID);
ALTER TABLE PROJECT
ADD FOREIGN KEY (SID) REFERENCES SELLER(SID);