CREATE TABLE BOARD(
   IDX INT NOT NULL AUTO_INCREMENT,
   TITLE VARCHAR(100) NOT NULL,
   CONTENT VARCHAR(2000) NOT NULL,
   WRITER VARCHAR(30) NOT NULL,
   INDATE DATETIME DEFAULT NOW(),
   COUNT INT DEFAULT 0,
   PRIMARY KEY(IDX)
);


INSERT INTO BOARD(TITLE, CONTENT, WRITER)
VALUES('100억 부자 유병재 VS 100억 빚 차은우', '차은우!', '???');
INSERT INTO BOARD(TITLE, CONTENT, WRITER)
VALUES('1000억 부자 유병재 VS 100억 빚 차은우', '차은우!', '???');
INSERT INTO BOARD(TITLE, CONTENT, WRITER)
VALUES('1조 부자 유병재 VS 100억 빚 차은우', '차은우!', '???');
INSERT INTO BOARD(TITLE, CONTENT, WRITER)
VALUES('10조 부자 유병재 VS 100억 빚 차은우', '차은우!', '???');
INSERT INTO BOARD(TITLE, CONTENT, WRITER)
VALUES('100조 부자 유병재 VS 100억 빚 차은우', '차은우!', '???');



SELECT * FROM BOARD;



--  member 테이블

CREATE TABLE MEMBER(
   memIdx INT NOT NULL AUTO_INCREMENT,
   memId VARCHAR(20) NOT NULL,
   memPassword VARCHAR(20) NOT NULL,
   memName VARCHAR(20) NOT NULL,
   memAge INT,
   memGender VARCHAR(20),
   memEmail VARCHAR(50),
   memProfile VARCHAR(50),
   PRIMARY KEY(memIdx)
);


SELECT * FROM MEMBER;

-- test 데이터 넣기
INSERT INTO MEMBER (memId, memPassword, memName,memAge, memGender, memEmail)
VALUES('admin', '1234', '관리자',20, '여자','riricos153@gmail.com');
INSERT INTO MEMBER (memId, memPassword, memName,memAge, memGender, memEmail)
VALUES('smart', '13434', '카즈하',22, '여자','riricos15333@gmail.com');
INSERT INTO MEMBER (memId, memPassword, memName,memAge, memGender, memEmail)
VALUES('spring', '15555', '공명',25, '남자','riricos46463@gmail.com');

DELETE FROM MEMBER WHERE memId='admin';
DELETE FROM MEMBER WHERE memId='smhrd';
DELETE FROM MEMBER WHERE memId='spring';

