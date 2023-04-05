
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
    // 캔버스 초기화
    this.init()
  }
  init(){
    // 스타일 관련 초기 설정
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

    // 버튼활성화 및 배경디자인 생성
    this.pageLiner(this.canvas.width,this.canvas.height)
    this.toolActivation()
    this.colorBtn()

  }

  // 배율 조정 및 윈도우 사이즈 조절시 수정된 좌표값 생성 함수
  xy(xy){
    return xy/this.pageZoom/this.functionZoom
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
  stampTool() {
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
  selectorTool(){
    this.canvas.addEventListener("mousemove",(e)=>{
      let x = this.xy(e.offsetX)
      let y = this.xy(e.offsetY)
      this.jsonArray.forEach((c)=>{
      })
    })
  };
  textTool(){};
  palateTool(){ };
  postTool(){};

  //직선 툴
  lineTool(){

    let canvasObj = this;
    let moveHandler;
    function startFunction(e) {
      if(canvasObj.activatedTool!=="line"){
        this.removeEventListener("mousedown", startFunction
        )
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
            canvasObj.subContext.strokeStyle="grey";
            canvasObj.subContext.lineWidth=2;
            canvasObj.subContext.setLineDash([10, 10]);

            canvasObj.subContext.clearRect(0,0,canvasObj.canvas.width,canvasObj.canvas.height)
            canvasObj.subContext.beginPath()
            canvasObj.subContext.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
            canvasObj.subContext.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
            canvasObj.subContext.stroke()
        })
          this.addEventListener("mouseup",(e)=>{
            this.removeEventListener("mousemove", moveHandler)
            canvasObj.subCanvasClear()
            canvasObj.context.beginPath()
            canvasObj.context.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
            canvasObj.context.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
            canvasObj.context.stroke();
            console.log(canvasObj.context.strokeStyle)
            console.log(canvasObj.context.lineWidth)
            canvasObj.jsonParser([startX,startY],[e.offsetX,e.offsetY])
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", startFunction);

  };
  //사각형 툴
  rectTool(){

    let canvasObj = this;
    let moveHandler;
    function startFunction(e) {
      if(canvasObj.activatedTool!=="rect"){
        this.removeEventListener("mousedown", startFunction
        )
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
          canvasObj.subContext.lineWidth=2;
          canvasObj.subContext.strokeStyle="grey";
          canvasObj.subContext.setLineDash([10, 10]);

          canvasObj.subContext.clearRect(0,0,canvasObj.canvas.width,canvasObj.canvas.height)
          // canvasObj.subContext.beginPath()

          canvasObj.subContext.strokeRect(
                                        canvasObj.xy(startX),
                                        canvasObj.xy(startY),
                                      canvasObj.xy(e.offsetX)-canvasObj.xy(startX),
                                      canvasObj.xy(e.offsetY)-canvasObj.xy(startY));
          canvasObj.subContext.stroke()
        })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          canvasObj.subCanvasClear()
          canvasObj.context.fillRect(
                                    canvasObj.xy(startX),
                                    canvasObj.xy(startY),
                                  canvasObj.xy(e.offsetX)-canvasObj.xy(startX),
                                  canvasObj.xy(e.offsetY)-canvasObj.xy(startY));
          canvasObj.context.stroke();
          canvasObj.jsonParser([startX,startY],[e.offsetX,e.offsetY])
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", startFunction
    );

  };



  //펜 툴
  penTool(){

    let canvasObj = this;
    let moveHandler;
    function startFunction
    (e) {
      if(canvasObj.activatedTool!=="pen"){
        this.removeEventListener("mousedown", startFunction
        );
      } else {
          let startX = e.offsetX;
          let startY = e.offsetY;
          let pathArray = [];
          canvasObj.context.beginPath()
          canvasObj.context.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
          this.addEventListener("mousemove", moveHandler = function (e){
              canvasObj.pathLogger(pathArray,e.offsetX,e.offsetY)
              canvasObj.context.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
              canvasObj.context.stroke()
          })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          canvasObj.jsonParser([startX,startY],undefined, pathArray)
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", startFunction);

  };

  //보조캔버스 클리어
  subCanvasClear(){

    // this.subContext.restore();
    this.subContext.clearRect(0,0,this.subCanvas.width,this.subCanvas.height);

  }
  // 펜 기능에서 좌표값을 배열에 담음 (펜 메서드 안에 집어넣어도딜듯)
  pathLogger(arr,x,y){
    let logger = [x,y];
    return arr.push(logger);
  }
  // 경로값 저장용
  jsonParser(
             moveTo= undefined,
             lineTo= undefined,
             path= undefined){
    let tempObj = {
      strokeStyle : this.context.strokeStyle,
      lineWidth : this.context.lineWidth,
      matrix : this.context.getTransform(),
      moveTo : moveTo,
      lineTo : lineTo,
      path : path

    }
    console.log(tempObj.matrix);
    return this.jsonArray.push(tempObj);

  }
  // 저장돤 경로를 불러옴
  pathLoader(){

    this.jsonArray.forEach((c)=>{
      this.context.strokeStyle=c.strokeStyle;
      this.context.lineWidth=c.lineWidth;
      this.context.transform(c.matrix[0],c.matrix[1],c.matrix[2],c.matrix[3],c.matrix[4],c.matrix[5]);
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
      this.context.restore();
      this.context.save();

    })
    this.layerArray.forEach((c)=>{
      this.context.drawImage(c,0,0)
    })
    console.log(this.jsonArray)
    console.log(JSON.stringify(this.jsonArray));
    console.log(JSON.parse(JSON.stringify(this.jsonArray)));

  }

  //사이드바 각 버튼에 기능부여
  toolActivation(){

    const tools = ["stamp","pen","line","selector","rect","text","palate","post"];

    tools.forEach((tool)=>{
      $(tool+"Btn").addEventListener("click",()=>{
        if(this.activatedTool===tool) return;
        else {
          this.activatedTool=tool;
          this[tool+"Tool"]();
        }
      })
    });

    // 테스트중인 기능들
    ///////배열 테스트용/////
    $("removeBtn").addEventListener("click", ()=>{
      this.activatedTool="default";
      this.context.clearRect(0,0,this.canvas.width,this.canvas.height);
    });
    ///////배열 테스트용/////
    $("loadBtn").addEventListener("click", ()=>{
      this.pathLoader()
    });


    ///////배율조정 테스트용/////
    $("plusBtn").addEventListener("click",()=>{
      if (this.functionZoom<5.00) {
        // this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.functionZoom *= 1.2;
        this.context.scale(1.2, 1.2);
        this.subContext.scale(1.2, 1.2);
        this.pathLoader()
      }
    });
    $("minusBtn").addEventListener("click",()=>{
      if (this.functionZoom>1.05){
        this.context.clearRect(0,0,this.canvas.width,this.canvas.height);
        this.context.scale(1/1.2,1/1.2)
        this.subContext.scale(1/1.2,1/1.2)
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
        this.context.fillStyle = content;
      };
    });
  }



};//클래스 닫기



//////////////////////////////////////////////////////////////////////////////////////////////

// 여기서부터는 테스트용..

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


