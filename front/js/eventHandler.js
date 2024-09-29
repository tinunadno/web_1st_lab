var rbSelection=0;
document.addEventListener('DOMContentLoaded', function() {
  //select box variable
  const selectElement = document.getElementById("user-input-selectBox");
  //radioButtons elements
  const RBForm=document.getElementById("radioButtonForm");
  //textField element
  const userInputTextField=document.getElementById("user-input-textField");

  const getResultButton=document.getElementById("responseButton");

  const resultOutput =document.getElementById("result");

  window.addEventListener("close", function (){
    sendRequest("EXIT", "EXIT", "EXIT", false);
  });


// Save the string when the page loads
  window.addEventListener('load', function() {

    // Retrieve the string when the page loads
    lastResponse = sessionStorage.getItem("lastResponse");
    token=sessionStorage.getItem("token");
    insertUserHistory(lastResponse);
  });

  getResultButton.addEventListener("click", function () {
    //sendRequest(selectElement.value, userInputTextField.value, rbSelection);
  //resultTest.textContent=`X: ${selectElement.value}`+`Y: ${userInputTextField.value}`+`R: ${rbSelection}`;

    const textFieldValue = userInputTextField.value;
    if(isNaN(parseInt(textFieldValue))){
      resultOutput.textContent="y must be an Integer";
      resultOutput.style.color='red';
    }
    else{
      let textFieldIntValue=parseInt(textFieldValue);
      if(textFieldIntValue<-3 || textFieldIntValue>3){
        resultOutput.textContent="y must be in [-3; 3]";
        resultOutput.style.color='red';
      }else{
        if (rbSelection != 0) {
          sendRequest(selectElement.value, userInputTextField.value, rbSelection, true);
          resultOutput.style.color = 'black';
        } else {
          resultOutput.textContent = "chose r size, please";
          resultOutput.style.color = 'red';
        }
      }
    }

  });

  RBForm.addEventListener('change', (event) =>{
    rbSelection=event.target.value;
  })
});

