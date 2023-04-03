
////////////////// 페이지 기능 관련  //////////////////

const newCanvas = document.getElementsByClassName("accordion-body")
Array.from(newCanvas).forEach((canvasFrame)=>{
  canvasFrame.addEventListener("click",()=>{
    //16은 부트스트랩에 설정된 패딩값임..
    let temp = 500/1200*canvasFrame.clientWidth+16
    canvasFrame.style.height =temp+"px";
    // canvasFrame.style.height =500+"px";
    let newCanvas = new CanvasCreate()
    let mainC = newCanvas.canvas
    let subC = newCanvas.subCanvas
    subC.classList.add("subCanvasX")
    // canvasFrame.appendChild(subC)
    canvasFrame.append(subC)
    canvasFrame.append(mainC)
  },{once:true})
});

//새 일정 행 추가용
const rowInputBtn = document.querySelector(".rowInputBtn")
const scheduleBox = document.querySelector(".accordion")
rowInputBtn.addEventListener("click",(e)=>{
  let sample = document.querySelector(".accordion-item").cloneNode(true);
  let acBody = sample.childNodes[3].childNodes[1];
  sample.childNodes[1].childNodes[1].setAttribute("data-bs-target",`#collapse${++canvasCnt}`)
  sample.childNodes[3].setAttribute("id",`collapse${canvasCnt}`)
  sample.childNodes[3].childNodes[1].addEventListener("click",()=>{
    //16은 부트스트랩에 설정된 패딩값임..
    let temp = 500/1200*acBody.clientWidth+16
    acBody.style.height =temp+"px";
    let newCanvas = new CanvasCreate()
    let mainC = newCanvas.canvas
    let subC = newCanvas.subCanvas
    subC.classList.add("subCanvasX")
    acBody.append(subC)
    acBody.append(mainC)
  },{once:true})
  scheduleBox.appendChild(sample)
})

const buttons = ["red","orange","yellow","green","blue","navy", "purple","black","white"];

buttons.forEach((content) => {
  let button = document.querySelector(`.${content}`);
  button.style.background = content;
  button.style.color = "white";
  button.style.width = "20px";
  button.style.height = "20px";
  button.style.borderRadius = "5px";
  button.style.boxShadow = "1px 2px 2px gray";
});
