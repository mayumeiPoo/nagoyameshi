
let url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?language=ja&input=$(“#address”).val()&inputtype=textquery&fields=formatted_address,photos,rating,geometry&key=AIzaSyCtPm_1RFZHhvrBw95zXKoCHwbba26HJqw"
$(function() {
   // ボタンがクリックされた場合
   $('button').on('click', function(){
     const Http = new XMLHttpRequest();
     const url="https://maps.googleapis.com/maps/api/place/findplacefromtext/json?language=ja&input=$(“#address”).val()&inputtype=textquery&fields=formatted_address,photos,rating,geometry&key=AIzaSyCtPm_1RFZHhvrBw95zXKoCHwbba26HJqw";
     Http.open("GET", url);
Http.send();
Http.onreadystatechange = (e) => {
console.log(Http.responseText)
let resVal = Http.responseText;
}
JSONGetElement ( "results[0].geometry.location.lat" )
   });
 });