function CacheData(id_name) {
    this.id_name = id_name;
    this.nullobj={};
    this.cahce_list = new Array();

    //添加缓存数据
    this.adddata = function(obj) {
        this.cahce_list[this.cahce_list.length] = obj;
    }

    //修改缓存数据
    this.moddata = function(obj) {
        for (var i = 0; i < this.cahce_list.length; i++) {
            if (Reflect.get(obj, this.id_name) === Reflect.get(this.cahce_list[i], this.id_name)) {    
            	this.cahce_list[i]=obj;
            }
        }
    }
    
    //删除缓存数据
    this.deldata = function(id_value) {
        for (var i = 0; i < this.cahce_list.length; i++) {
            if (Reflect.get(this.cahce_list[i], this.id_name) == id_value) {
                this.cahce_list[i]=this.nullobj;
            }
        }
    }

    //通过id获取缓存数据
    this.getobj = function(id_value) {
        for (var i = 0; i < this.cahce_list.length; i++) {
            if (Reflect.get(this.cahce_list[i], this.id_name) == id_value) {
                return this.cahce_list[i];
            }
        }

    }
    
    //获得缓存长度
    this.size = function() {
        var len=0;
          for (var i = 0; i < this.cahce_list.length; i++) {
            if (this.cahce_list[i]!=this.nullobj) {
                len++;
            }
        }
        return len;
    }
}