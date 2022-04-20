/*
 console.log("하이");

function getFormJson(){

    let form = document.querySelector("#form");
    let data = new FormData(form);
    let serializeForData = serialize(data);

    console.log(JSON.stringify(serializeForData));

}


function serialize (rawData){

    let rtnData = {};
    for (let [key, value] of rawData){
         let set = document.querySelectorAll("[name=" + key + "]");

         //Array Values
         if(sel.length > 1){
                if(rtnData[key] === undefined){
                        rtnData[key] = [];
                }
                rtnData[key].push(value);
         }

         //Other
         else{
            rtnData[key] = value;
         }
    }

    return rtnData;

}*/

    var obj = { width:1680, height:1050 };
    var data1 = $.param( obj ); // 자바스크립트 객체(리터럴)을 쿼리스트링으로 만들어준다.
    console.log(data1)          // 결과 : width=1680&height=1050


    var data2 = $("form").serialize();  // form의 입력데이터를 쿼리스트링으로 만들어준다.
    console.log(data2);               // 결과 : a=1&b=2&c=3&d=4&e=5


    var data3 = $('form').serializeArray(); // form의 입력데이터를 배열의 Object형태로 만들어준다.
    console.log(data3);

    var dataToObjectArray = new Array();

function addOrderList(){
    console.log("추가 이벤트");

    var dataToString = $("form").serialize();
    console.log(dataToString);

    var dataToObject = $("form").serializeArray();
    console.log(dataToObject);

    dataToObjectArray.push(dataToObject);

    console.log("배열");
    console.log(dataToObjectArray);

/*
    $.ajax({
        type:"POST",
        url : "order",
        data : JSON.stringify(dataToObjectArray),
        contentType : 'application/json; charset=UTF-8',
        dataType : "json",
        success : function(data){
            alert("분석이 완료되었습니다.");
        },
        error : function(request, status, error){
            console.log("AJAX_ERROR");
        }

    });
*/

    console.log(JSON.stringify(dataToObjectArray));

    fetch("/order",{
        method : 'POST',
        headers : {'Content-Type' : 'application/json'},
        body : JSON.stringify(dataToObjectArray),
    }).then(response => console.log(response));




}