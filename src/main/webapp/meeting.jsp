<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
<%@page import="com.aji_t9ra.Models.*"%>
<% List<Meeting> listMeetings = (List<Meeting>) request.getAttribute("listMeetings"); %>
<!DOCTYPE html>
<!--
 *  Copyright (c) 2021 The WebRTC project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree.
-->
<html>
<head>

    <meta charset="utf-8">
    <meta name="description" content="WebRTC code samples">
    <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1, maximum-scale=1">
    <meta itemprop="description" content="Client-side WebRTC code samples">
    <meta itemprop="image" content="../../../images/webrtc-icon-192x192.png">
    <meta itemprop="name" content="WebRTC code samples">
    <meta name="mobile-web-app-capable" content="yes">
    <meta id="theme-color" name="theme-color" content="#ffffff">

    <base target="_blank">

    <title>Communication entre utilisateur</title>

    <link rel="icon" sizes="192x192" href="../../../images/webrtc-icon-192x192.png">
    <link href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="assets/css/meeting.css"/>
    

</head>

<body>
<%--
<form >
<input type="text" name="id" placeholder="meeting id">
<input type="text" name="user_id" placeholder="1">
<input type="text" name="link" placeholder="room link">   
<button type="submit">create</button>   
</form>
<div id="container">
<%
					for (Meeting m : listMeetings) {
					%>
<div>
<%=m.getId()%>
</div>
<div>
<%=m.getUserId()%>
</div>
<a href="<%=m.getUserId()%>">
join meeting
</a>
<%} %> --%>
<div class="meeting">
<div cass="call">
    <video id="remoteVideo" playsinline autoplay></video>
    <video id="localVideo" playsinline autoplay muted></video>
 </div>
 
    <div class="box">
        <button id="startButton">rejoindre</button>
        <button id="hangupButton">quitter</button>
    </div>

</div>

<script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>
<script src="assets/js/meeting.js" async></script>




</body>
</html>