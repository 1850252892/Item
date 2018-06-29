/**
 * 
 */
var classification = "";
var imgpath = "";
var images = [];


$(function () {
   // getClasss();
    //弹出框水平垂直居中
    (window.onresize = function () {
        var win_height = $(window).height();
        var win_width = $(window).width();
        if (win_width <= 768) {
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight()) / 2,
                "left": 0
            });
        } else {
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight()) / 2,
                "left": (win_width - $(".tailoring-content").outerWidth()) / 2
            });
        }
    })();

    $("#replaceImg").on("click", function () {
        $(".tailoring-container").toggle();
    });
//裁剪后的处理
    $("#sureCut").on("click", function () {
        if ($("#tailoringImg").attr("src") == null) {
            return false;
        } else {
            var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
            var path = cas.toDataURL('image/jpeg',0.5); //转换为base64地址形式
            var img = '<div class="btn_upload" onclick="deleteImg(this)">'
                + '<img  src="' + path + '" /> ' + '</div>';

            if (images.length > 7) {
                alert("最多只能上传八张图片哦~");
                return;
            }

            $("#imgs").append(img);//显示为图片的形式
            for (var i = 0; i < images.length; i++) {
                var img = images[i];
                if (img.path == path) {
                    alert("图片已经存在");
                    return;
                }
            }
            var img = '<div class="btn_upload" onclick="deleteImg(this)">'
                + '<img  src="' + path + '" /> ' + '</div>';
            // $("#imgs").prepend(img);
			var file=convertBase64UrlToBlob(path);

            var image = {
                "span" : $("#imgs").children("div:first-child").children("span"),
                "file" : file,
                "path" : path
            };
            images.push(image);
            //关闭裁剪框
            closeTailor();
        }
    });
    //cropper图片裁剪
    $('#tailoringImg').cropper({
        aspectRatio: 8 / 5,//默认比例
        preview: '.previewImg',//预览视图
        guides: false,  //裁剪框的虚线(九宫格)
        autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
        movable: false, //是否允许移动图片
        dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
        movable: true,  //是否允许移动剪裁框
        resizable: true,  //是否允许改变裁剪框的大小
        zoomable: true,  //是否允许缩放图片大小
        mouseWheelZoom: true,  //是否允许通过鼠标滚轮来缩放图片
        touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
        rotatable: true,  //是否允许旋转图片
        crop: function (e) {
            // 输出结果数据裁剪图像。
        }
    });
//旋转
    $(".cropper-rotate-btn").on("click", function () {
        $('#tailoringImg').cropper("rotate", 45);
    });
//复位
    $(".cropper-reset-btn").on("click", function () {
        $('#tailoringImg').cropper("reset");
    });
//换向
    var flagX = true;
    $(".cropper-scaleX-btn").on("click", function () {
        if (flagX) {
            $('#tailoringImg').cropper("scaleX", -1);
            flagX = false;
        } else {
            $('#tailoringImg').cropper("scaleX", 1);
            flagX = true;
        }
        flagX != flagX;
    });


});

//将base64转换为blod文件
function convertBase64UrlToBlob(urlData){

    var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte

    //处理异常,将ascii码小于0的转换为大于0
    var ab = new ArrayBuffer(bytes.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i);
    }

    return new Blob( [ab] , {type : 'image/png'});
}

//选择图片
function selectImg(file) {
    var path=window.URL.createObjectURL(file.files[0]);
    var img=new Image();
    img.src=path;
    img.onload=function () {
        path = compress(img, 200, 200, 0.8);
        $('#tailoringImg').cropper('replace', path, false);//默认false，适应高度，不失真
    }
	// var fileReader = new FileReader();
	// var path = "";
	// fileReader.readAsDataURL(file.files[0]);
	// fileReader.onload = function(evt) {
	//     alert(evt);
	// 	path = compress(evt, 500, 400, 0.7)
    //
     //    $('#tailoringImg').cropper('replace', path, false);//默认false，适应高度，不失真
	// }

}

//关闭裁剪框
function closeTailor() {
    $(".tailoring-container").toggle();
}


