<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/my.js">
	
</script>
<script type="text/javascript">
    window.onload = function(){
    	var search = document.getElementById("name");
    	
    	var content = document.getElementById("content");
    	
    	search.onkeyup = function(){
    		var txt = this.value;
    		
    		//1.
    		var req = getXMLHttpRequest();
    	
    	    //4.
    	    req.onreadystatechange = function(){
    	    	if(req.readyState==4 && req.status==200){
    	    	     var ss = req.responseText;
    	    	     //alert("ss="+ss);
    	    	     
    	    	     var sss = ss.split(",");
    	    	     
    	    	     str = "";
    	    	     for(var i=0;i<sss.length;i++){
    	    	     	str += "<div onclick='showText(this)' onmouseover='changeBgColor(this)'  onmouseout='changeToColor(this)'>"+sss[i]+"</div>";
    	    	     }
    	    	     content.innerHTML = str;
    	    	     content.style.display = "block";//显示div的设置
    	    	}
 
    	    }
    	    
    	    //2.
    	    //为了避免在都次执行地址的时候,去执行缓存,所以在地址后面加入一个时间参数
    	    //搜索中文时,需要先编码
    	    var encodeTxt = window.encodeURIComponent(txt,"utf-8");
    	    req.open("get", "${pageContext.request.contextPath}/findBooksByNameServlet?name="+encodeTxt+"&time="+new Date().getTime());
    	    //3
    	    req.send();
    	}

    }
    
    function showText(div){
    	var search = document.getElementById("name");
    	
    	search.value = div.innerHTML;
    	
    	//隐藏大的div--大的div是当前div的父节点
    	div.parentNode.style.display = "none";
    }
    
    function changeBgColor(div){
    	div.style.backgroundColor="gray";
    
    }
    
    function changeToColor(div){
    
    	div.style.backgroundColor="white";
    }
</script>

<div id="divmenu">
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=文学">文学</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=生活">生活</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=计算机">计算机</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=外语">外语</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=经营">经管</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=励志">励志</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=社科">社科</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=学术">学术</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=少儿">少儿</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=艺术">艺术</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=原版">原版</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=科技">科技</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=考试">考试</a>
	<a
		href="${pageContext.request.contextPath}/showProductByPage?category=生活百科">生活百科</a>
	<a href="${pageContext.request.contextPath}/showProductByPage"
		style="color:#FFFF00">全部商品目录</a>
</div>
<div id="divsearch">
	<form action="${pageContext.request.contextPath}/findProductBySearch"
		method="post">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td style="text-align:right; padding-right:220px">
				Search <input
					type="text" name="name" class="inputtable" onkeyup="searchName();"
					id="name" /> 
					<input type="image" src="images/serchbutton.gif"
					border="0" style="margin-bottom:-4px">
				</td>
		
			</tr>
		</table>

	</form>
</div>
<div id="content" style="display:none;background-color:white;width:128px;text-align:left;color:black;position:absolute;left:1000px;top:135px;"></div>
