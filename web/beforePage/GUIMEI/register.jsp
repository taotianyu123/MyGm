<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019\1\3 0003
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="../css/guimei.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../EasyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../EasyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../EasyUI/themes/metro/demo.css">
    <script type="text/javascript" src="../EasyUI/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="../EasyUI/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="..//EasyUI/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/register.js"></script>
    <script type="text/javascript">

    </script>
    <style>
        #textfield{background-color:#FFF3F3}
        .file-box{ position:relative;width:210px;	}
        .btn{  border:1px solid #CDCDCD;height:24px; width:70px;}
        .file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0; }
    </style>
</head>
<body id="zhuce1">
<div style="width: 490px;height: 550px;float: left;">
    <form id="formSub" action="../servlet/customServlet?action=customRegister" method="post"  enctype="multipart/form-data">
        <table style="margin:60px 0 0 80px">
            <tr>
                <td class="text1">姓名：</td>
                <td><input type="text" id="ok"  class="easyui-validatebox" data-options="required:true" name="C_name"><br><label></label></td>
            </tr>
            <tr>
                <td class="text1">登录名：</td>
                <td><input type="text" id="id" class="easyui-validatebox" data-options="required:true" name="C_loginName"><label id="checkName"></label>
                    <br></td>
            </tr>
            <tr>
                <td class="text1">密码：</td>
                <td><input type="password" id="pwd1" class="easyui-validatebox" data-options="required:true" name="C_pwd"><br></td>
            </tr>
            <tr>
                <td class="text1">再次输入密码：</td>
                <td><input type="password" class="easyui-validatebox"  data-options="required:true" name="C_pwd2" id="pwd2"></td>
            </tr>
            <tr>
                <td class="text1">电子邮箱：</td>
                <td><input type="EMAIL" id="Email" class="easyui-validatebox" name="C_email" data-options="required:true,validType:'email'"></td>
            </tr>
            <tr>
                <td class="text1">性别：</td>
                <td>
                    <input type="radio" name="C_sex" id="man" value="男"><img src="../images/img/zhuce/Male.gif">男
                    <input type="radio" name="C_sex" id="woman"  value="女" checked="checked"> <img src="../images/img/zhuce/Female.gif">女
                </td>
            </tr>
            <tr>
                <td class="text1">头像：</td>
                <td>
                    <div class="file-box">
                        <input type='text' name='textfield' id='textfield' data-options="required:true"/>
                        <input type="file" name="C_photo" class="file" data-options="required:true" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
                        <label style="width:150px;font-size:14px"></label>
                        <br/><label id="filetext" style="width:150px;font-size:11px"></label>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="text1">爱好：</td>
                <td>
                    <input type="checkbox" name="C_hobby" value="运动">&nbsp;运动
                    <input type="checkbox" name="C_hobby" value="聊天">&nbsp;聊天
                    <input type="checkbox" name="C_hobby" value="玩游戏">&nbsp;玩游戏
                </td>
            </tr>
            <tr>
                <td class="text1">出生日期：</td>
                <td><input class="easyui-datebox" data-options="required:true" type="text" name="C_birthday"></td>
            </tr>
            <tr>
                <td class="text1">身份证号：</td>
                <td><input type="text"  class="easyui-validatebox" data-options="required:true" name="C_code"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;height: 34px;">
                    <input type="image" src="../images/img/zhuce/submit.gif" style="float:left;margin-left:75px">
                    <input type="reset" value=" " style="background-image: url('../images/img/zhuce/reset.gif');background-repeat:no-repeat; border:0px;width:80px;height:34px;float:left;" >
                </td>
            </tr>
        </table>
    </form>
</div>
<div style="width: 480px;height: 550px;float: left">
    <img style="margin:30px 0 0 20px" src="../images/img/zhuce/read.gif"/><b>阅读贵美网服务协议</b> <br>
    <textarea style="margin: 10px 0 0 50px; width: 310px;height: 400px;resize: none;">
