<%@page import="java.util.Base64" %>
<%@page import="org.owasp.ottawa.topten2017_A8.BicycleBean" %>
<%@page import="java.io.ByteArrayInputStream" %>
<%@page import="java.io.ObjectInputStream" %> 

<html>
  <head>
    <title>OWASP Ottawa - Insecure Deserialization</title>
  </head>
  <body>
    <div>add a new Bicycle record to the internal DB:</div>
<%if (request.getParameter("input")==null) { %>
    <form target="result">
      <textarea name="input" rows="5" cols="50"></textarea>
      <br/>
      <input type="submit" value="submit">
    </form>
    <br/>
    <iframe name="result" style="width: 100%; height: 100%"></iframe>
<% } else {
   byte[] stream = Base64.getDecoder().decode(request.getParameter("input"));	
   ByteArrayInputStream bais = new ByteArrayInputStream(stream);
   ObjectInputStream ois = new ObjectInputStream(bais);
   BicycleBean b = (BicycleBean)ois.readObject();
   ois.close();
   bais.close();
   out.println("Bicycle '"+b.getName()+"' added");	
} %>
  </body>
</html>
