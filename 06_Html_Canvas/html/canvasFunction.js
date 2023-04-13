

const box = document.querySelector(".accordion-header").clientWidth-40
let canvasCnt = 0;

////////////////// 캔버스 생성용 클래스 //////////////////
class CanvasCreate {
  index;
  canvas; ctx;
  layerArr = []; //레이어 구현용 배열
  activatedTool; //활성화 툴 체크용
  pageZoom = box/1200; //페이지 사이즈 변화에 따른 배율 조졍용(아직)
  fZoom = 1.0; // 줌기능 배율 조졍용
  currentCanvas = new Image(); //배율조정시 리로드용
  defaultBack = new Image();

  constructor() {
    // 캔버스 본체
    this.canvas = document.createElement("canvas");
    this.ctx = this.canvas.getContext("2d")

    // 캔버스 초기화
    this.init()
  }
  init(){
    // 스타일 관련 초기 설정
    this.index = ++canvasCnt;
    this.canvas.width = 1200;
    this.canvas.height = 500;
    this.ctx.strokeStyle = "black";
    this.ctx.lineWidth = 0.5;
    this.ctx.scale(1.0,1.0)
    this.ctx.translate(0,0)
    this.ctx.setLineDash([0,0])
    this.ctx.save();

    // 버튼활성화 및 배경디자인 생성
    this.pageLiner(this.canvas.width,this.canvas.height)
    this.toolActivation()
    this.colorBtn()

  }

  // 배율 조정 및 윈도우 사이즈 조절시 수정된 좌표값 생성 함수
  xy(xy){
    return xy/this.pageZoom
  }
  xyAll(xy){
    return xy/this.pageZoom/this.fZoom
  }



  // 캔버스 기본 배경
  pageLiner(width,height){
    let x = 0;
    let y = 0;
    while (x<=height){
      this.ctx.beginPath();
      if(x===0 || height-x===0){
        this.ctx.strokeStyle="black"
        this.ctx.lineWidth=1.0;
      } else if(x%50===0){
          this.ctx.strokeStyle="gray"
          this.ctx.lineWidth=0.2;
      } else{
        this.ctx.strokeStyle="gray"
        this.ctx.lineWidth=0.1;
      }
      this.ctx.moveTo(0, x);
      this.ctx.lineTo(width, x);
      this.ctx.stroke();
      x+=10;
    }
    while (y<=width){
      this.ctx.beginPath();
      if(y===0 || y===width){
        this.ctx.strokeStyle="black"
        this.ctx.lineWidth=1.0;
      } else if(y%50===0){
        if(y/50===12){
          this.ctx.strokeStyle="red"
          this.ctx.lineWidth=0.3;
        } else {
          this.ctx.strokeStyle="gray"
          this.ctx.lineWidth=0.2;
        }
      } else{
        this.ctx.strokeStyle="gray"
        this.ctx.lineWidth=0.1;
      }
      this.ctx.moveTo(y, 0);
      this.ctx.lineTo(y, height);
      this.ctx.stroke();
      y+=10;
    }
    this.currentCanvas.src = this.canvas.toDataURL();
    this.defaultBack.src = this.canvas.toDataURL();
    this.canvasRestore();

  }


  //스템프 툴
  stampTool() {
    let co = this
    function drawHandler(e){
      if(co.activatedTool!=="stamp"){
        co.canvas.removeEventListener("mousedown",drawHandler)
      } else {
        let imgElem = new Image();
        let imgSrc = "../img/star.png";
        imgElem.src = imgSrc;
        co.ctx.drawImage(imgElem, co.xy(e.offsetX-25), co.xy(e.offsetY-25))
        co.layerPush("img",[e.offsetX,e.offsetY],undefined,undefined, imgSrc)
      }
    }
    this.canvas.addEventListener("mousedown",drawHandler)
  };


