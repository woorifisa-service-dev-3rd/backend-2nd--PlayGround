<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertForm</title>
</head>
<body>
	<form action="cheat" method="POST">
    	    <label>계좌</label>
    	    <input id="accountNumber" type="text" name="accountNumber"><br/>
    	      <label>설명</label>
            <input id="description" type="text" name="description"><br/>
            <label>피해금액</label>
            <input id="amount" type="number" name="amount"><br/>
    	    <button type="submit">사기꾼 등록</button>
    	</form>
</body>
</html>
