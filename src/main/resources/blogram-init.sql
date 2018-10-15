-- 개발환경 설정시 최초 한번 실행한다.

-- blogram schema 생성
CREATE DATABASE blogram;

-- blogram user 생성
CREATE USER 'blogramuser'@'localhost' IDENTIFIED BY 'blogrampass';
GRANT ALL PRIVILEGES ON blogram.* To 'blogramuser'@'localhost' IDENTIFIED BY 'blogrampass';
FLUSH PRIVILEGES;