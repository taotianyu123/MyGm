<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\1\3 0003
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="../js/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="../js/homepage.js"></script>
    <link href="../css/guimei.css" rel="stylesheet" type="text/css">
    <link href="../css/homepage.css" rel="stylesheet" type="text/css">

</head>
<body>
<div id="home_body">

    <div id="body_1">
        <p id="class_1_big" style="margin-top:38px;"></p>
        <div id="class_1_small">
        </div>
        <p id="class_2_big" style="margin-top:4px;"></p>
        <div id="class_2_small">
        </div>
        <p id="class_3_big" style="margin-top:3px;"></p>
        <div id="class_3_small">
        </div>
        <p id="class_4_big" style="margin-top:1px;"></p>
        <div id="class_4_small">
        </div>
    </div>

    <div id="body_2">
        <div  id="b2d2">
            <img name="adimg" src="../images/img/images/ad-01.jpg">
            <img name="adimg" src="../images/img/images/ad-02.jpg">
            <img name="adimg" src="../images/img/images/ad-03.jpg">
            <img name="adimg" src="../images/img/images/ad-04.jpg">
        </div>
        <div id="ad_a">
            <div class="ad_a1" onmouseover=xunhuan(1)>1</div>
            <div class="ad_a1" onmouseover=xunhuan(2)>2</div>
            <div class="ad_a1" onmouseover=xunhuan(3)>3</div>
            <div class="ad_a1" onmouseover=xunhuan(4)>4</div>
        </div>
        <div id="b2d1" >
            <div	id="goodsShow" style="width:515px;height:380px"></div>
            <table id="paging"  width="100%"  style="text-align:left;font-size:14px">
                <tr>

                    <td style="text-align:right;">当前第<b id="pageNum"></b>页，共<b id="totalPage"></b>页</td>
                </tr>
            </table>
        </div>
    </div>

    <div id="body_3">
        <div	id="announ" style="width:210px;height:55px;margin-top:44px;margin:50px 0 65px 10px;"></div>
        <div class="b3dx" ></div>
        <div style="width: 234px;height: 47px" onclick=congzi()  >
            <img id="ad_img" style="margin-top: 6px;visibility:visible"  src="../images/img/images/phono.jpg" >
        </div>
        <div id="b3congzhi">
            <div id="selbd_1">
                <form action="#" method="post">
                    品&nbsp;牌：<select class="selbd">
                    <option value="联想">联想</option>
                    <option value="联想">DELL</option>
                    <option value="联想">华硕</option>
                    <option value="联想">苹果</option>
                    <option value="联想">HP</option>
                </select><br>
                    尺&nbsp;寸：<select class="selbd">
                    <option value="联想">14英寸</option>
                    <option value="联想">16英寸</option>
                    <option value="联想">17英寸</option>
                    <option value="联想">19英寸</option>
                </select><br>
                    价&nbsp;格：<select class="selbd">
                    <option value="联想">4000元以下</option>
                    <option value="联想">5000元以下</option>
                    <option value="联想">6000元以下</option>
                    <option value="联想">7000元以下</option>
                    <option value="联想">8000元以下</option>
                </select>
                    <input style="margin: 8px 0 0 30px" type="image" src="../images/img/images/tabbody2.JPG">
                </form>
            </div>
        </div>
        <div id="b3congzhi2">
            <div id="selbd_2">
                运营商：<select class="selbd">
                <option>移动</option>
                <option>联通</option>
                <option>电信</option>
            </select>
                地&nbsp;&nbsp;&nbsp;区：<select class="selbd">
                <option>北京</option>
                <option>湖南</option>
                <option>湖北</option>
                <option>上海</option>
                <option>成都</option>
                <option>香港</option>
            </select>
                面&nbsp;&nbsp;&nbsp;值：<select class="selbd">
                <option>100元</option>
                <option>50元</option>
                <option>20元</option>
                <option>10元</option>
            </select>
                <input style="margin: 8px 0 0 30px" type="image" src="../images/img/images/tabbody2.JPG">
            </div>
        </div>
    </div>

</div>


</body>
</html>