function compress(img, width, height, ratio) {
    var canvas, ctx, img64;

    canvas = document.createElement('canvas');
    canvas.width = width;
    canvas.height = height;

    ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, width, height);

    img64 = canvas.toDataURL("image/jpeg", ratio);

    return img64;
}




/**
 * 判断图片是否需要压缩
 * @param image          HTMLImageElement
 * @param imageSize      int
 * @returns {*}          HTMLImageElement
 */
function judgeCompress(image,imageSize) {

    //判断图片是否大于300000 bit
    var threshold = 300000;//阈值,可根据实际情况调整
    console.log('imageSize:'+imageSize);
    if(imageSize>threshold){
        var imageData = compress(image);

        var newImage = new Image();
        newImage.src = imageData;
        return newImage;
    }else {
        return image;
    }
}

/**
 *压缩图片
 * @param image         HTMLImageElement
 * @returns {string}    base64格式图像
 */
function compress(image) {
    console.log('compress');
    console.log(image);

    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');

    var imageLength = image.length;
    var width = image.width;
    var height = image.height;

    canvas.width = width;
    canvas.height = height;

    ctx.drawImage(image, 0, 0, width, height);

    //压缩操作
    var quality = 0.5;//图片质量  范围：0<quality<=1 根据实际需求调正
    var imageData = canvas.toDataURL("image/jpeg", quality);

    console.log("压缩前：" + imageLength);
    console.log("压缩后：" + imageData.length);
    console.log("压缩率：" + ~~(100 * (imageLength - imageData.length) / imageLength) + "%");
    return imageData;
}








function deleteImg(img) {

	var src = $(img).children("img")[0].src;
	$(img).remove();
	for (var i = 0; i < images.length; i++) {
		var img = images[i];
		if (src == img.path) {
			images.splice(i, 1)
		}
	}

}
function uploadImgs() {
	for (var i = 0; i < images.length; i++) {
		var img = images[i];
		var form = new FormData();
		form.append("img", img.file);
		$.ajax({
			type : "post",
			url : "/file/uploadImgToQn",
			data : form,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(data, status) {
				if (imgpath == "") {
					imgpath = data;
				} else {
					imgpath = imgpath + "*" + data;
				}
			},
			error : function() {
				alert("服务器异常");
			},
			complete : function() {

			}
		});
	}
}
window.onload = function() {
	$('#classification li').click(function() {
		classification = ($(this).text());
		$("#s_type").empty();
		$("#s_type").append(classification);
	});
};
function addItem() {
	var name = document.getElementById("name").value;
	var detail = document.getElementById("detail").value;
	var price = document.getElementById("price").value;
	var expect = document.getElementById("expect").value;

	if (name == "") {
		alert("请填写一个名称");
		return;
	}
	if (detail == "") {
		alert("你没有填写任何内容");
		return;
	}
	if (classification == "") {
		alert("请选择一个分类");
		return;
	}
	if (price == "") {
		alert("请填写一个预估价格");
		return;

	}
	if (expect == "") {
		alert("请填写一个期望商品");
		return;
	}
	uploadImgs();

	$.ajax({
		url : '/item/uploadItem',
		type : 'post',
		dataType : 'text',
		traditional : true,
		data : {
			'name' : name,
			'detail' : detail,
			'img' : imgpath,
			'expect' : expect,
			'classification' : classification,
			'price' : price
		},
		success : function(data) {
			if (data == "success")
				self.location = "addSuccessPage";
			else
				alert("添加失败");
		},
		error : function() {
			alert("服务器异常-");
		}
	});
	classification = "";
	imgpath = "";
	images = [];
}

function getClasss() {
    $.ajax({
        url: "/item/class_list",
        type: "get",
        dataType: "text",
        success: function (data) {
            var list = JSON.parse(data);
            var list_box = $("#item-type");
            var len = list.length;
            // list_box.empty();
            for (var i = 0; i < len; i++) {
                var li = ' <li><a href="#">'+list[i]+'</a></li>';
                list_box.append(li);
            }
        },
        error: function () {

        }

    })
}

