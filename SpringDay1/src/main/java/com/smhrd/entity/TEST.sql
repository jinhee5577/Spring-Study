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


-- 시험 BOOK 테이블

CREATE TABLE BOOK(
   num INT NOT NULL AUTO_INCREMENT,
   title VARCHAR(50) NOT NULL,
   author VARCHAR(30) NOT NULL,
   company VARCHAR(50) NOT NULL,
   isbn VARCHAR(30) NOT NULL,
   count INT NOT NULL,
   PRIMARY KEY(num)
);


SELECT * FROM BOOK;

DELETE FROM BOOK;

-- test 데이터 넣기
INSERT INTO BOOK (title, author, company,isbn, count)
VALUES('해리포터와 아즈카반의 죄수', 'J.K 롤링','문학수첩', '8983920726',12);
INSERT INTO BOOK (title, author, company,isbn, count)
VALUES('난중일기', '이순신', '관리자','서해문집', '8974832232',8);
INSERT INTO BOOK (title, author, company,isbn, count)
VALUES('수학 귀신', '한스 마그누스','비룡소', '8949187310',9);
INSERT INTO BOOK (title, author, company,isbn, count)
VALUES('윌리를 찾아라!', '마틴 핸드포드', '예꿈', '8992882734',20);
INSERT INTO BOOK (title, author, company,isbn, count)
VALUES('오세암', '정채봉', '창비','8936440195', 4);