  //셀렉터 툴
  selectorTool(){
    let co = this
    let selectedLayer = 0;
      function drawHandler(e){
        let x = e.offsetX;
        let y = e.offsetY;

        //이 부분은 코드 정리 다시해야됨
        for(let i = co.layerArr.length-1 ; i>=0 ; i--){
          let minX = co.layerArr[i].range[0][0]*co.fZoom/co.layerArr[i].scale;
          let minY = co.layerArr[i].range[0][1]*co.fZoom/co.layerArr[i].scale;
          let maxX = co.layerArr[i].range[1][0]*co.fZoom/co.layerArr[i].scale;
          let maxY = co.layerArr[i].range[1][1]*co.fZoom/co.layerArr[i].scale;
          if((x>=co.layerArr[i].range[0][0]*co.fZoom/co.layerArr[i].scale && x<=co.layerArr[i].range[1][0]*co.fZoom/co.layerArr[i].scale)
              &&
            (y>=co.layerArr[i].range[0][1]*co.fZoom/co.layerArr[i].scale && y<=co.layerArr[i].range[1][1]*co.fZoom/co.layerArr[i].scale))
          {
                co.ctx.beginPath();
                co.ctx.strokeStyle="black";
                co.ctx.lineWidth=1;
                co.ctx.setLineDash([5, 5]);
                co.ctx.strokeRect(
                  co.xy(co.layerArr[i].range[0][0])*co.fZoom/co.layerArr[i].scale-20,
                  co.xy(co.layerArr[i].range[0][1])*co.fZoom/co.layerArr[i].scale-20,
                  co.xy(co.layerArr[i].range[1][0]-co.layerArr[i].range[0][0])*co.fZoom/co.layerArr[i].scale+40,
                  co.xy(co.layerArr[i].range[1][1]-co.layerArr[i].range[0][1])*co.fZoom/co.layerArr[i].scale+40);
                co.ctx.fillStyle="black";
                co.ctx.fillRect(co.xy(minX)-25, co.xy(minY)-25, 10,10);
                co.ctx.fillRect(co.xy(minX)-25, co.xy(maxY)+15, 10,10);
                co.ctx.fillRect(co.xy(maxX)+15, co.xy(minY)-25, 10,10);
                co.ctx.fillRect(co.xy(maxX)+15, co.xy(maxY)+15, 10,10);
                co.ctx.fillStyle="white";
                co.ctx.fillRect(co.xy(minX)-23, co.xy(minY)-23, 6,6);
                co.ctx.fillRect(co.xy(minX)-23, co.xy(maxY)+17, 6,6);
                co.ctx.fillRect(co.xy(maxX)+17, co.xy(minY)-23, 6,6);
                co.ctx.fillRect(co.xy(maxX)+17, co.xy(maxY)+17, 6,6);
                co.ctx.setLineDash([5, 5]);
                co.ctx.moveTo(co.xy(maxX)+20,co.xy((maxY+minY)/2));
                co.ctx.lineTo(co.xy(maxX)+70,co.xy((maxY+minY)/2));
                co.ctx.stroke();
                co.ctx.beginPath();
                co.ctx.fillStyle="black";
                co.ctx.setLineDash([0, 0]);
                co.ctx.arc(co.xy(maxX)+70, co.xy((maxY+minY)/2), 6, 0, 2*Math.PI);
                co.ctx.stroke();
                co.ctx.fill();



                co.canvasRestore();
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
    let co = this;
    let moveHandler;
    function drawHandler(e) {
      if(co.activatedTool!=="line"){
        this.removeEventListener("mousedown", drawHandler)
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
            co.ctx.strokeStyle="grey";
            co.ctx.lineWidth=2;
            co.ctx.setLineDash([10, 10]);
            co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
            co.ctx.drawImage(co.currentCanvas,0,0);
            co.ctx.beginPath();
            co.ctx.moveTo(co.xy(startX),co.xy(startY));
            co.ctx.lineTo(co.xy(e.offsetX),co.xy(e.offsetY));
            co.ctx.stroke();
        })
          this.addEventListener("mouseup",(e)=>{
            this.removeEventListener("mousemove", moveHandler);
            co.canvasRestore();
            co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
            co.ctx.drawImage(co.currentCanvas,0,0);
            co.ctx.beginPath();
            co.ctx.moveTo(co.xy(startX),co.xy(startY));
            co.ctx.lineTo(co.xy(e.offsetX),co.xy(e.offsetY));
            co.ctx.stroke();
            co.layerPush("line",[startX,startY],[e.offsetX,e.offsetY])
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);

  };
  //사각형 툴
  rectTool(){
    let co = this;
    let moveHandler;
    function drawHandler(e) {
      if(co.activatedTool!=="rect"){
        this.removeEventListener("mousedown", drawHandler
        )
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
          co.ctx.beginPath();
          co.ctx.lineWidth=2;
          co.ctx.strokeStyle="grey";
          co.ctx.setLineDash([10, 10]);
          co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
          co.ctx.drawImage(co.currentCanvas,0,0);
          co.ctx.strokeRect(
                                        co.xy(startX),
                                        co.xy(startY),
                                      co.xy(e.offsetX)-co.xy(startX),
                                      co.xy(e.offsetY)-co.xy(startY));
          co.ctx.stroke()
        })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
          co.ctx.drawImage(co.currentCanvas,0,0);
          co.ctx.beginPath();
          co.ctx.fillRect(
                                    co.xy(startX),
                                    co.xy(startY),
                                  co.xy(e.offsetX)-co.xy(startX),
                                  co.xy(e.offsetY)-co.xy(startY));
          co.ctx.stroke();
          co.layerPush("rect",[startX,startY],[e.offsetX,e.offsetY]);
        },{once:true});
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);
  };




  //펜 툴
  penTool(){
    let co = this;
    let moveHandler;
    function drawHandler(e) {
      if(co.activatedTool!=="pen"){
        this.removeEventListener("mousedown", drawHandler);
      } else {
          let startX = e.offsetX;
          let startY = e.offsetY;
          let pathArray = [];
          co.ctx.beginPath()
          co.ctx.moveTo(co.xy(startX),co.xy(startY));
          this.addEventListener("mousemove", moveHandler = function (e){
              pathArray.push([e.offsetX,e.offsetY])
              co.ctx.lineTo(co.xy(e.offsetX),co.xy(e.offsetY));
              co.ctx.stroke()
          })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          co.layerPush("pen",[startX,startY],undefined, pathArray)
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);

  };
  canvasRestore(){
    let co = this;
    co.ctx.restore();
    co.ctx.save();
  }


  // 경로값 저장용
  layerPush(
             type,
             moveTo = undefined,
             lineTo = undefined,
             path = undefined,
             src = undefined)
  {
    let co = this;

    let tempObj = {
      type : type,
      strokeStyle : co.ctx.strokeStyle,
      lineWidth : co.ctx.lineWidth,
      scale : co.fZoom,
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


    co.currentCanvas.src = co.canvas.toDataURL()
    return co.layerArr.push(tempObj);

  }


  layerLoad(){
    let co = this;
    co.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    co.ctx.scale(co.fZoom,co.fZoom)
    co.ctx.drawImage(co.defaultBack,0,0);

    co.layerArr.forEach((c, i)=>{
      co.ctx.strokeStyle=c.strokeStyle;
      co.ctx.lineWidth=c.lineWidth;


      co.ctx.beginPath();
      co.ctx.moveTo(co.xy(c.moveTo[0])/c.scale,co.xy(c.moveTo[1])/c.scale)
      if(c.type==="line") {
        co.ctx.lineTo(co.xy(c.lineTo[0])/c.scale,co.xy(c.lineTo[1])/c.scale)
        co.ctx.stroke();
      } else if(c.type==="pen"){
        c.path.forEach((p)=>{
          co.ctx.lineTo(co.xy(p[0])/c.scale,co.xy(p[1])/c.scale);
          co.ctx.stroke();
        })
      } else if(c.type==="rect"){

          co.ctx.fillRect(
            co.xy(c.moveTo[0])/c.scale,
            co.xy(c.moveTo[1])/c.scale,
            co.xy(c.lineTo[0])/c.scale-co.xy(c.moveTo[0])/c.scale,
            co.xy(c.lineTo[1])/c.scale-co.xy(c.moveTo[1])/c.scale
          );
      }
    })

      co.canvasRestore();
    console.log(JSON.stringify(this.layerArr));
    console.log(JSON.parse(JSON.stringify(this.layerArr)));

  }



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
      this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height);
    });
    ///////배열 테스트용/////
    document.getElementById("loadBtn").addEventListener("click", ()=>{
      this.layerLoad()
    });


