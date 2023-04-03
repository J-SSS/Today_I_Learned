class CanvasLayer {
  constructor() {
    let newCan =document.createElement("canvas");
    let newCtx =newCan.getContext("2d")
    newCan.onclick = function (event) {
      const x = event.offsetX
      const y = event.offsetY
      newCtx.fillRect(x - 15, y - 15, 30, 30);

    }
    return newCan
  }
}

const button = document.getElementById("button1")
const canvasBox = document.querySelector(".canvasBox");



button.addEventListener('click',function (){
  let newCan =document.createElement("canvas");
  let newCtx =newCan.getContext("2d")
  newCan.onclick = function (event) {
    const x = event.offsetX
    const y = event.offsetY
    newCtx.fillRect(x - 15, y - 15, 30, 30);
  }

  canvasBox.appendChild(newCan);

} );

button2.addEventListener("click",function (){
  let newCan = new CanvasLayer()
  console.log(newCan)
  canvasBox.appendChild(newCan);
})

const canvas = document.getElementById("myCanvas");
const ctx = canvas.getContext("2d");


canvas.width = 200;
canvas.height = 200;

// function liner(){
//   ctx.lineWidth=0.1;
//   let count=20;
//   while (count<height){
//     ctx.beginPath();
//     ctx.moveTo(10, count);
//     ctx.lineTo(width-10, count);
//     ctx.stroke();
//     count+=30;
//   }
//
//
// }
// liner();

canvas.style.cursor = 'pointer';

let painting;

function finishWork(event) {
  painting = false;
}

function startWork() {
  painting = true;
}

ctx.lineWidth = 20;
function onMouseMove(event) {
  const x = event.offsetX;
  const y = event.offsetY;
  if (!painting) {
    ctx.beginPath();
    ctx.moveTo(x, y);
  } else {
    ctx.lineTo(x, y);
    ctx.stroke();
  }

}

canvas.addEventListener("mousemove", onMouseMove);
canvas.addEventListener("mousedown", startWork);
canvas.addEventListener("mouseup", finishWork);
canvas.addEventListener("mouseleave", finishWork);




//마우스 위치에 사각형 찍어내기
canvas.onclick = function (event) {
  const x = event.offsetX
  const y = event.offsetY
  ctx.fillRect(x - 15, y - 15, 30, 30);
}


// rowInputBtn.addEventListener("click",(e)=>{
//   let accordionHeader = document.createElement("div");
//   accordionHeader.classList.add("accordion-item");
//   scheduleBox.append(accordionHeader);
//     let headerH2 = document.createElement("h2");
//     headerH2.classList.add("accordion-header");
//     accordionHeader.append(headerH2);
//       let headerBtn = document.createElement("btn");
//       headerBtn.classList.add("accordion-button");
//       headerBtn.classList.add("collapsed");
//       headerBtn.classList.add("scheduleBtn");
//       //어트리뷰트는 설정안함
//         headerH2.append(headerBtn);
//         let container = document.createElement("div");
//         container.classList.add("container")
//         container.classList.add("scheduleContainer")
//         headerBtn.append(container)
//           let scheduleRow = document.createElement("div");
//           scheduleRow.classList.add("row");
//           scheduleRow.classList.add("scheduleRow");
//           container.append(scheduleRow);
//             let scheduleLeft = document.createElement("div");
//             scheduleLeft.classList.add("col");
//             scheduleLeft.classList.add("col-md-1");
//             scheduleLeft.classList.add("scheduleLeft");
//             scheduleLeft.innerText = "hh:mm"
//             scheduleRow.append(scheduleLeft);
//             let scheduleCenter = document.createElement("div");
//             scheduleCenter.classList.add("col");
//             scheduleCenter.classList.add("col-md-11-1");
//             scheduleCenter.classList.add("scheduleCenter");
//             scheduleRow.append(scheduleCenter);
//               let scheduleCenterRow = document.createElement("div");
//               scheduleCenterRow.classList.add("row");
//               scheduleCenter.append(scheduleCenterRow);
//                 let scheduleCenterRow11 = document.createElement("div");
//                 scheduleCenterRow11.classList.add("col");
//                 scheduleCenterRow11.classList.add("col-s-11");
//                 scheduleCenterRow.append(scheduleCenterRow11);
//                   let scheduleCenterRow11Row = document.createElement("div");
//                   scheduleCenterRow11Row.classList.add("row");
//                   scheduleCenterRow11.append(scheduleCenterRow11Row);
//                     let scheduleTitle = document.createElement("div");
//                     scheduleTitle.classList.add("col");
//                     scheduleTitle.classList.add("scheduleTitle");
//                     scheduleTitle.innerText="[ test2 ]"
//                     scheduleCenterRow11Row.append(scheduleTitle);
//                       let scheduleTitleText = document.createElement("span");
//                       scheduleTitleText.classList.add("scheduleTitleText");
//                       scheduleTitle.append(scheduleTitleText);
//                   let scheduleCenterRow11Row2 = document.createElement("div");
//                   scheduleCenterRow11Row2.classList.add("row");
//                   scheduleCenterRow11.append(scheduleCenterRow11Row2);
//                     let scheduleContent = document.createElement("div");
//                     scheduleContent.classList.add("col");
//                     scheduleContent.classList.add("scheduleContent");
//                     scheduleContent.innerText = "경유지 #1 → 경유지 #2 → 경유지 #3"
//                     scheduleCenterRow11Row2.append(scheduleContent);
//                 let scheduleRight = document.createElement("div");
//                 scheduleRight.classList.add("col");
//                 scheduleRight.classList.add("col-md-1");
//                 scheduleRight.classList.add("scheduleRight");
//                 scheduleRight.innerHTML= ""
//                 scheduleCenterRow11.append(scheduleRight);
//     let bodyBox = document.createElement("div");
//     bodyBox.classList.add("accordion-collapse");
//     bodyBox.classList.add("collapse");
//     accordionHeader.append(bodyBox);
//       let accordionBody = document.createElement("div");
//       accordionBody.classList.add("accordion-body");
//       accordionBody.classList.add("collapse");
//       bodyBox.append(accordionBody);
// });
