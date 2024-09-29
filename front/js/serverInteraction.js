var req;
var token;
var lastResponse;
function sendRequest(xParam, yParam, rParam, needProcess) {
  console.log("ama here");
  req = null;
  var url="http://192.168.10.80:17936/fcgi-bin/server-1.0-jar-with-dependencies.jar"
  try {
    req = new XMLHttpRequest();
  } catch (e){}
  if (req) {
    let body="";
    req.open("POST", url, true);
    if(token==undefined){
      body=`X:${xParam}\nY:${yParam}\nR:${rParam}\nTOKEN:NEW`;
    }else {
      body = `X:${xParam}\nY:${yParam}\nR:${rParam}\nTOKEN:${token}`;
    }

    req.send(body);
    req.onload = function (){
      acceptResponse(needProcess);
    }
    req.onerror = function() {
      alert("Запрос не удался");
    };
  }
}

function acceptResponse(needProcess){
    try { // Важно!
      // только при состоянии "complete"
      if (req.readyState == 4) {
        // для статуса "OK"
        if (req.status == 200) {
          var response=req.response;
          lastResponse=response;
          sessionStorage.setItem("lastResponse", lastResponse);
          console.log(response);
          if(needProcess)
            insertUserHistory(response)
        } else {
          alert("Не удалось получить данные:\n" +
            req.statusText);
        }
      }
    }
    catch( e ) {
      alert('Ошибка: ' + e.description);
      // В связи с багом XMLHttpRequest в Firefox
      // приходится отлавливать ошибку
    }

}

function setToken(tok){
  token=tok
}
function getToken(){
  return token;
}
