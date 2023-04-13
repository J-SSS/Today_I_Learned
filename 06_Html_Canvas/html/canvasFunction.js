

const box = document.querySelector(".accordion-header").clientWidth-40
let canvasCnt = 0;

////////////////// 캔버스 생성용 클래스 //////////////////
class CanvasCreate {
  index;
  canvas; context;
  layerArray = []; //레이어 구현용 배열
  activatedTool; //활성화 툴 체크용
  pageZoom = box/1200; //페이지 사이즈 변화에 따른 배율 조졍용(아직)
  functionZoom = 1.0; //줌기능 배율 조졍용
  currentCanvas = new Image(); //배율조정시 리로드용

  constructor() {
    // 캔버스 본체
    this.canvas = document.createElement("canvas");
    this.context = this.canvas.getContext("2d")

    // 캔버스 초기화
    this.init()
  }
  init(){
    // 스타일 관련 초기 설정
    this.index = ++canvasCnt;
    this.canvas.width = 1200;
    this.canvas.height = 500;
    this.context.strokeStyle = "black";
    this.context.lineWidth = 0.5;
    this.context.scale(1.0,1.0)
    this.context.translate(0,0)
    this.context.setLineDash([])
    this.context.save();

    // 버튼활성화 및 배경디자인 생성
    this.pageLiner(this.canvas.width,this.canvas.height)
    this.toolActivation()
    this.colorBtn()

  }

  // 배율 조정 및 윈도우 사이즈 조절시 수정된 좌표값 생성 함수
  xy(xy){
    return xy/this.pageZoom/this.functionZoom
    // return xy/this.pageZoom
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
    this.currentCanvas.src = this.canvas.toDataURL();
    this.canvasRestore();

  }


  //스템프 툴
  stampTool() {
    let canvasObj = this
    function drawHandler(e){
      if(canvasObj.activatedTool!=="stamp"){
        canvasObj.canvas.removeEventListener("mousedown",drawHandler)
      } else {
        let imgElem = new Image();
        let imgSrc = "../img/star.png";
        imgElem.src = src;
        canvasObj.context.drawImage(imgElem, canvasObj.xy(e.offsetX-25), canvasObj.xy(e.offsetY-25))
        canvasObj.layerPush("img",[e.offsetX,e.offsetY],undefined,undefined, imgSrc)
      }
    }
    this.canvas.addEventListener("mousedown",drawHandler)
  };


  //셀렉터 툴
  selectorTool(){
    let canvasObj = this
    let selectedLayer = 0;
      function drawHandler(e){
        let x = e.offsetX;
        let y = e.offsetY;

        for(let i = canvasObj.layerArray.length-1 ; i>=0 ; i--){
          if((x>=canvasObj.layerArray[i].range[0][0]
              && x<=canvasObj.layerArray[i].range[1][0])
                &&
            (y>=canvasObj.layerArray[i].range[0][1]
              && y<=canvasObj.layerArray[i].range[1][1]))
          {
                canvasObj.context.beginPath();
                canvasObj.context.strokeStyle="black";
                canvasObj.context.lineWidth=1;
                canvasObj.context.setLineDash([10, 10]);
                canvasObj.context.strokeRect(
                  canvasObj.xy(canvasObj.layerArray[i].range[0][0])-30,
                  canvasObj.xy(canvasObj.layerArray[i].range[0][1])-30,
                  canvasObj.xy(canvasObj.layerArray[i].range[1][0]-canvasObj.layerArray[i].range[0][0])+60,
                  canvasObj.xy(canvasObj.layerArray[i].range[1][1]-canvasObj.layerArray[i].range[0][1])+60);
                canvasObj.context.stroke();
                canvasObj.canvasRestore();
          }
          selectedLayer = i;
        }
      }
      this.canvas.addEventListener("mousedown",drawHandler)
  };
  textTool(){};
  palateTool(){};
  postTool(){};

