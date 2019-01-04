<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\1\3 0003
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>贵美商城</title>
    <style type="text/css">
        /*左右上下浮动图片*/
        #mm1,#mm2{
            position: absolute;
            height: 800px;
        }

        #mm2{
            left:93%;
        }
    </style>
    <script type="text/javascript" src="../js/jquery-2.1.0.js"></script>
    <link href="../css/guimei.css" rel="stylesheet" type="text/css">
    <script>
        /* iframe自适应高宽 */
        function iFrameHeight() {
            var ifm= document.getElementById("iframepage");
            var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;
            if(ifm != null && subWeb != null) {
                ifm.height = subWeb.body.scrollHeight;
                ifm.width = subWeb.body.scrollWidth;
            }
        }
    </script>
</head>
<body>
<marquee id="mm1" direction="down" style="z-index: -1;"><img src="../images/img/images/list1.jpg"></marquee>
<marquee id="mm2" direction="up" style="z-index: -1;"><img src="../images/img/images/list2.jpg"></marquee>
<div id="head">
    <iframe src="head.html" width="980px" height="131px" name="head_i" frameborder="no" scrolling="no"></iframe>
</div>
<div id="body" >
    <iframe src="homepage.html" id="iframepage" name="body_i" frameborder="no" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()"></iframe>
</div>
<div id="foot">
    <iframe src="foot.html" width="980px" height="150px" frameborder="no" scrolling="no"></iframe>
</div>

</body>
</html>