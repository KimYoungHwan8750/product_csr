


//옵션정의
let options = function (type,data) {
    if(arguments.length===1) {
        console.log("옵션 : get 유형");
        return {
            method: type
        }
    } else if(arguments.length>3){
        console.log("옵션 : '"+type+"' 유형의 formData 전송");

        return {
            method : type,
            body:data
        }
    }

    console.log("옵션 : '"+type+"' 유형의 jsonData 전송")
    return {
         method : type,
         headers: {"Content-Type":"application/json"},
         body:JSON.stringify(data)
        }
}
// 사용법
// get은 url만,
// get을 제외한 요청은 (url,data) 이렇게 두 인자를 보내는데 (url,data,false)를 입력하면
// stringify(data)가 아닌 data가 보내진다. 이때 보낼 data는 formData형식을 따라야한다.
let ajax ={
    //get
    get: async (url)=>{
        try{
            let $fetch = await fetch(url);
            let result = await $fetch.json();
            return result;
        } catch (e){
            console.error(e);
        }
    },

    //post
    post:async (url,data,useFormData)=>{
        try{
            let $fetch;
            if(useFormData){
                $fetch=await fetch(url,options("post",data,null,null));
            } else {
                $fetch=await fetch(url,options("post",data));
            }
            let result = await $fetch.json();
            return result;
        } catch (e){
            console.error(e);
        }

    },

    //patch
    patch:async (url,data,useFormData)=>{
        try{
            let $fetch;
            if(useFormData){
                $fetch=await fetch(url,options("PATCH",data,null,null));
            } else {
                $fetch=await fetch(url,options("PATCH",data));

            }
            let result = await $fetch.json();
            return result;
        } catch (e){
            console.error(e);
        }

    },

    //put
    put:async (url,data,useFormData)=>{
        try{
            let $fetch;
            if(useFormData){
                $fetch=await fetch(url,options("PUT",data,null,null));
            } else {
                $fetch=await fetch(url,options("PUT",data));
            }
            let result = await $fetch.json();
            return result;
        } catch (e){
            console.error(e);
        }
    },

    //delete
    delete:async (url,data,useFormData)=>{
        try{
            let $fetch;
            if(useFormData){
                $fetch=await fetch(url,options("DELETE",data,null,null));
            } else {
                $fetch=await fetch(url,options("DELETE",data));

            }
            let result = await $fetch.json();
            return result;
        } catch (e){
            console.error(e);
        }

    }
}

export {ajax}