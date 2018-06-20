
;(function($, window, document,undefined) {
	
    //定义Slide的构造函数
    var Slide = function(ele, opt) {
        this.$element = ele,
        this.defaults = {
        	
        	type : 1,
        	vOffset: 5,
            vSpace : 5,
            imgName : ['1.jpg', '2.jpg'],
            imgSize : {
	        	width: '400px',
	        	height: '200px',
	        },
	        blockSize : {
	        	width: '50px',
	        	height: '50px',
	        },
	        barSize : {
	        	width : '400px',
	        	height : '40px',
	        },
            ready : function(){},
        	success : function(){},
            error : function(){}
            
        },
        this.options = $.extend({}, this.defaults, opt)
    };
    
    
    //定义Slide的方法
    Slide.prototype = {
        
        init: function() {
        	var _this = this;
        	
        	//加载页面
        	this.loadDom();
        	this.options.ready();
        	
        	this.$element[0].onselectstart = document.body.ondrag = function(){ 
				return false; 
			};
        	
        	//按下
        	this.htmlDoms.move_block.on('touchstart', function(e) {
        		_this.start(e);
        	});
        	
        	this.htmlDoms.move_block.on('mousedown', function(e) {
        		_this.start(e);
        	});
        	
        	//拖动
            window.addEventListener("touchmove", function(e) {
            	_this.move(e);
            });
            window.addEventListener("mousemove", function(e) {
            	_this.move(e);
            });
            
            //鼠标松开
            window.addEventListener("touchend", function() {
            	_this.end();
            });
            window.addEventListener("mouseup", function() {
            	_this.end();
            });
            
            //刷新
            _this.$element.find('.verify-refresh').on('click', function() {
            	_this.refresh();
            });
        },
        
        //初始化加载
        loadDom : function() {
        	this.img_rand = Math.floor(Math.random() * this.options.imgName.length);			//随机的背景图片
        	
        	var panelHtml = '';
        	var tmpHtml = '';
        	
        	if(this.options.type != 1) {		//图片滑动
        		panelHtml += '<div class="verify-img-panel"><div  class="verify-refresh"><i class="iconfont icon-refresh"></i></div><div class="verify-gap"></div></div>';
        		tmpHtml = '<div  class="verify-sub-block"></div>';
        	}
        	
        	panelHtml += '<div class="verify-bar-area"><span  class="verify-msg">向右滑动完成验证</span><div class="verify-left-bar"><span  class="verify-msg"></span><div  class="verify-move-block"><i  class="verify-icon iconfont icon-right"></i>'+tmpHtml+'</div></div></div>';
        	this.$element.append(panelHtml);
        	
        	this.htmlDoms = {
        		gap : this.$element.find('.verify-gap'),
        		sub_block : this.$element.find('.verify-sub-block'),
        		img_panel : this.$element.find('.verify-img-panel'),
        		bar_area : this.$element.find('.verify-bar-area'),
        		move_block : this.$element.find('.verify-move-block'),
        		left_bar : this.$element.find('.verify-left-bar'),
        		msg : this.$element.find('.verify-msg'),
        		icon : this.$element.find('.verify-icon'),
        		refresh :this.$element.find('.verify-refresh')
        	};
        	
        	this.status = false;	//鼠标状态
        	this.setSize = this.resetSize(this);	//重新设置宽度高度
        	
        	this.htmlDoms.gap.css({'width': this.options.blockSize.width, 'height': this.options.blockSize.height});
        	this.htmlDoms.sub_block.css({'width': this.options.blockSize.width, 'height': this.options.blockSize.height});
        	this.htmlDoms.img_panel.css({'width': this.setSize.img_width, 'height': this.setSize.img_height, 'background': 'url(images/'+this.options.imgName[this.img_rand]+')', 'background-size' : this.setSize.img_width + ' '+ this.setSize.img_height});
        	this.htmlDoms.bar_area.css({'width': this.setSize.bar_width, 'height': this.options.barSize.height, 'line-height':this.options.barSize.height});
        	this.htmlDoms.move_block.css({'width': this.options.barSize.height, 'height': this.options.barSize.height});
        	this.htmlDoms.left_bar.css({'width': this.options.barSize.height, 'height': this.options.barSize.height});
        	
        	this.randSet();
        },
        
        //鼠标按下
        start: function(e) {
        	this.htmlDoms.msg.text('');
        	this.htmlDoms.move_block.css('background-color', '#337ab7');
        	this.htmlDoms.left_bar.css('border-color', '#337AB7');
        	this.htmlDoms.icon.css('color', '#fff');
        	e.stopPropagation();
        	this.status = true;
        	
        },
        
        //鼠标移动
        move: function(e) {
        	if(this.status) {
	            if(!e.touches) {    //兼容移动端
	                var x = e.clientX;
	            }else {     //兼容PC端
	                var x = e.touches[0].pageX;
	            }
	            var bar_area_left = Slide.prototype.getLeft(this.htmlDoms.bar_area[0]); 
	            var move_block_left = x - bar_area_left; //小方块相对于父元素的left值
	            
	            
	            if(this.options.type != 1) {		//图片滑动
	            	if(move_block_left >= this.htmlDoms.bar_area[0].offsetWidth - parseInt(parseInt(this.options.blockSize.width)/2) - 2) {
	                	move_block_left = this.htmlDoms.bar_area[0].offsetWidth - parseInt(parseInt(this.options.blockSize.width)/2) - 2;
	            	}
	            	
	            	
	            	
	            	
	            	
	            }else {		//普通滑动
	            	if(move_block_left >= this.htmlDoms.bar_area[0].offsetWidth - parseInt(parseInt(this.options.barSize.height)/2) + 3) {
	            		this.$element.find('.verify-msg:eq(1)').text('松开验证');
	                	move_block_left = this.htmlDoms.bar_area[0].offsetWidth - parseInt(parseInt(this.options.barSize.height)/2) + 3;
	            	}else {
	            		this.$element.find('.verify-msg:eq(1)').text('');
	            	}
	            }
	            
	            
	            if(move_block_left <= 0) {
            		move_block_left = parseInt(parseInt(this.options.blockSize.width)/2);
            	}
	            
	            //拖动后小方块的left值
	            this.htmlDoms.move_block.css('left', move_block_left-parseInt(parseInt(this.options.blockSize.width)/2) + "px");
	            this.htmlDoms.left_bar.css('width', move_block_left-parseInt(parseInt(this.options.blockSize.width)/2) + "px");
	        }
        },
        
        //鼠标松开
        end: function() {
        	
        	var _this = this;
        	
        	//判断是否重合
        	if(this.status) {
        		
        		if(this.options.type != 1) {		//图片滑动
        			
        			var vOffset = parseInt(this.options.vOffset);
		            if(parseInt(this.htmlDoms.gap.css('left')) >= (parseInt(this.htmlDoms.move_block.css('left')) - vOffset) && parseInt(this.htmlDoms.gap.css('left')) <= (parseInt(this.htmlDoms.move_block.css('left')) + vOffset)) {
		            	this.htmlDoms.move_block.css('background-color', '#5cb85c');
		            	this.htmlDoms.left_bar.css({'border-color': '#5cb85c', 'background-color': '#fff'});
		            	this.htmlDoms.icon.css('color', '#fff');
		            	this.htmlDoms.icon.removeClass('icon-right');
		            	this.htmlDoms.icon.addClass('icon-check');
		            	this.htmlDoms.refresh.hide();
		            	this.htmlDoms.move_block.unbind('mousedown touchstart');
		            	this.options.success();
		            }else{
		            	this.htmlDoms.move_block.css('background-color', '#d9534f');
		            	this.htmlDoms.left_bar.css('border-color', '#d9534f');
		            	this.htmlDoms.icon.css('color', '#fff');
		            	this.htmlDoms.icon.removeClass('icon-right');
		            	this.htmlDoms.icon.addClass('icon-close');
		            	
		            	setTimeout(function () { 
					    	_this.htmlDoms.move_block.animate({'left':'0px'}, 'fast');
					    	_this.htmlDoms.left_bar.animate({'width': '40px'}, 'fast');
					    	_this.htmlDoms.left_bar.css({'border-color': '#ddd'});
					    	
					    	_this.htmlDoms.move_block.css('background-color', '#fff');
					    	_this.htmlDoms.icon.css('color', '#000');
					    	_this.htmlDoms.icon.removeClass('icon-close');
		            		_this.htmlDoms.icon.addClass('icon-right');
		            		_this.$element.find('.verify-msg:eq(0)').text('向右滑动完成验证');
					    	
					    }, 400);
		            	
		            	this.options.error();
		            }
        			
        		}else {		//普通滑动
        			
        			if(parseInt(this.htmlDoms.move_block.css('left')) >= (parseInt(this.setSize.bar_width) - parseInt(this.options.barSize.height) - parseInt(this.options.vOffset))) {
        				this.htmlDoms.move_block.css('background-color', '#5cb85c');
        				this.htmlDoms.left_bar.css({'color': '#4cae4c', 'border-color': '#5cb85c', 'background-color': '#fff' });
        				this.htmlDoms.icon.css('color', '#fff');
		            	this.htmlDoms.icon.removeClass('icon-right');
		            	this.htmlDoms.icon.addClass('icon-check');
		            	this.htmlDoms.refresh.hide();
		            	this.htmlDoms.move_block.unbind('mousedown');
		            	this.htmlDoms.move_block.unbind('touchstart');
        				this.$element.find('.verify-msg:eq(1)').text('验证成功');
        				this.options.success();
        			}else {
        				
        				this.htmlDoms.move_block.css('background-color', '#d9534f');
		            	this.htmlDoms.left_bar.css('border-color', '#d9534f');
		            	this.htmlDoms.icon.css('color', '#fff');
		            	this.htmlDoms.icon.removeClass('icon-right');
		            	this.htmlDoms.icon.addClass('icon-close');
		            	
		            	setTimeout(function () { 
					    	_this.htmlDoms.move_block.animate({'left':'0px'}, 'fast');
					    	_this.htmlDoms.left_bar.animate({'width': '40px'}, 'fast');
					    	_this.htmlDoms.left_bar.css({'border-color': '#ddd'});
					    	
					    	_this.htmlDoms.move_block.css('background-color', '#fff');
					    	_this.htmlDoms.icon.css('color', '#000');
					    	_this.htmlDoms.icon.removeClass('icon-close');
		            		_this.htmlDoms.icon.addClass('icon-right');
		            		_this.$element.find('.verify-msg:eq(0)').text('向右滑动解锁');
					    	
					    }, 400);
		            	
		            	this.options.error();
        			}
        		}
        		
	            this.status = false;
        	}
        },
        
        
        resetSize : function(obj) {
        	var img_width,img_height,bar_width,bar_height;	//图片的宽度、高度，移动条的宽度、高度
        	var parentWidth = obj.$element.parent().width() || $(window).width();
        	var parentHeight = obj.$element.parent().height() || $(window).height();
        	
       		if(obj.options.imgSize.width.indexOf('%')!= -1){
        		img_width = parseInt(obj.options.imgSize.width)/100 * parentWidth + 'px';
        		}else {
				img_width = obj.options.imgSize.width;
			}
		
			if(obj.options.imgSize.height.indexOf('%')!= -1){
        		img_height = parseInt(obj.options.imgSize.height)/100 * parentHeight + 'px';
        		}else {
				img_height = obj.options.imgSize.height;
			}
		
			if(obj.options.barSize.width.indexOf('%')!= -1){
        		bar_width = parseInt(obj.options.barSize.width)/100 * parentWidth + 'px';
        		}else {
				bar_width = obj.options.barSize.width;
			}
		
			if(obj.options.barSize.height.indexOf('%')!= -1){
        		bar_height = parseInt(obj.options.barSize.height)/100 * parentHeight + 'px';
        		}else {
				bar_height = obj.options.barSize.height;
			}
		
			return {img_width : img_width, img_height : img_height, bar_width : bar_width, bar_height : bar_height};
       	},
        
        //随机出生点位
        randSet: function() {
        	var rand1 = Math.floor(Math.random()*9+1);
        	var rand2 = Math.floor(Math.random()*9+1);
        	var top = rand1 * parseInt(this.setSize.img_height)/15 + parseInt(this.setSize.img_height) * 0.1;
        	var left = rand2 * parseInt(this.setSize.img_width)/15 + parseInt(this.setSize.img_width) * 0.1;
        	
        	this.$element.find('.verify-img-panel').css('margin-bottom', this.options.vSpace + 'px');
        	this.$element.find('.verify-gap').css({'top': top, 'left': left});
          	this.$element.find('.verify-sub-block').css({'top':'-'+(parseInt(this.setSize.img_height)- top + this.options.vSpace + 2)+'px', 'background-image': 'url(images/'+this.options.imgName[this.img_rand]+')', 'background-size': this.setSize.img_width + ' '+ this.setSize.img_height,'background-position-y': '-'+top+ 'px', 'background-position-x': '-'+left+'px'});
        },
        
        //刷新
        refresh: function() {
        	this.randSet();
        	this.img_rand = Math.floor(Math.random() * this.options.imgName.length);			//随机的背景图片
            this.$element.find('.verify-img-panel').css({'background': 'url(images/'+this.options.imgName[this.img_rand]+')', 'background-size': this.setSize.img_width + ' '+ this.setSize.img_height});
            this.$element.find('.verify-sub-block').css({'background-image': 'url(images/'+this.options.imgName[this.img_rand]+')', 'background-size': this.setSize.img_width + ' '+ this.setSize.img_height});
        },
        
        //获取left值
        getLeft: function(node) {
			var left = $(node).offset().left; 
            return left;
        }
    };

    //在插件中使用slideVerify对象
    $.fn.slideVerify = function(options, callbacks) {
        var slide = new Slide(this, options);
        slide.init();
    };

})(jQuery, window, document);
