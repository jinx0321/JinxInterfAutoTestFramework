Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

function test1(){
	console.log('123');
}

function CacheData(id_name) {
	this.id_name = id_name;
	this.index = 0;
	this.cahce_list = new Array();

	//添加缓存数据
	this.adddata = function(obj) {
		this.cahce_list[this.index] = obj;
		this.index = this.index + 1;
	}

	//修改缓存数据
	this.moddata = function(obj) {
		for (var i = 0; i < this.index; i++) {
			if (Reflect.get(obj, this.id_name) === Reflect.get(
					this.cahce_list[i], this.id_name)) {
				      this.cahce_list[i]=obj;
				      console.log('已修改：'+this.cahce_list[i]);
			}
		}
	}
	
	//删除缓存数据
	this.deldata = function(id_value) {
		for (var i = 0; i < this.index; i++) {
			if (Reflect.get(this.cahce_list[i], this.id_name) == id_value) {
				this.cahce_list.remove(i);
				this.index = this.index - 1;
			}
		}
	}

	//通过id获取缓存数据
	this.getobj = function(id_value) {
		for (var i = 0; i < this.index; i++) {
			if (Reflect.get(this.cahce_list[i], this.id_name) == id_value) {
				return this.cahce_list[i];
			}
		}

	}
	
	//获得缓存长度
	this.size = function() {
		return this.index;
	}
}