贵美商城（网址:http://www.guimei.com）是北京贵美网络科技有限公司旗下经营的网站。本协议双方为北京贵美网络科技有限公司（简称“贵美商城”）与贵美商城项目发起人（简称“您”或“用户”）。在您使用贵美商城提供的服务之前，请务必认真阅读本协议的全部内容。本协议一经您点击确认并同意接受，即在您与贵美商城之间产生法律效力。
一、 定义
1、项目发起人：在贵美商城平台注册并发起众筹项目的单位或个人；
2、项目投资人：在贵美商城平台注册并投资某众筹项目的单位或个人。
3、保证金：项目发起人在贵美商城平台发起的项目，由贵美商城作为代收款方，收取项目投资人基于项目投资明细单而支付的众筹金额。项目众筹成功后，贵美商城留存项目众筹总金额的30%作为保证金，在项目发起人兑现对项目投资人的回报承诺后，贵美商城将该保证金返还给项目发起人。如果项目发起人未兑现对项目投资人的回报承诺，则贵美商城有权直接支配该保证金以用于兑现对项目投资人的回报承诺。
4、资金支付渠道费：为保证众筹资金安全，众筹项目项下资金均通过第三方支付公司代为划转，由此产生的支付渠道费由项目发起人负担，项目众筹成功后，贵美商城将扣除项目众筹总金额的1.5%作为资金支付渠道费，并支付给第三方支付机构。如众筹项目不成功，则不收取该笔费用。另，如众筹项目已众筹成功，但由于发起人原因无法履行回报的，发起人需向贵美商城支付相当于筹资总金额的1.5%的资金支付手续费。
二、本协议的构成及效力
1、所有贵美商城已经发布的或将来发布的各类规则，作为本协议不可分割的一部分，与本协议具有同等的法律效力。
2、贵美商城有权根据需要适时修订本协议及各类规则，并在网站上公示。变更后的协议和规则一经在贵美商城站上公示，即发生法律效力。
3、在使用贵美商城提供的服务之前，请务必认真阅读本协议的全部内容。如您对本协议有任何疑问，应向贵美商城咨询。本协议生效后，您不应以未阅读或不接受本协议的内容为由，主张本协议无效或要求撤销本协议。
4、您应该按照本协议约定行使权利并履行义务。如您不能接受本协议的约定，包括但不限于不能接受修订后的协议及各类规则，则您应立即停止使用贵美商城针对项目发起人提供的服务。如您继续使用贵美商城针对项目发起人提供的服务，则表示您同意并接受本协议及各类规则的约束。
三、项目发起人的资格
1、作为项目发起人，您应为贵美商城的注册用户，完全理解并接受本协议。
2、作为项目发起人的单位应为依法成立并登记备案的企业法人或其他组织；作为项目发起人的个人，应为年满18周岁并具有完全民事行为能力和民事权利能力的自然人，如项目发起人未满18周岁，应由其监护人代为履行本协议权利和义务。
3、您应按照贵美商城的要求，进行必要的身份认证和资质认证，包括但不限于身份证、护照、学历证明等的认证。
4、您应拥有在中国大陆地区开户并接收人民币汇款的银行卡或支付宝账户。
5、您应妥善保管在贵美商城的用户名和密码，凡使用您的用户名和密码登陆贵美商城进行的一切操作，均视为您本人的行为，一切责任由您本人承担。
四、您发起的众筹项目应符合以下要求
1、您在贵美商城上发起的项目，应明确具体的开始时间和结束时间。截止项目结束时间，如项目众筹金额低于预定众筹金额，则项目众筹失败；如项目众筹金额等于或大于预定众筹金额，则项目众筹成功。
2、你不应抄袭、盗用他人的成果发起众筹项目，创意类产品必须为原创。
3、您发起的项目不得包含非法、色情、淫秽、暴力等内容，不得含有攻击性、侮辱性言论，不得含有违反国家法律法规、政策的内容及其他贵美商城认为不适宜的内容。
4、您在贵美商城发起的项目，不得在国内外同类贵美商城站同时发起。
5、您在贵美商城上发起的项目不得涉及种族主义、宗教极端主义、恐怖主义等内容。
6、您应对自己及您发起的项目进行介绍，内容包括“关于我”、“发起该项目的目的”、“项目的进展和风险”、“为什么以众筹的方式发起该项目”、“项目众筹成功后的回报”等。同时，您应向项目投资人充分说明项目存在的风险及挑战，以便于项目投资人对项目有全面充分的了解，从而独立慎重作出是否投资的决定。
7、您发起的项目应内容完整、合理，具有可行性。
8、您发起的项目不应与第三方存在任何权利纠纷，否则因此导致的一切损失（包括贵美商城因此被第三方权利追索而遭受的一切损失）由您本人承担，与贵美商城无关。
9、在项目发起后的运行过程中，您应及时回复网友的提问，与项目投资人进行充分互动，促成项目众筹成功。
10、项目发起后，您应及时更新项目进展情况，以包括但不限于以照片、视频、素描等方式展示项目的进度信息。
五、项目回报
1、您在发起项目时，应明确项目众筹成功后的回报时间。
2、您承诺，如项目众筹成功，及时兑现对项目投资人承诺的回报；如项目众筹失败，同意贵美商城将众筹款项及时退还项目投资人，并由您就项目众筹失败的原因等对项目投资人作出解释。
3、如您与项目投资人在兑现回报过程中发生纠纷，一切责任由您本人承担，如因此给贵美商城造成经济或名誉损失，您应赔偿损失、恢复名誉。
4、如您在兑现对项目投资人的回报过程中，与第三方（包括但不限于物流公司）发生纠纷，一切责任由您本人承担，与贵美商城无关。
六、协议终止
1、以下任一情形出现，本协议即终止
 （1）您发起的项目违反了国家法律法规、政策或贵美商城的平台使用规则或本协议约定，贵美商城停止为您提供服务的；
 （2）您本人不同意接受本协议约定（含贵美商城发布的各类规则），并停止使用贵美商城针对项目发起人提供的服务的；
 （3）您不符合本协议约定的项目发起人应具备的资格的；
 （4）其他原因导致本协议终止的。
2、本协议终止后，不影响您因在本协议终止前的行为应承担的义务和责任。
七、本协议的解释权归北京网信贵美商城络科技有限公司所有。
		</textarea><br>
    <input type="checkbox" style="margin:15px 0 0 50px;" name="clause">&nbsp;我同意以上协议内容
</div>
</body>
</html>
