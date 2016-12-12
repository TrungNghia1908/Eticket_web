<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Chuyen Xe Dem</title>
        <link rel="stylesheet" href="<c:url value='/css/normalize.css'/> ">
        <link rel="stylesheet" href="<c:url value='/css/style.css'/> ">
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>

    <body>
        <header>
            <div class="container-fluid top">
                <div class="row">
                    <p class="col-md-3 logo">Chuyến Xe Đêm</p>
                    <div class="col-md-5">
                        <form action= "<c:url value='/user/searchTrip'/>" method="post">
                            <input type="text" placeholder="Search" name="searchKey" class="srch">
                            <input type="image" src="<c:url value='/icon/1476082422_search-icon-tm.png'/>" class="ic1 ic1p">
                        </form>
                    </div>
                    <div class="col-md-3 login">
                        <div>
                            <ul class="list-inline">
                                <li>
                                    <form action="<c:url value='/user/login'/>" method="post">
                                        <input type="submit" value="My Account" class="button scpecial">
                                    </form>
                                </li>
                                <li>
                                    <form action="<c:url value='/user/logout'/>" method="post">
                                        <input type="submit" value="Logout" class="button">
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>
                                  
        <nav class="navbar navbar-custom">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li><form action="<c:url value='/admin/addTrip'/>" method="post">
                                        <input type="submit" value="Add Trips" class="link">
                                    </form></li>
                    <li><form action="<c:url value='/admin/editTrip'/>" method="post">
                                        <input type="submit" value="Edit Trips" class="link">
                                    </form></li>
                    <li><form action="<c:url value=''/>" method="post">
                                        <input type="submit" value="Show Customers" class="link">
                                    </form></li>
                    <li><form action="<c:url value='/admin/showFeedback'/>" method="post">
                                        <input type="submit" value="Show Feedbacks" class="link">
                                    </form></li>
                </ul>
            </div>
        </nav>
    