  //직선 툴
  lineTool(){
    let canvasObj = this;
    let moveHandler;
    function drawHandler(e) {
      if(canvasObj.activatedTool!=="line"){
        this.removeEventListener("mousedown", drawHandler)
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
            canvasObj.context.strokeStyle="grey";
            canvasObj.context.lineWidth=2;
            canvasObj.context.setLineDash([10, 10]);
            canvasObj.context.clearRect(0,0,canvasObj.canvas.width,canvasObj.canvas.height);
            canvasObj.context.drawImage(canvasObj.currentCanvas,0,0);
            canvasObj.context.beginPath();
            canvasObj.context.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
            canvasObj.context.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
            canvasObj.context.stroke();
        })
          this.addEventListener("mouseup",(e)=>{
            this.removeEventListener("mousemove", moveHandler);
            canvasObj.canvasRestore();
            canvasObj.context.clearRect(0,0,canvasObj.canvas.width,canvasObj.canvas.height);
            canvasObj.context.drawImage(canvasObj.currentCanvas,0,0);
            canvasObj.context.beginPath();
            canvasObj.context.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
            canvasObj.context.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
            canvasObj.context.stroke();
            canvasObj.layerPush("line",[startX,startY],[e.offsetX,e.offsetY])
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);

  };
  //사각형 툴
  rectTool(){
    let canvasObj = this;
    let moveHandler;
    function drawHandler(e) {
      if(canvasObj.activatedTool!=="rect"){
        this.removeEventListener("mousedown", drawHandler
        )
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
          canvasObj.context.beginPath();
          canvasObj.context.lineWidth=2;
          canvasObj.context.strokeStyle="grey";
          canvasObj.context.setLineDash([10, 10]);
          canvasObj.context.clearRect(0,0,canvasObj.canvas.width,canvasObj.canvas.height);
          canvasObj.context.drawImage(canvasObj.currentCanvas,0,0);
          canvasObj.context.strokeRect(
                                        canvasObj.xy(startX),
                                        canvasObj.xy(startY),
                                      canvasObj.xy(e.offsetX)-canvasObj.xy(startX),
                                      canvasObj.xy(e.offsetY)-canvasObj.xy(startY));
          canvasObj.context.stroke()
        })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          canvasObj.context.clearRect(0,0,canvasObj.canvas.width,canvasObj.canvas.height);
          canvasObj.context.drawImage(canvasObj.currentCanvas,0,0);
          canvasObj.context.beginPath();
          canvasObj.context.fillRect(
                                    canvasObj.xy(startX),
                                    canvasObj.xy(startY),
                                  canvasObj.xy(e.offsetX)-canvasObj.xy(startX),
                                  canvasObj.xy(e.offsetY)-canvasObj.xy(startY));
          canvasObj.context.stroke();
          canvasObj.layerPush("rect",[canvasObj.xy(startX),canvasObj.xy(startY)],[canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY)]);
          // canvasObj.layerPush("rect",[startX,startY],[e.offsetX,e.offsetY]);
          console.log([startX,startY],[e.offsetX,e.offsetY])
          console.log([canvasObj.xy(startX),canvasObj.xy(startY)],[canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY)])

        },{once:true});
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);
  };




  //펜 툴
  penTool(){
    let canvasObj = this;
    let moveHandler;
    function drawHandler(e) {
      if(canvasObj.activatedTool!=="pen"){
        this.removeEventListener("mousedown", drawHandler);
      } else {
          let startX = e.offsetX;
          let startY = e.offsetY;
          let pathArray = [];
          canvasObj.context.beginPath()
          canvasObj.context.moveTo(canvasObj.xy(startX),canvasObj.xy(startY));
          this.addEventListener("mousemove", moveHandler = function (e){
              pathArray.push([e.offsetX,e.offsetY])
              canvasObj.context.lineTo(canvasObj.xy(e.offsetX),canvasObj.xy(e.offsetY));
              canvasObj.context.stroke()
          })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          canvasObj.layerPush("pen",[startX,startY],undefined, pathArray)
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);

  };
  canvasRestore(){
    let canvasObj = this;
    canvasObj.context.restore();
    canvasObj.context.save();
  }

  // 경로값 저장용
  layerPush(
             type,
             moveTo = undefined,
             lineTo = undefined,
             path = undefined,
             src = undefined)
  {
    let canvasObj = this;

    let tempObj = {
      type : type,
      strokeStyle : canvasObj.context.strokeStyle,
      lineWidth : canvasObj.context.lineWidth,
      matrix : canvasObj.context.getTransform(),
      // matrix : this.functionZoom,
      moveTo : moveTo,
      lineTo : lineTo,
      path : path,
      src : src,
      range : [],
      }
    function findRange(arr){
      let maxX=0, maxY=0, minX=1200, minY=500;
      arr.forEach((xy)=>{
        if(xy[0]>maxX) maxX=xy[0];
        if(xy[1]>maxY) maxY=xy[1];
        if(xy[0]<minX) minX=xy[0];
        if(xy[1]<minY) minY=xy[1];
      })
      return [[minX,minY],[maxX,maxY]]
    }
      // 범위 저장용
    if(type==="line" || type==="rect") {
      tempObj.range = findRange([moveTo,lineTo]);
    } else if(type==="pen") {
      tempObj.range = findRange(path);
    }


    canvasObj.currentCanvas.src = canvasObj.canvas.toDataURL()
    return canvasObj.layerArray.push(tempObj);

  }
  // 저장돤 경로를 불러옴
  layerLoad(){
    let canvasObj = this;
    canvasObj.layerArray.forEach((c, i)=>{
      canvasObj.context.strokeStyle=c.strokeStyle;
      canvasObj.context.lineWidth=c.lineWidth;
      canvasObj.context.transform(1,0,0,1,0,0);
      // canvasObj.context.scale(1/this.functionZoom, 1/this.functionZoom);
      // canvasObj.context.scale(c.matrix,c.matrix);
      // canvasObj.context.transform(c.matrix[0],c.matrix[1],c.matrix[2],c.matrix[3],c.matrix[4],c.matrix[5]);
      canvasObj.context.beginPath();
      canvasObj.context.moveTo(canvasObj.xy(c.moveTo[0]),canvasObj.xy(c.moveTo[1]))
      if(c.type==="line") {
        canvasObj.context.lineTo(canvasObj.xy(c.lineTo[0]),canvasObj.xy(c.lineTo[1]))
        canvasObj.context.stroke();
      } else if(c.type==="pen"){
        c.path.forEach((p)=>{
          canvasObj.context.lineTo(canvasObj.xy(p[0]),canvasObj.xy(p[1]));
          canvasObj.context.stroke();
        })
      } else if(c.type==="rect"){
          canvasObj.context.fillRect(
          c.moveTo[0],
          c.moveTo[1],
            c.lineTo[0]-c.moveTo[0],
            c.lineTo[1]-c.moveTo[1]);
      }
      // canvasObj.canvasRestore();
    })

    console.log(JSON.stringify(this.layerArray));
    console.log(JSON.parse(JSON.stringify(this.layerArray)));

  }

  // layerLoad(){
  //   let canvasObj = this;
  //   canvasObj.layerArray.forEach((c, i)=>{
  //     canvasObj.context.strokeStyle=c.strokeStyle;
  //     canvasObj.context.lineWidth=c.lineWidth;
  //     // canvasObj.context.scale(c.matrix,c.matrix);
  //     // canvasObj.context.transform(c.matrix[0],c.matrix[1],c.matrix[2],c.matrix[3],c.matrix[4],c.matrix[5]);
  //     canvasObj.context.beginPath();
  //     canvasObj.context.moveTo(canvasObj.xy(c.moveTo[0]),canvasObj.xy(c.moveTo[1]))
  //     if(c.type==="line") {
  //       canvasObj.context.lineTo(canvasObj.xy(c.lineTo[0]),canvasObj.xy(c.lineTo[1]))
  //       canvasObj.context.stroke();
  //     } else if(c.type==="pen"){
  //       c.path.forEach((p)=>{
  //         canvasObj.context.lineTo(canvasObj.xy(p[0]),canvasObj.xy(p[1]));
  //         canvasObj.context.stroke();
  //       })
  //     } else if(c.type==="rect"){
  //       canvasObj.context.fillRect(
  //         canvasObj.xy(c.moveTo[0]),
  //         canvasObj.xy(c.moveTo[1]),
  //         canvasObj.xy(c.lineTo[0])-canvasObj.xy(c.moveTo[0]),
  //         canvasObj.xy(c.lineTo[1])-canvasObj.xy(c.moveTo[1]));
  //     }
  //     canvasObj.canvasRestore();
  //   })
  //
  //   console.log(JSON.stringify(this.layerArray));
  //   console.log(JSON.parse(JSON.stringify(this.layerArray)));
  //
  // }

  //사이드바 각 버튼에 기능부여
  toolActivation(){
    const tools = ["stamp","pen","line","selector","rect","text","palate","post"];

    tools.forEach((tool)=>{
      document.getElementById(tool+"Btn").addEventListener("click",()=>{
        if(this.activatedTool===tool) return;
        else {
          this.activatedTool=tool;
          this[tool+"Tool"]();
        }
      })
    });

    // 테스트중인 기능들
    ///////배열 테스트용/////
    document.getElementById("removeBtn").addEventListener("click", ()=>{
      this.activatedTool="default";
      this.context.clearRect(0,0,this.canvas.width,this.canvas.height);
    });
    ///////배열 테스트용/////
    document.getElementById("loadBtn").addEventListener("click", ()=>{
      this.layerLoad()
    });


    ///////배율조정 테스트용/////
    document.getElementById("plusBtn").addEventListener("click",()=>{
      if (this.functionZoom<5.00) {
        this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.functionZoom *= 1.2;
        this.context.scale(1.2, 1.2);
        this.context.drawImage(this.currentCanvas,0,0);
        // this.context.scale(1/1.2, 1/1.2);
        // this.currentCanvas.src = this.canvas.toDataURL()
      }
    });
    document.getElementById("minusBtn").addEventListener("click",()=>{
      if (this.functionZoom>1.05){
        this.context.clearRect(0,0,this.canvas.width,this.canvas.height);
        this.functionZoom/=1.2;
        this.context.scale(1/1.2, 1/1.2);
        this.context.drawImage(this.currentCanvas,0,0)
      }
    })
  };

  colorBtn(){
    buttons.forEach((content) => {
      let button = document.querySelector(`.${content}`);
      button.onclick = () => {
        this.context.strokeStyle = content;
        this.context.fillStyle = content;
        this.context.save();
      };
    });
  }



};//클래스 닫기

document.getElementById("settingBtn").addEventListener("click",()=>{
  let CCC = document.getElementById("123");
  console.log(CCC)
  let CCCO = CCC.getContext("2d");
  CCCO.strokeRect(0,0,50,50);
})


//////////////////////////////////////////////////////////////////////////////////////////////


// 사분면 파악용(필요없게 됐는데 일단 냅둠)
// if(moveTo[0]<=lineTo[0] && moveTo[1]>=lineTo[1]) tempObj.quad = 1;
// else if(moveTo[0]<=lineTo[0] && moveTo[1]<=lineTo[1]) tempObj.quad = 2;
// else if(moveTo[0]>=lineTo[0] && moveTo[1]<=lineTo[1]) tempObj.quad = 3;
// else if(moveTo[0]>=lineTo[0] && moveTo[1]>=lineTo[1]) tempObj.quad = 4;



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


