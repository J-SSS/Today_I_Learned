
function $(x){return document.getElementById(x)}
const box = document.querySelector(".accordion-header").clientWidth-40
let canvasCnt = 0;

////////////////// 캔버스 생성용 클래스 //////////////////
class CanvasCreate {
  index;
  canvas; context; subCanvas; subContext;
  layerArray = []; //레이어 구현용 배열
  jsonArray = []; //레이어 구현용 배열
  activatedTool; //활성화 툴 체크용
  pageZoom = box/1200; //페이지 사이즈 변화에 따른 배율 조졍용(아직)
  functionZoom = 1.0; //줌기능 배율 조졍용

  constructor() {
    // 캔버스 본체
    this.canvas = document.createElement("canvas");
    this.context = this.canvas.getContext("2d")
    // 레이어 구현용 보조캔버스
    this.subCanvas = document.createElement("canvas");
    this.subContext = this.subCanvas.getContext("2d");
    //캔버스 초기화
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
    this.context.setLineDash([])
    this.subContext.strokeStyle = "black";
    this.subContext.lineWidth = 0.5;
    this.subContext.scale(1.0,1.0)
    this.subContext.translate(0,0)
    this.subContext.setLineDash([])
    this.context.save();
    this.subContext.save();

    this.pageLiner(this.canvas.width,this.canvas.height)
    this.toolActivation()

    this.colorBtn()
    // test
    // this.canvas.addEventListener("click",()=>{
    //   console.log(this.index)
    //   console.log(this.layerArray)
    // })

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
    this.context.save()
    ///배열에 들어간 캔버스가 계속 변경되는 문제 해결 필요
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
    let canvasObj = this
    function drawHandler(e){
      if(canvasObj.activatedTool!=="stamp"){
        canvasObj.canvas.removeEventListener("mousedown",drawHandler)
      } else {
        canvasObj.subContext.clearRect(0,0,100,100)
        let imgElem = new Image();
        imgElem.src = "../img/star.png"
        canvasObj.pushImage(imgElem, canvasObj.xy(e.offsetX-25),canvasObj.xy(e.offsetY-25))
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
  activateLine(){
    let canvasObj = this;
    function startPainting(e) {
      if(canvasObj.activatedTool!=="line"){
        this.removeEventListener("mousedown", startPainting)
      } else {
        canvasObj.subActivation()
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", function move(e){
            let path = new Path2D()
            canvasObj.subContext.strokeStyle="grey";
            canvasObj.subContext.lineWidth=2;
            canvasObj.subContext.setLineDash([10, 10]);
            canvasObj.subContext.clearRect(0,0,canvasObj.canvas.width,canvasObj.canvas.height)

            path.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
            path.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
            canvasObj.subContext.stroke(path)

            this.addEventListener("mouseup",(e)=>{
              this.removeEventListener("mousemove", move)
              canvasObj.subDeactivation()
            },{once:true})
        })
          this.addEventListener("mouseup",(e)=>{
            canvasObj.context.restore();
            canvasObj.context.save();
            // canvasObj.context.setLineDash([]);
          let path2 = new Path2D()
          path2.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
          path2.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
          canvasObj.pushPath(path2)
          canvasObj.jsonParser(undefined ,undefined ,[startX,startY],[e.offsetX,e.offsetY])
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", startPainting);
  };


  //펜 툴
  activatePen(){
    let canvasObj = this;
    let painting = false;
    let pathArray = []
    let startX;
    let startY;
    let path = new Path2D()
    function startPainting(e) {
      if(canvasObj.activatedTool!=="pen"){
        this.removeEventListener("mousemove", onMouseMove);
        this.removeEventListener("mousedown", startPainting);
        this.removeEventListener("mouseup", stopPainting);
        this.removeEventListener("mouseleave", stopPainting);
      } else {
        painting=true;
        startX = e.offsetX;
        startY = e.offsetY;
      }
    }
    function stopPainting(e) {
      painting=false;
      canvasObj.pushPath(path)
    }
    function onMouseMove(e){
      if(!painting) {
        path.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
      }
      else {
        canvasObj.pathLogger(pathArray,e.offsetX,e.offsetY)
        path.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
        canvasObj.context.stroke(path)
      }}
        canvasObj.jsonParser(undefined ,undefined ,[startX,startY],undefined, pathArray)
    this.canvas.addEventListener("mousemove", onMouseMove);
    this.canvas.addEventListener("mousedown", startPainting);
    this.canvas.addEventListener("mouseup", stopPainting);
    this.canvas.addEventListener("mouseleave", stopPainting);
  };

  //보조캔버스 on
  subActivation(){
    this.canvas.style.filter = "blur(1px)";
    this.subCanvas.classList.remove("subCanvasX");
    this.subCanvas.classList.add("subCanvasO");
  }
  //보조캔버스 off
  subDeactivation(){
    this.canvas.style.filter = "blur(0px)";
    this.subCanvas.classList.remove("subCanvasO");
    this.subCanvas.classList.add("subCanvasX");
    this.subContext.restore();
    this.subContext.clearRect(0,0,this.subCanvas.width,this.subCanvas.height);
  }
  // 펜 기능에서 좌표값을 배열에 담음 (펜 메서드 안에 집어넣어도딜듯)
  pathLogger(arr,x,y){
    let logger = [x,y];
    return arr.push(logger);
  }
  // 경로값 저장용
  jsonParser(strokeStyle='black',
             lineWidth=1,
             moveTo= undefined,
             lineTo= undefined,
             path= undefined){
    let tempObj = {
      strokeStyle : strokeStyle,
      lineWidth : lineWidth,
      moveTo : moveTo,
      lineTo : lineTo,
      path : path
    }
    return this.jsonArray.push(tempObj);
  }
  // 저장돤 경로를 불러옴
  pathLoader(){
    this.jsonArray.forEach((c)=>{
      this.context.beginPath()
      this.context.moveTo(c.moveTo[0],c.moveTo[1])
      if(c.lineTo===undefined){
        c.path.forEach((p)=>{
          this.context.lineTo(p[0],p[1])
          this.context.stroke();
        })
      } else {
        this.context.lineTo(c.lineTo[0],c.lineTo[1])
        this.context.stroke();
      }
    })
    console.log(this.jsonArray)
    console.log(JSON.stringify(this.jsonArray));
    console.log(JSON.parse(JSON.stringify(this.jsonArray)));
  }

  //사이드바 각 버튼에 기능부여
  toolActivation(){
    $("stampBtn").addEventListener("click", ()=>{
      if(this.activatedTool==="stamp") {
        return;
      }
      else {
        this.activatedTool="stamp";
        this['activateStamp']()
      }
    });
    $("penBtn").addEventListener("click", ()=>{
      if(this.activatedTool==="pen") {
        return;
      } else {
        this.activatedTool="pen";
        this.activatePen();
      }
    });
    $("lineBtn").addEventListener("click", ()=>{
      if(this.activatedTool==="line") {
        return;
      } else {
        this.activatedTool = "line";
        this.activateLine();
      }
    });
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
      this.pathLoader()
    },{once:true});


    ///////배율조정 테스트용/////
    $("plusBtn").addEventListener("click",()=>{
      if (this.functionZoom<5.00) {
        this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.functionZoom *= 1.2;
        this.context.scale(1.2, 1.2);
        this.pathLoader()
      }
    });
    $("minusBtn").addEventListener("click",()=>{
      if (this.functionZoom>1.05){
        this.context.clearRect(0,0,this.canvas.width,this.canvas.height);
        this.context.scale(1/1.2,1/1.2)
        this.functionZoom/=1.2;
        this.pathLoader()
      }
    })
  };

  colorBtn(){
    buttons.forEach((content) => {
      let button = document.querySelector(`.${content}`);
      button.onclick = () => {
        this.context.strokeStyle = content;
      };
    });
  }



};//클래스 닫기



//////////////////////////////////////////////////////////////////////////////////////////////

// 여기서부터는 테스트용..
/*
this.context.fillRect(0,0,50,50)
this.context.fillRect(100,0,50,50)
this.context.fillRect(0,100,50,50)
this.context.fillRect(50,50,50,50)
this.context.fillRect(100,100,50,50)
*/

//포인터 변경기능
// canvas.style.cursor = 'pointer';


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


