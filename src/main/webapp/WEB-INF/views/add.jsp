<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertForm</title>
<style>
* {
    box-sizing: border-box;
    font-family: Arial, sans-serif;
}

body {
    margin: 0;
    padding: 20px;
    background-color: #f9f9f9;
}

form {
    width: 80%;
    max-width: 700px;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
    text-align: left;
    margin-bottom: 20px;
    font-size: 1.5em;
    color: #333;
}

label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
    color: #333;
}

input[type="text"],
input[type="number"],
input[type="date"],
select {
    width: calc(100% - 20px);
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1em;
}

.description {
    font-size: 0.85em;
    color: red;
    margin-bottom: 20px;
}

button[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #ff4d4f;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 1.2em;
    cursor: pointer;
}

button[type="submit"]:hover {
    background-color: #d9363e;
}
</style>
</head>
<body>
	<form action="/account/insert" method="POST">
	    <label>이름</label>
	    <input id="name" type="text" name="name" required><br/>

	    <label>핸드폰 번호</label>
        <input id="phone_number" type="text" name="phone_number" required><br/>

        <label>계좌 번호</label>
        <input id="account_number" type="text" name="account_number" required><br/>

        <label>은행</label>
        <input id="finance_name" type="text" name="finance_name" required><br/>

        <label>피해 금액</label>
        <input id="depositAndWithdrawalMoney" type="number" name="depositAndWithdrawalMoney" step="0.01" required><br/>

        <label>설명</label>
        <input id="description" type="text" name="description" required><br/>

        <button type="submit">사기꾼 등록</button>
	</form>
</body>
</html>