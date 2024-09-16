var req;
var token="NEW";

function sendRequest(x, y, r) {
  //for till now its just an emulation of server response, fcgi isnt working(
  serverResp=generateStory(x, y, r);
  insertUserHistory(serverResp);
}
posibleXValues=[-5, -4, -3, -2, -1, 0, 1, 2, 3];
posibleYValues=[-3, -2, -1, 0, 1, 2, 3];
posibleRValues=[1, 1.5, 2, 2.5, 3]
function generateStory(x_, y_, r_){
  //also just a server emulation
  length=Math.floor(Math.random()*10)+1;
  xStory="X:";
  yStory="Y:";
  rStory="R:";
  serverResponseStory="SR:";
  temp="";
  for(let i=0;i<length;i++){
    x=posibleXValues[Math.floor(Math.random()*posibleXValues.length)];
    y=posibleYValues[Math.floor(Math.random()*posibleYValues.length)];
    r=posibleRValues[Math.floor(Math.random()*posibleRValues.length)];
    serverResponse=processPoint(x, y, r)+0;
    xStory+=temp+x;
    yStory+=temp+y;
    rStory+=temp+r;
    serverResponseStory+=temp+serverResponse;
    temp=",";
  }
  return xStory+","+x_+"\n"+yStory+","+y_+"\n"+rStory+","+r_+"\n"+serverResponseStory+","+(processPoint(x_, y_, r_)+0);
}
function processPoint(x, y, r){
  return ((x*x+y*y)<((r*r)/2) && x<0 && y>0 || y>x-r && x>0 && y<0 || -r/2<x && x<0 && y<0 && y>-r);
}
// function sendRequest(xParam, yParam, rParam) {
//   console.log("ama here");
//   req = null;
//   var url="http://192.168.10.80:17936/httpd-root/fcgi-bin/web_1st_lab_back-1.0-SNAPSHOT.jar"
//   try {
//     req = new XMLHttpRequest();
//   } catch (e){}
//   if (req) {
//     req.open("GET", url, true);
//
//     const body=`X:${xParam}\nY:${yParam}\nR:${rParam}\n${token}`;
//
//     req.send(body);
//     req.onload = function (){
//       acceptResponse();
//     }
//     req.onerror = function() {
//       alert("Запрос не удался");
//     };
//   }
// }
//
// function acceptResponse(){
//     try { // Важно!
//       // только при состоянии "complete"
//       if (req.readyState == 4) {
//         // для статуса "OK"
//         if (req.status == 200) {
//           var response=req.response;
//           console.log(response);
//         } else {
//           alert("Не удалось получить данные:\n" +
//             req.statusText);
//         }
//       }
//     }
//     catch( e ) {
//       alert('Ошибка: ' + e.description);
//       // В связи с багом XMLHttpRequest в Firefox
//       // приходится отлавливать ошибку
//     }
//
// }
