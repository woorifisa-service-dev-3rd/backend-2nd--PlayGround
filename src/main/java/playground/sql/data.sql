use playground;
INSERT INTO Fiance (type, name) VALUES ("bank","국민은행");
INSERT INTO Fiance (type, name) VALUES ("bank","농협은행");
INSERT INTO Fiance (type, name) VALUES ("bank","우리은행");
INSERT INTO Fiance (type, name) VALUES ("bank","우체국");
INSERT INTO Fiance (type, name) VALUES ("bank","카카오뱅크");
INSERT INTO Fiance (type, name) VALUES ("bank","교보");
INSERT INTO Fiance (type, name) VALUES ("bank","BC카드");
INSERT INTO Fiance (type, name) VALUES ("bank","기업은행");
INSERT INTO Fiance (type, name) VALUES ("bank","현대");
INSERT INTO Fiance (type, name) VALUES ("Securities","DB금융투자");
INSERT INTO Fiance (type, name) VALUES ("Securities","IBK투자증권");
INSERT INTO Fiance (type, name) VALUES ("Securities","KB증권");
INSERT INTO Fiance (type, name) VALUES ("Securities","NH투자증권");
INSERT INTO Fiance (type, name) VALUES ("Securities","sk증권");
INSERT INTO Fiance (type, name) VALUES ("Securities","하나증권");
INSERT INTO Fiance (type, name) VALUES ("national_tax","국세");
INSERT INTO Fiance (type, name) VALUES ("national_tax","국고금");
INSERT INTO Fiance (type, name) VALUES ("local_tax","지방세입");

INSERT INTO Users (user_name, phone_number) VALUES ("신원섭","010-0000-0000");
INSERT INTO Users (user_name, phone_number) VALUES ("곽지은","010-0000-0000");
INSERT INTO Users (user_name, phone_number) VALUES ("구자빈","010-0000-0000");
INSERT INTO Users (user_name, phone_number) VALUES ("김민지","010-0000-0000");

INSERT INTO Account (user_id, fiance_id, account_number, money, description, deposit_and_withdrawal_money, is_deposit_or_withdrawal, date_time)
VALUES (1,1,"1234567890987",10000000,"심심해", 10000, "Deposit", "2024-09-06 11:30:50");

INSERT INTO Account (user_id, fiance_id, account_number, money, description, deposit_and_withdrawal_money, is_deposit_or_withdrawal, date_time)
VALUES (2,4,"0987654321234",30000000,"바보", 280500, "Withdrawal", "2024-09-06 11:30:50");

INSERT INTO Account (user_id, fiance_id, account_number, money, description, deposit_and_withdrawal_money, is_deposit_or_withdrawal, date_time)
VALUES (2,4,"1111111111111",30000000,"바보", 280500, "Withdrawal", "2024-09-06 11:30:50");

INSERT INTO the_cheat (account_id, account_number, description, amount) VALUES (1,"1234567890987","사기쳤음",100000);
INSERT INTO the_cheat (account_id, account_number, description, amount) VALUES (2,"0987654321234","당근에서 도망갔습니다.",20000);

