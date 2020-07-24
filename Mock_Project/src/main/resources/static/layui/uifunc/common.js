/**
 * 公共方法区
 * @param i
 * @param obj
 * @returns
 */
/*全局代理*/
//获取当前url的代理
function getproxy( url, reqid){
	  var env={};
	  if(url==null){
		  url='';
		  env=[];
	  }
	  if(reqid=null){
		  reqid=='';
	  }
	
	  $.ajax({          
           url:"/mock/getproxy",
           type:"post", 
           async: false,
           data:{
        	   url:url,
        	   reqid:reqid
           },
           success:function(data){
        	   env= data;
           },
           error:function(xhr,state,errorThrown){
             
           }
	   });
	  return env;
}

//代理ui层
function proxyui(layer,url,reqid){
	var url_='/mock_proxy?pagetype=proxy&&url='+url+'&&reqid='+reqid;
	var width;
	var height;
	if(url==''&&reqid==''){
		width='700px';
		height='400px';
	}else{
		width='400px';
		height='350px';
	}
	layer.open({
		  type: 2, 
		  content: [url_,'no'] ,
		  area: [width, height],
	        fix: false,
	        scrollbar: true,
	        success:function(){	        	
	        	
	        }
		}); 
	
}


var  slash='f41e90af75ef4173b92a2ab22a99a570';

