function insertUserHistory(userHistory){
  let temp = userHistory.split("\n");
  for(let i=0;i<temp.length;i++){
    temp[i]=temp[i].substring(temp[i].indexOf(":")+1, temp[i].length).split(",");
  }
  console.log(getToken())
  if (getToken() === undefined){
    setToken(temp[0][0])
    sessionStorage.setItem("token", temp[0][0]);
    temp.splice(0, 1);
  }
  console.log(getToken())
  console.log(temp)
  insertHistoryIntoTable(temp)
}
function insertHistoryIntoTable(history){
  const table = document.querySelector('#userHistoryTable tbody');
  while (table.rows.length > 0) {
    table.deleteRow(0); // Удаляем первую строку в tbody
  }
  for(let i=0;i<history[0].length;i++){
    let row = table.insertRow();
    for(let j=0;j<history.length;j++){
      let cell=row.insertCell(j)
      cell.innerHTML=history[j][i];
      if(j===history.length-1){
        if(history[j][i]==="false")
          cell.classList.add("th", "col_0")
        else
          cell.classList.add("th", "col_1")
      }else{
        cell.classList.add("th")
      }
    }
    let cell=row.insertCell(0);
    // noinspection JSValidateTypes
    cell.innerHTML= 1 + i;
    cell.classList.add("tableHeader")
  }
  const resultOutput =document.getElementById("result");
  let sr = history[3][0];
  if(sr==="false"){
    resultOutput.textContent="your point doesn't belongs to function"
  }else{
    resultOutput.textContent="your point belongs to function"
  }

  displayPoint(history[0][history[0].length-1], history[1][history[1].length-1], history[2][history[2].length-1])
}

function displayPoint(x, y, r){
  let point = document.getElementById("point");
  x=Number(x)
  y=Number(y)
  r=Number(r)
  x=(x+r)/(2*r)*100
  y=(y+r)/(2*r)*100
  y=100-y
  point.style.left="calc("+x+"% - 5px)"
  point.style.top="calc("+y+"% - 3px)"
}
