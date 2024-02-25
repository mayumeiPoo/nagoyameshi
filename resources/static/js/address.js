$(function() {
   $('button').on('click', () =>{
     const Https = new XMLHttpRequest();
const url=`https//maps.googleapis.com/maps/api/geocode/json?address=$(“#address”).val()&key=AIzaSyCtPm_1RFZHhvrBw95zXKoCHwbba26HJqw`;
Http.open("GET", url);
Http.send();
Http.onreadystatechange = (e) => {
console.log(Http.responseText)
let resVal = Http.responseText;
}
   });
 });