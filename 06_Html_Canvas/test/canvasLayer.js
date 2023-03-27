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
