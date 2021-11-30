<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JSP</title>
</head>
<body>

<h1>Hello World</h1>

<%!
    int a = 15;
    int b = 10;

    public int doSum(){
    return a + b;
    }
%>

<%
out.println(doSum());
out.println(a);
%>

<h1> Sum is <%= doSum() %></h1>
</body>
</html>