//界面增加一条url
function addurlui(obj,i,form){
  
	 var urld=obj.url;
	 var data=obj.data;
	 var urldata=urld.replace(/\//g,slash);
	 var is_forward=obj.is_Forward;
	 var forward_addr=obj.forward_Addr;
	 var divid=urld.replace(/\//g,"_")+'divid_';
	 var textareaid=urld.replace(/\//g,"_")+'textarea_';
	 var editid=urld.replace(/\//g,"_")+'edit_';
	 var updateid=urld.replace(/\//g,"_")+'update_';
	 var deleteid=urld.replace(/\//g,"_")+'delete_';
	 var isforwardid=urld.replace(/\//g,"_")+'isforward_';
	 var isforwarddivid=urld.replace(/\//g,"_")+'isforwarddiv_';
	 var forwardurlid=urld.replace(/\//g,"_")+'forwardurl_';
	 var forwardurldivid=urld.replace(/\//g,"_")+'forwardurldiv_';
	 var specialid=urld.replace(/\//g,"_")+'specialid_';
	 var proxyid=urld.replace(/\//g,"_")+'proxyid_';
	 var action={
			"url":urld,
	    		"divid":divid,
	    		"textareaid":textareaid,
	    		"editid":editid,
	    		"updateid":updateid,
	    		"deleteid":deleteid,
	    		"isforwardid":isforwardid,
	    		"isforwarddivid":isforwarddivid,
	    		"forwardurlid":forwardurlid,
	    		"forwardurldivid":forwardurldivid,
	    		"specialid":specialid,
	    		"urldata":urldata,
	    		"proxyid":proxyid
	     }
	    
	  divid=divid+i;
	  textareaid=textareaid+i;
	  editid=editid+i;
	  updateid=updateid+i;
	  deleteid=deleteid+i;
	  isforwardid=isforwardid+i;
	  isforwarddivid=isforwarddivid+i;
	  forwardurlid=forwardurlid+i;
	  forwardurldivid=forwardurldivid+i;
	  specialid=specialid+i;
	  proxyid=proxyid+i;
	  
	  
	 if(isJSON(data)){
		 data=JSON.stringify(JSON.parse(data),null,2); 
	 }
	 $("#show").prepend('<div id="'+divid+'"><fieldset class="layui-elem-field layui-field-title"style="margin-top: 20px;">'+
	          ' <legend>URL:'+urld+'</legend></fieldset><div class="layui-collapse" lay-filter="test">'+
	          ' <div class="layui-colla-item">'+
	          ' <h2 class="layui-colla-title">'+urld+'</h2><div class="layui-colla-content">	'+
	          ' <ul class="site-doc-icon site-doc-anim">'+
	          ' <div class="layui-container">'+
		      ' <div class="layui-row">'+
		      ' <div class="layui-col-md1">'+
			  ' <button id='+editid+'  data-anim="layui-anim-scaleSpring" type="button" class="layui-btn layui-btn-primary layui-bg-green layui-anim">编辑</button>'+
			  ' </div> '+
			  ' <div class="layui-col-md1">'+
			  ' <button id='+updateid+' data-anim="layui-anim-scaleSpring" type="button" class="layui-btn layui-btn-primary layui-bg-blue layui-anim">更新</button> '+
			  ' </div> '+
			  ' <div class="layui-col-md1">'+
			  ' <button id='+deleteid+' data-anim="layui-anim-scaleSpring" type="button" class="layui-btn layui-btn-primary layui-bg-black layui-anim">删除</button>'+
			  ' </div> '+
			  ' <div class="layui-col-md1">'+
			  ' <button id='+specialid+' data-anim="layui-anim-scaleSpring" type="button" class="layui-btn layui-btn-primary layui-bg-orange layui-anim">请求数据过滤</button>'+
			  ' </div> '+
			  ' <div class="layui-col-md5">&nbsp;'+
			  ' </div> '+
			   ' <div class="layui-col-md1">'+
				  '   <form class="layui-form" action="">'+
				  '     <div class="layui-form-item">'+
				  '        <div class="layui-button-block" >'+
				  '         <button id="'+proxyid+'" type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-small layui-anim layui-icon layui-icon-set-fill layui-bg-cyan" >'+
				  '        url代理</button>'+
				  '        </div>'+
				  '      </div>'+
				  '  </form> '+
				  ' </div> <br><br>'+
			  ' </div></div><br>'+
			  ' </ul>'+
		      ' <textarea id="'+textareaid+'" required lay-verify="required" placeholder="请输入" class="layui-textarea" style="height:500px" disabled>'+data+'</textarea>'+
		      ' </div></div><div><div>'); 
	 if(is_forward=='true'){
		 $('#'+isforwardid).attr('ischecked','true');
		 $("#"+isforwardid).prop('checked',true);
		 $('#'+forwardurlid).width('200px');
		 $('#'+forwardurlid).val(forward_addr);
	 }else if(is_forward=='false'){
		 $('#'+isforwardid).attr('ischecked','false');
		 $('#'+forwardurlid).width('200px');
	     $('#'+forwardurldivid).hide(); 
	 }
	
	 form.render();
     return action;
}


//这里写起来实在是太生艹了
function bildbutton(i, obj) {

	//编辑动作
	$("#" + obj.editid + i).bind("click", {
		index : i,
		obj : obj
	}, clickeditHandler);
	//更新动作
	$("#" + obj.updateid + i).bind("click", {
		index : i,
		obj : obj
	}, clickupdateHandler);
	//删除动作
	$("#" + obj.deleteid + i).bind("click", {
		index : i,
		obj : obj
	}, clickdeleteHandler);
	//重定向动作
	$("#" + obj.isforwarddivid + i).bind("click", {
		index : i,
		obj : obj
	}, clickisforwardHandler);
	
	//个性化定制
	$("#" + obj.specialid + i).bind("click", {
		index : i,
		obj : obj
	}, clickspecialHandler);
	
	//Url代理
	$("#" + obj.proxyid + i).bind("click", {
		index : i,
		obj : obj
	}, clickproxyHandler);

}
//编辑动作
function clickeditHandler(event) {
	var i = event.data.index;
	var obj = event.data.obj;

	$("#" + obj.textareaid + i).removeAttr("disabled");
	layer.msg('文本框可编辑');
}
//更新动作
function clickupdateHandler(event) {
	var i = event.data.index;
	var obj = event.data.obj;
	var content = $("#" + obj.textareaid + i).val();
	var url = obj.url;
	var is_forward = $("#" + obj.isforwardid + i).attr('ischecked');
	var forward_addr = $("#" + obj.forwardurlid + i).val();
	var forward_addr_bak = forward_addr;
	if (is_forward == 'false') {
		forward_addr = '';
		$("#" + obj.forwardurlid + i).val('');
	}
	$.ajax({
		url : "/mock/update_data",
		type : "post",
		async : false,
		data : {
			url : url,
			data : content,
			is_forward : is_forward,
			forward_addr : forward_addr
		},
		success : function(data) {
			if (data.flag == 'success') {
			} else {
				$("#" + obj.forwardurlid + i).val(forward_addr_bak);
			}
			layer.msg(data.info);
			$("#" + obj.textareaid + i).attr("disabled", 'disabled');
		}
	});
}
//删除动作
function clickdeleteHandler(event) {
	var i = event.data.index;
	var obj = event.data.obj;
	var url = obj.url;
	layer.confirm('确定删除吗?', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			url : "/mock/delete_data",
			type : "post",
			async : false,
			data : {
				url : url
			},
			success : function(data) {
				layer.msg(data.info);
				$("#" + obj.divid + i).remove();

			}
		});

		layer.close(index);
	});

}
//重定向按钮
function clickisforwardHandler(event) {
	var i = event.data.index;
	var obj = event.data.obj;
	var status = $("#" + obj.isforwardid + i).attr('ischecked');
	if (status == 'false') {
		$("#" + obj.forwardurldivid + i).show();
		$("#" + obj.isforwardid + i).attr('ischecked', 'true');
	} else if (status == 'true') {
		$("#" + obj.forwardurldivid + i).hide();
		$("#" + obj.isforwardid + i).attr('ischecked', 'false');
	}

	//$("#"+actions.data[i].forwardurlid+i).show();

}

//数据定制
function clickspecialHandler(event){
	var i = event.data.index;
	var obj = event.data.obj;
	var urldata=event.data.obj.urldata;
	var url='/mock_special?pagetype=special&&data='+urldata;
	layer.open({
		  type: 2, 
		  content: [url,'no'] ,
		  area: ['700px', '630px'],
	        fix: false,
	        scrollbar: true,
	        success:function(){	        	
	        	//console.log(urldata);
	        }
		}); 
}
//Url代理
function clickproxyHandler(event){
	var i = event.data.index;
	var obj = event.data.obj;
	var urldata=event.data.obj.urldata;
	var url=event.data.obj.url;
	proxyui(layer,url,'');
	
}
//是否是json字符串
function isJSON(str) {
    if (typeof str == 'string') {
        try {
            var obj=JSON.parse(str);
            if(typeof obj == 'object' && obj ){
                return true;
            }else{
                return false;
            }

        } catch(e) {
            return false;
        }
    }
}




//随机数
function getRandom(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

//按钮动画
function anim(){
        //演示动画开始
     $('.site-doc-icon .layui-anim').on('click', function(){
           var othis = $(this), anim = othis.data('anim');

           //停止循环
     if(othis.hasClass('layui-anim-loop')){
             return othis.removeClass(anim);
               }
      othis.removeClass(anim);
      setTimeout(function(){
      othis.addClass(anim);});
     if(anim === 'layui-anim-scaleSpring'){
           setTimeout(function(){
            othis.removeClass(anim);
             }, 200);
               }
});}

/*
 * 数据映射tab页渲染
 * 
 * rd为urldata中的requestdata
 * 
 * */

function tabcontent(rd,url){
	var is_select_true='';
	var is_select_false='';
    //请求按钮名称
    var btname1='更新';
    var btname2='删除';
	if(rd.is_Forward=='true'){
		is_select_true='selected';
	}else if(rd.is_Forward=='false'){
		is_select_false='selected';
	}
	
	var title='Data'+rd.paramId;
	var content=
 // '<div class="layui-tab-item">'+
	'<div class="layui-container">'+
	'<div class="layui-row">'+
	  ' <div class="layui-col-md2">'+
	  '   <form class="layui-form" action="" lay-filter="form'+rd.paramId+'">'+

	  '     <div class="layui-form-item">'+
	  '        <div class="layui-button-block" >'+
	  '         <button lay-submit="" lay-filter="proxy'+rd.paramId+'"  type="button" class="layui-btn layui-btn-sm layui-btn-radius layui-btn-small layui-anim layui-icon layui-icon-set-fill layui-bg-cyan" >'+
	  '        param代理</button>'+
	  '        </div>'+
	  '      </div>'+ 
	  '     <div class="layui-form-item " >'+
	  '      <label class="layui-form-label">是否禁用</label>'+
	  '       <div class="layui-input-block" style="width:100px">'+
	  '       <input type="checkbox" name="is_disable"  title="禁用" checked="">'+
	  '        </div>'+
	  '     </div>'+
	  '     <div class="layui-form-item">'+
	  '       <label class="layui-form-label">映射参数</label>'+
	  '        <div class="layui-input-block">'+
	  '         <input  id="mapparam'+rd.paramId+'" name="param" type="text" value="'+rd.param+'" required   placeholder="映射参数" autocomplete="off" class="layui-input">'+
	  '        </div>'+
	  '        </div><br>'+
	  '     <div class="layui-form-item">'+
	  '       <label class="layui-form-label">返回数据</label>'+
	  '        <div class="layui-input-block">'+
	  '        <textarea name="data" id="mapdata'+rd.paramId+'" required  placeholder="请输入" class="layui-textarea" style="height:150px" >'+rd.data+'</textarea>'+
	  '        </div>'+
	  '      </div><br>'+
	  '     <div class="layui-form-item">'+
	  '       <button class="layui-btn layui-btn-sm layui-btn-primary layui-btn-radius" lay-submit="" lay-filter="mod'+rd.paramId+'"><i class="layui-icon">&#xe669;</i></button>'+
	  '       <button class="layui-btn layui-btn-sm layui-btn-primary layui-btn-radius" lay-submit="" lay-filter="del'+rd.paramId+'"><i class="layui-icon">&#xe640;</i></button>'+
	  '      </div>'+
	  '  </form> '+
	'</div>'+
	'</div>'+
	'</div>';
	//'</div>';
	
	var data={
			"title":title,
			"content":content
	}
	return data;
}

function addtab(filter,rd,url,form,type,element){

	  var tabdata=tabcontent(rd,url)
	

	
}
function tabcontentaction(url,rd,type,form,cachedata,element,filter){
	//请求url
    var requrl_mod="";
    var requrl_del="/mock/del_requestdata";
	   if(type=='mod'){
		   requrl_mod="/mock/mod_requestdata";
	   }else if(type=='add'){
		   requrl_mod="/mock/mod_requestdata";
	   }
	  
	   
	  form.render();
	  //更新按钮动作
	  form.on('submit(mod'+rd.paramId+')', function(data){
		   var urldata={
				"url":url
		   }    
		   var requestData={
				   "data":data.field.data,
					"param":data.field.param,
					"paramId":rd.paramId,
					"is_Disable":data.field.is_disable=='on'?"true":"false"
			}
		   console.log(requestData);
		  
		   urldata.requestData=requestData;	 
		   $.ajax({          
	           	url:requrl_mod,
	            contentType: "application/json;charset=utf-8",
	             type:"post", 
	             async: false,
	             data:JSON.stringify(urldata),
	             success:function(data){
	            	  //新增或是修改入库,都是修改数据
	      		      cachedata.moddata(requestData);
	            	  layer.msg(data.info);
	            	  if(data.flag=='success'){
	            		  rd.isindao='true';
	            	  }
	             },
	             error:function(xhr,state,errorThrown){
	            	 layer.msg('更新失败,失败原因:'+xhr.responseText);
	             }
		   }
		   );  
		    return false;
	   });
	  
	  //删除按钮动作
	  form.on('submit(del'+rd.paramId+')', function(data){
		   var urldata={
				"url":url
		   }    
		   var requestData={
				   "data":data.field.data,
					"is_Forward":data.field.Is_Forward,
					"param":data.field.param,
					"paramId":rd.paramId,
					"is_Disable":data.field.is_disable
			}
		  
		   urldata.requestData=requestData;	 
		   layer.confirm('确定删除吗?', {
				icon : 3,
				title : '提示'
			}, function(index) {
				console.log(requestData.paramId);
				  $.ajax({          
			           	url:requrl_del,
			            contentType: "application/json;charset=utf-8",
			             type:"post", 
			             async: false,
			             data:JSON.stringify(urldata),
			             success:function(data){
			            	 if(data.flag=='success'){
			            	   //新增或是修改入库,都是修改数据
			      		       cachedata.deldata(requestData.paramId);
			            	   layer.msg(data.info);
			            	   console.log('requestData.paramId='+requestData.paramId);
			            	   element.tabDelete(filter, requestData.paramId);
			            	   element.render('tab('+filter+')'); 
			            		$('#curr_id').text('');
			    				$('#curr_param').text('');
			            	 }else {
			            		 layer.msg(data.info);
			            	 }
			             },
			             error:function(xhr,state,errorThrown){
			            	 layer.msg('删除失败,失败原因:'+xhr.responseText );
			             }
				   }
				   );  

				layer.close(index);
			});
		 
		    return false;
	   });
	  
	  form.on('submit(proxy'+rd.paramId+')', function(data){
		  if(rd.isindao=='true'){
			  proxyui(layer,url,rd.paramId);
		  }else{
			  layer.msg('需要先更新这条数据');
		  }
		
	  });
	  return 0;
}


function uuid() {
	var s = [];
	var hexDigits = "0123456789abcdef";
	for (var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	}
	s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
	s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
	s[8] = s[13] = s[18] = s[23] = "";

	var uuid = s.join("");
	return uuid;
}