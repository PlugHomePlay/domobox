<%@page import="fr.csn.suiviFormation.util.ConfigHelper"%>
<%@ page session="false"  %>
<html>
<head>
<META http-equiv="refresh" content="0;URL=pages/accueil/<%= ConfigHelper.getAccesEcranRecetteAutorise() ? "login.jsf" : "accueil.jsf"%>">
</head>
<body>
</body>
</html>