    ///////배율조정 테스트용/////
    document.getElementById("plusBtn").addEventListener("click",()=>{
      if (this.fZoom<5.00) {
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.fZoom *= 1.2;
        this.ctx.scale(1.2, 1.2);
        this.ctx.drawImage(this.currentCanvas,0,0);
        this.ctx.scale(1/1.2, 1/1.2);
        this.currentCanvas.src = this.canvas.toDataURL()
      }
    });
    document.getElementById("minusBtn").addEventListener("click",()=>{
      if (this.fZoom>1.05){
        this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height);
        this.fZoom/=1.2;
        this.layerLoad();

        this.currentCanvas.src = this.canvas.toDataURL()
      }
    })
  };

  colorBtn(){
    buttons.forEach((content) => {
      let button = document.querySelector(`.${content}`);
      button.onclick = () => {
        this.ctx.strokeStyle = content;
        this.ctx.fillStyle = content;
        this.ctx.save();
      };
    });
  }



};//클래스 닫기

document.getElementById("settingBtn").addEventListener("click",()=>{
  let CCC = document.getElementById("123");
  console.log(CCC)
  let CCco = CCC.getContext("2d");
  CCco.strokeRect(0,0,50,50);
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
//   let keycode = e.keycode;
//   if (keycode === 13) {
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


