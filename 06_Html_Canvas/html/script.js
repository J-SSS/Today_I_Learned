window.onload = function (){
  function $(x){return document.getElementById(x)}
  const box = document.querySelector(".accordion-header").clientWidth-40
  let canvasCnt = 0;

  let jTest = "어케하는겨";
  let canvasParser = {

    layerArrays : {123 : 123, jTest
    }
  }
  console.log(canvasParser.layerArrays)
  console.log(JSON.parse(JSON.stringify(canvasParser)))



////////////////// 캔버스 생성용 클래스 //////////////////
class CanvasCreate {
  index;
  canvas; context; subCanvas; subContext;
  layerArray = []; //레이어 구현용 배열
  activatedTool; //활성화 툴 체크용
  pageZoom = box/1200; //페이지 사이즈 변화에 따른 배율 조졍용(아직)
  functionZoom = 1.0; //줌기능 배율 조졍용

  constructor() {
    // 캔버스 본체
    this.canvas = document.createElement("canvas");
    this.context = this.canvas.getContext("2d")
    // 레이어 구현용 보조캔버스(미완성)
    this.subCanvas = document.createElement("canvas");
    this.subContext = this.subCanvas.getContext("2d");
    //캔버스 초기화.. 길어질 것 같아서 일단 빼둠
    this.init()
  }

  //초기설정 따로빼줬음
  init(){
    this.index = ++canvasCnt;

    this.canvas.width = 1200;
    this.canvas.height = 500;
    this.subCanvas.width = 1200;
    this.subCanvas.height = 500;
    this.context.strokeStyle = "black";
    this.context.lineWidth = 0.5;
    this.context.scale(1.0,1.0)
    this.context.translate(0,0)
    this.context.save();
    this.pageLiner(this.canvas.width,this.canvas.height)
    this.toolActivate()

    // test

    this.canvas.addEventListener("click",()=>{
      console.log(this.index)
      console.log(this.layerArray)
    })

  }

  // 배율 조정 및 윈도우 사이즈 조절시 수정된 좌표값 생성 함수
  xy(xy){
    return xy/this.pageZoom/this.functionZoom
    // return xy
    /*
    scale()적용시 기준점이 이동하여 눈에 보이는 좌표와 실제 캔버스내 좌표가 달라지는데
    눈에 보이는 좌표를 scale()로 설정한 배율로 다시 나눠주면 캔버스상 좌표와 눈에 보이는 좌표가 일치함
    pageZoom도 마찬가지 원리.. 페이지 사이즈 변화를 감지하여 변수에 새로운 값 부여
     */
  }

  // 캔버스 기본 배경
  pageLiner(width,height){

    let x = 0;
    let y = 0;
    while (x<=height){
      this.context.beginPath();
      if(x===0 || height-x===0){
        this.context.strokeStyle="black"
        this.context.lineWidth=1.0;
      } else if(x%50===0){
          this.context.strokeStyle="gray"
          this.context.lineWidth=0.2;
      } else{
        this.context.strokeStyle="gray"
        this.context.lineWidth=0.1;
      }
      this.context.moveTo(0, x);
      this.context.lineTo(width, x);
      this.context.stroke();
      x+=10;
    }
    while (y<=width){
      this.context.beginPath();
      if(y===0 || y===width){
        this.context.strokeStyle="black"
        this.context.lineWidth=1.0;
      } else if(y%50===0){
        if(y/50===12){
          this.context.strokeStyle="red"
          this.context.lineWidth=0.3;
        } else {
          this.context.strokeStyle="gray"
          this.context.lineWidth=0.2;
        }
      } else{
        this.context.strokeStyle="gray"
        this.context.lineWidth=0.1;
      }
      this.context.moveTo(y, 0);
      this.context.lineTo(y, height);
      this.context.stroke();
      y+=10;
    }
    this.pushCanvas(this.canvas)
    this.context.restore()
    ///배열에 들어간 캔버스가 계속 변경되는 문제 해결
  }


  //레이어 배열에 레이어를 담고, 본 캔버스에 그린다
  pushImage(img,x,y){
    this.layerArray.push({img,x,y})
    this.context.drawImage(img, x, y)
  }
  pushPath(pathObj){
    this.layerArray.push(pathObj)
    this.context.stroke(pathObj)
  }
  pushCanvas(canvas){
    let tempImg = new Image();
    tempImg.src = canvas.toDataURL()
    this.layerArray.push(tempImg)
    this.context.drawImage(canvas, 0, 0)
  }

  //스템프 툴
  activateStamp() {
    let myThis = this
    function drawHandler(e){
      if(myThis.activatedTool!=="stamp"){
        myThis.canvas.removeEventListener("mousedown",drawHandler)
      } else {
        myThis.subContext.clearRect(0,0,100,100)
        let imgElem = new Image();
        imgElem.src = "../img/star.png"
        myThis.pushImage(imgElem, myThis.xy(e.offsetX-25),myThis.xy(e.offsetY-25))

      }
    }
    this.canvas.addEventListener("mousedown",drawHandler)
  };


  //미완성
  activateSelector(){
    this.canvas.addEventListener("mousemove",(e)=>{
      let x = e.offsetX
      let y = e.offsetY
      this.layerArray.forEach((c)=>{
        if(c instanceof Path2D){
          console.log(this.context.isPointInStroke(c,x,y))
        } else {
          // console.log(this.context.isPointInStroke(x,y))
        }
      })
    })
  };

  //직선 툴
  // activateLine(){
  //   let myThis = this;
  //   function startPainting(e) {
  //     if(myThis.activatedTool!=="line"){
  //       this.removeEventListener("mousedown", startPainting)
  //     } else {
  //       let startX = e.offsetX;
  //       let startY= e.offsetY;
  //       this.addEventListener("mouseup",(e)=>{
  //         let path = new Path2D()
  //         path.moveTo(myThis.xy(startX),myThis.xy(startY));
  //         path.lineTo(myThis.xy(e.offsetX),myThis.xy(e.offsetY));
  //         myThis.pushPath(path)
  //       },{once:true})
  //     }
  //   }
  //   this.canvas.addEventListener("mousedown", startPainting);
  // };

  activateLine(){
    let myThis = this;
    function startPainting(e) {
      if(myThis.activatedTool!=="line"){
        this.removeEventListener("mousedown", startPainting)
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        let temp = new Image()
        temp.src = myThis.canvas.toDataURL()
        this.addEventListener("mousemove", function move(e){
            let path = new Path2D()
            myThis.context.setLineDash([5, 15]);
            myThis.context.clearRect(0,0,myThis.canvas.width,myThis.canvas.height)

            path.moveTo(myThis.xy(startX),myThis.xy(startY));
            path.lineTo(myThis.xy(e.offsetX),myThis.xy(e.offsetY));
            myThis.context.stroke(path)
            myThis.context.drawImage(temp,0,0)
            this.addEventListener("mouseup",(e)=>{
              this.removeEventListener("mousemove", move)
            },{once:true})

        })
          this.addEventListener("mouseup",(e)=>{
          let path2 = new Path2D()
          path2.moveTo(myThis.xy(startX),myThis.xy(startY));
          path2.lineTo(myThis.xy(e.offsetX),myThis.xy(e.offsetY));
          myThis.pushPath(path2)
        },{once:true})

      }
    }
    this.canvas.addEventListener("mousedown", startPainting);
  };

  //펜 툴
  activatePen(){
    let myThis = this;
    let painting = false;
    let path = new Path2D()
    function startPainting() {
      if(myThis.activatedTool!=="pen"){
        this.removeEventListener("mousemove", onMouseMove);
        this.removeEventListener("mousedown", startPainting);
        this.removeEventListener("mouseup", stopPainting);
        this.removeEventListener("mouseleave", stopPainting);
      } else {
        painting=true;
      }
    }
    function stopPainting(e) {
      painting=false;
      myThis.pushPath(path)
    }
    function onMouseMove(e){
      const x = e.offsetX;
      const y = e.offsetY;
      if(!painting) {
        path.moveTo(myThis.xy(x),myThis.xy(y));
      }
      else {
        path.lineTo(myThis.xy(e.offsetX),myThis.xy(e.offsetY));
        myThis.context.stroke(path)
      }}
    this.canvas.addEventListener("mousemove", onMouseMove);
    this.canvas.addEventListener("mousedown", startPainting);
    this.canvas.addEventListener("mouseup", stopPainting);
    this.canvas.addEventListener("mouseleave", stopPainting);
  };



  //사이드바 각 버튼에 기능부여
  toolActivate(){
    //스템프툴 활성화//
    $("stampBtn").addEventListener("click", ()=>{
      if(this.activatedTool==="stamp") {
        return;
      }
      else {
        this.activatedTool="stamp";
        this.activateStamp();
      }
    });
    //펜툴 활성화//
    $("penBtn").addEventListener("click", ()=>{
      if(this.activatedTool==="pen") {
        return;
      } else {
        this.activatedTool="pen";
        this.activatePen();
      }
    });
    //직선툴 활성화//
    $("lineBtn").addEventListener("click", ()=>{
      if(this.activatedTool==="line") {
        return;
      } else {
        this.activatedTool = "line";
        this.activateLine();
      }
    });
    //선택툴 활성화//
    $("selectorBtn").addEventListener("click", ()=>{
      if(this.activatedTool==="selector") {
        return
      } else {
      this.activatedTool="selector";
      this.activateSelector();
      }
    });

    ///////배열 테스트용/////
    $("removeBtn").addEventListener("click", ()=>{
      this.activatedTool="default";
      this.context.clearRect(0,0,this.canvas.width,this.canvas.height);
    });
    ///////배열 테스트용/////
    $("loadBtn").addEventListener("click", ()=>{
      this.layerArray.forEach((c)=>{

        if(c instanceof Path2D){
          this.context.stroke(c)
        } else {
          // this.context.drawImage(c.img,c.x,c.y)
        }
      })
    });


    ///////배율조정 테스트용/////
    $("plusBtn").addEventListener("click",()=>{
      this.context.clearRect(0,0,this.canvas.width,this.canvas.height);
      this.functionZoom*=1.2;
      this.context.scale(1.2,1.2);

      this.layerArray.forEach((c)=>{
        if(c instanceof Path2D){
          this.context.stroke(c)
        } else if (c instanceof Image)  {
          this.context.drawImage(c,0,0)
        }
      })
    });
    $("minusBtn").addEventListener("click",()=>{
      if (this.functionZoom>1.05){
        this.context.clearRect(0,0,this.canvas.width,this.canvas.height);
        this.context.scale(1/1.2,1/1.2)
        this.functionZoom/=1.2;

        console.log(this.functionZoom)
        this.layerArray.forEach((c)=>{
          if(c instanceof Path2D){
            this.context.stroke(c)
          } else if (c instanceof Image)  {
            this.context.drawImage(c,0,0)
          }
        })
      } else console.log("축소할 수 없음")

    })
  };

};//클래스 닫기



////////////////// 페이지 기능 관련  //////////////////

//새 캔버스 생성(querySelectorAll은 동적으로 생성되는 요소에 반응하지 않기 때문에 getElements로 찾아야한다)
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


//새 일정 행 추가용.. 미완성..
const rowInputBtn = document.querySelector(".rowInputBtn")
const scheduleBox = document.querySelector(".accordion")
rowInputBtn.addEventListener("click",(e)=>{
  let parentDiv = document.createElement("div");
  parentDiv.classList.add("accordion-item")
  scheduleBox.append(parentDiv)
  let headerH2 = document.createElement("h2")
  headerH2.classList.add("accordion-header")
  parentDiv.append(headerH2)
  let headerBtn = document.createElement("btn")
  headerBtn.classList.add("accordion-button");
  headerBtn.classList.add("collapsed");
  headerH2.append(headerBtn)
});

//////////////////////////////////////////////////////////////////////////////////////////////

// 여기서부터는 테스트용..
/*
this.context.fillRect(0,0,50,50)
this.context.fillRect(100,0,50,50)
this.context.fillRect(0,100,50,50)
this.context.fillRect(50,50,50,50)
this.context.fillRect(100,100,50,50)
*/

// createRect = (e)=>{
//   const x = e.offsetX
//   const y = e.offsetY
//   this.subContext.fillRect(x - 15, y - 15, 30, 30);
//   this.draw()
// }


//포인터 변경기능
// canvas.style.cursor = 'pointer';


// //파레트기능
// const buttons = [
//   "red",
//   "orange",
//   "yellow",
//   "green",
//   "blue",
//   "navy",
//   "purple",
//   "black",
//   "white",
//   "clear",
//
// ];
// let lineColor = "black";
//
// buttons.forEach((content) => {
//   let button = document.querySelector(`.${content}`);
//
//   button.style.cursor = 'pointer';
//
//   if (content === "clear" ) {
//
//     button.style.background = "rgba(100,100,100,0.2)";
//   } else {
//     button.style.background = content;
//   }
//   button.style.color = "white";
//   button.style.display = "block";
//   button.style.lineHeight = "30px";
//   button.style.textAlign = "center";
//   button.style.width = "30px";
//   button.style.height = "30px";
//   button.style.borderRadius = "10%";
//   button.style.boxShadow = "1px 2px 2px gray";
//   button.style.marginLeft = "45px";
//
//   button.onclick = () => {
//     context.strokeStyle = content;
//     lineColor = content;
//   };
// });
//
// //전체 삭제버튼
// document.querySelector(".clear").onclick = () => {
//   context.clearRect(0, 0, width, height);
// };


//텍스트 입력기능 부분
// var hasInput = false;
//
// canvas.onclick = function(e) {
//   if (hasInput) return;
//   addInput(e.clientX, e.clientY);
// }
//
//
// let font = '14px sans-serif';
// function addInput(x, y) {
//   var input = document.createElement('input');
//
//   input.type = 'text';
//   input.style.position = 'fixed';
//   input.style.left = (x - 4) + 'px';
//   input.style.top = (y - 4) + 'px';
//
//   input.onkeydown = handleEnter;
//
//   document.body.appendChild(input);
//
//   input.focus();
//
//   hasInput = true;
// }
//
// function handleEnter(e) {
//   let keyCode = e.keyCode;
//   if (keyCode === 13) {
//     drawText(this.value, 100, 100);
//     document.body.removeChild(this);
//     hasInput = false;
//   }
// }
//
// function drawText(txt, x, y) {
//   // context.textBaseline = 'top';
//   context.textAlign = 'left';
//   context.font = font;
//   context.fillText(txt, x + 30, y + 30);
// }


}
