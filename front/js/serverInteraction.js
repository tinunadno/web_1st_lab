var req;
let token = "NEW";
function sendRequest(xParam, yParam, rParam) {
  console.log("ama here");
  req = null;
  var url="http://192.168.10.80:17936/fcgi-bin/server.jar"
  try {
    req = new XMLHttpRequest();
  } catch (e){}
  if (req) {
    req.open("POST", url, true);

    const body=`X:${xParam}\nY:${yParam}\nR:${rParam}\nTOKEN:${token}`;

    req.send(body);
    req.onload = function (){
      acceptResponse();
    }
    req.onerror = function() {
      alert("Запрос не удался");
    };
  }
}

function acceptResponse(){
    try { // Важно!
      // только при состоянии "complete"
      if (req.readyState == 4) {
        // для статуса "OK"
        if (req.status == 200) {
          var response=req.response;
          console.log(response);
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
